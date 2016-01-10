# Class that performs Haar Cascade -based face detection on
# input image and outputs detected faces as a list of rectangles.

import cv2

class face_detector:
    def __init__(self):
        self.faceDetection = cv2.CascadeClassifier("haarcascade_frontalface_default.xml")

    def findfaces(self, image):
        # Convert image to grayscale (required for Haar classification)
        gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

        # Detect faces, output bounding rectangles
        return self.faceDetection.detectMultiScale(gray,
        scaleFactor=1.1, minNeighbors=3, minSize=(30,30))