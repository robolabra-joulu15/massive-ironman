# -*- coding: utf-8 -*-

# Get and show video data from the drone.
import time, sys
import ps_drone
import cv2
import thread
import numpy as np
import pickle
from Camera_Calibrator import *

# Class definition

class cameraImageGetter:
    def __init__(self, cap):
        self.currentFrame = None
        self.capture = cap

    def start(self):
        thread.start_new_thread(self.updateFrame, ())

    def updateFrame(self):
        while(True):
            ret, self.currentFrame = self.capture.read()

            while(self.currentFrame == None):
                ret, self.currentFrame = self.capture.read()

    def getFrame(self):
        return self.currentFrame 

# End Class definition

# "Super-fast" gamma correction
# adapted from www.pyimagesearch.com/2015/10/05/opencv-gamma-correction

class gammaCorrecter():
    def __init__(self, gamma):
        # Build a lookup table mapping values [0,255] to adjusted ones.
        invgamma = 1.0 / gamma
        self.table = np.array([((i / 255.0) ** invgamma)*255 
             for i in range(256)]).astype("uint8")

def adjust_gamma(image):
    return cv2.lut(image, table)

# End gamma correcter

# Start and connect to the drone
drone = ps_drone.Drone()
drone.startup()

# Reset drone status and wait until done
drone.reset()
while (drone.getBattery()[0]==-1): time.sleep(0.1)

# Output battery level
print "Battery: " + str(drone.getBattery()[0]) + "% " + str(drone.getBattery()[1])

# Send 15 navdata/second
drone.useDemoMode(True)

# Go to multiconfig-mode
drone.setConfigAllID()
# SD -resolution
drone.hdVideo()
# Using the front camera
drone.frontCam()
# Wait for the config data count to change (initialization ready)
CDC = drone.ConfigDataCount
while CDC==drone.ConfigDataCount: time.sleep(0.001)

# Create a HOG classifier for detecting people
hog = cv2.HOGDescriptor()
hog.setSVMDetector(cv2.HOGDescriptor_getDefaultPeopleDetector())
print "HOG People Detector created."

# Start the video
print "Starting video!"

drone.midVideo()
print "midVideo() set"

# Create a camera calibrator
cameraCal = cameraCalibrator(5,7)

cap = cv2.VideoCapture("tcp://192.168.1.1:5555")
cap.set(cv2.CAP_PROP_FOURCC, cv2.VideoWriter_fourcc('H','2','6','4'))
cap.set(cv2.CAP_PROP_BUFFERSIZE, 0)
print "Capturing camera."

imageGetter = cameraImageGetter(cap)
imageGetter.start()
frame = imageGetter.getFrame()
while(frame == None):
    frame = imageGetter.getFrame()
cv2.imshow("frame", frame)

while(True):
    frame = imageGetter.getFrame()
    ret, corners = cameraCal.checkImage(frame)
    # If a chessboard pattern is found, show it
    # for 2 seconds and continue the loop
    if ret:
        frame = cv2.drawChessboardCorners(frame, (7,6), corners, ret)
        cv2.imshow("frame", frame)
        if cv2.waitKey(2000) & 0xFF == ord('q'):
            break
        continue
    # otherwise, just show the frame, waiting
    # for a q(uit) command
    cv2.imshow("frame", frame)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

# After the camera playback has been quit,
# produce the camera calibration, release
# the camera and close the playback window(s).
ret, mtx, dist, newcameramtx, roi = cameraCal.getCalibration()
f = open("calibrationconfigurationhd", "w")
pickle.dump([mtx, dist, newcameramtx, roi], f)
f.close()
cap.release()
cv2.destroyAllWindows()