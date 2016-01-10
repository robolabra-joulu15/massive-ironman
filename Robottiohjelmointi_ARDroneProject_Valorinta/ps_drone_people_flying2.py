# -*- coding: utf-8 -*-

# Get and show video data from the drone.
import time, sys
import cv2
import numpy as np
import pickle
from HOGPeopleDetectorCamera import *
from Quadcopter import *
from cameraImageGetter import *
from droneController import *

# Start and connect to the drone
drone = Quadcopter()

# Output battery level
print "Battery: " + str(drone.getBattery()[0]) + "% " + str(drone.getBattery()[1])

# Create a HOG classifier for detecting people
hog = HOG_people_detector(10)
print "HOG People Detector created."

# Start the video
print "Starting video!"

imageGetter = cameraImageGetter("tcp://192.168.1.1:5555")
imageGetter.start()

while(True):
    frame = imageGetter.getFrame()
    while(frame == None):
        frame = imageGetter.getFrame()
    cv2.imshow("frame", frame)
    if cv2.waitKey(1) & 0xFF == ord('s'):
        break

# Get the camera calibration parameters
f = open("calibrationconfiguration", "r")
mtx, dist, newcameramtx, roi = pickle.load(f)
f.close()

xr,yr,wr,hr = (roi[0]/1.1, roi[1]/1.5, 2.5*roi[2], 2.5*roi[3])


# Start the drone
print "Starting drone!"
droneControl = droneController(drone)
droneControl.start()
# Wait for the drone to take off.
time.sleep(2)

while(True):
    frame = imageGetter.getFrame()
    
    # Correct the distortion
    frame = cv2.undistort(frame, mtx, dist, None, newcameramtx)
    frame = frame[yr:yr+hr, xr:xr+wr]
    
    # Detect people
    people = hog.checkFrame(frame)

    # Draw a rectangle around the nearest person, if found:
    if people:
        (x, y, w, h) = people[0]
        # If the person fills > 95% of FOV along the y-axis,
        # Assume that the person is close enough and stop.
        if h <= 0.95*hr:
            cv2.rectangle(frame, (x,y), (x+w, y+h), (0,0,255), 2)
            droneControl.setDeviation(x+((w-wr)/2))
            droneControl.setHumansDetected(True)
            #print "Deviation from center: " + str((x+w/2)-wr/2)
        else:
            droneControl.setHumansDetected(False)
    else:
        droneControl.setHumansDetected(False)

    cv2.imshow("frame", frame)
    
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

droneControl.land()
imageGetter.stop()
time.sleep(7)
cv2.destroyAllWindows()