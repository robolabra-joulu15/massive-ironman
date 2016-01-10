# Class that accepts images (such as frames from a camera
# stream), checks for a chessboard pattern and performs camera
# calibration after a sufficient amount of images at various angles 
# has been found.

import numpy as np
import cv2
import time

class cameraCalibrator:
    def __init__(self, x,y):
        self.criteria = (cv2.TERM_CRITERIA_EPS + cv2.TERM_CRITERIA_MAX_ITER, 30, 0.001)
        self.objp = np.zeros((x*y,3), np.float32)
        self.objp[:,:2] = np.mgrid[0:x, 0:y].T.reshape(-1,2) 
        self.objpoints = []
        self.imgpoints = []
        self.x = x
        self.y = y
        self.calltime = 0
        self.size = (0,0)

    def checkImage(self, image):
        if (self.calltime - time.time()) > 0:
              return (False, [])
        gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
        self.size = gray.shape[::-1]
        # Find chessboard corners
        ret, corners = cv2.findChessboardCorners(gray, (self.x,self.y))
        # If found...
        if ret == True:
            #... add object points and image points
            self.objpoints.append(self.objp)
            corners = cv2.cornerSubPix(gray, corners, (11,11), (-1,-1), self.criteria)
            self.imgpoints.append(corners)
            self.calltime = time.time()+5
        return (ret, corners)

    def getCalibration(self):
        ret, mtx, dist, rvecs, tvecs = cv2.calibrateCamera(self.objpoints, self.imgpoints, self.size, None, None)
        h, w = self.size[:2]
        newcameramtx, roi = cv2.getOptimalNewCameraMatrix(mtx, dist, (w,h), 1, (w,h))
        return (True, mtx, dist, newcameramtx, roi)