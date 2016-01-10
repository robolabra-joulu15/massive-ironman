# Change "harry.jpg" if you want to test with other images.

import cv2
from facedetector import *

faceDetect = face_detector()

image = cv2.imread("harry.jpg")
image = cv2.resize(image, (640,480))

faces = faceDetect.findfaces(image)
for (x,y,w,h) in faces:
    image = cv2.rectangle(image, (x,y), (x+w,y+h), (255,0,0), 2)

cv2.imshow("faces", image)
cv2.waitKey(0)