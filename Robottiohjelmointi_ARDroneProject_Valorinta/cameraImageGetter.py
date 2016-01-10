# A class for getting the camera feed in a separate thread.
# The original idea was based on this stackoverflow answer:
# http://stackoverflow.com/questions/24370725/opencv-videocapture-only-updates-after-5-reads

import cv2
import thread
import time

class cameraImageGetter:
    def __init__(self, capstring):
        self.currentFrame = None
        self.capstring = capstring
        self.continueUpdate = True
        self.capture = None

    def __initializeCapture(self):
        # Sometimes cv2 seems to get a capture that doesn't actually return any
        # frames; this is a workaround. If you fail to get a frame 26 times in
        # a row, retry cv2.VideoCapture() from the beginning.
        while(True):
            print "Capturing..."
            self.capture = cv2.VideoCapture(self.capstring)
            self.capture.set(cv2.CAP_PROP_FOURCC, cv2.VideoWriter_fourcc('H','2','6','4'))
            self.capture.set(cv2.CAP_PROP_BUFFERSIZE, 0)
            nonecount = 0
            ret, currentFrame = self.capture.read()
            while(currentFrame==None):
                ret, currentFrame = self.capture.read()
                nonecount = nonecount + 1
                if nonecount > 25:
                    break
            else:
                break

    def start(self):
        self.__initializeCapture()
        thread.start_new_thread(self.updateFrame, ())

    def updateFrame(self):
        while(self.continueUpdate):
            ret, self.currentFrame = self.capture.read()

            #while(self.currentFrame == None):
                #ret, self.currentFrame = self.capture.read()

    def getFrame(self):
        return self.currentFrame 

    def stop(self):
        self.continueUpdate = False
        time.sleep(1)
        self.capture.release()