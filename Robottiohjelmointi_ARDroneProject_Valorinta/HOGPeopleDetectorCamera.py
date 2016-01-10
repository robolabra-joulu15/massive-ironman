# Class that takes a video feed frame by frame and uses HOG to
# detect people every ten (or whatever value
# maxframecount has been set to) frames.

import cv2

def rectanglesorter(rect):
    return rect[2]*rect[3]

class HOG_people_detector:
    def __init__(self, maxframecount):
        self.maxframecount = maxframecount
        self.framecount = 0
        self.rects = []
        self.weights = []
        self.hog = cv2.HOGDescriptor()
        self.hog.setSVMDetector(cv2.HOGDescriptor_getDefaultPeopleDetector())

    def checkFrame(self, frame):
        if self.framecount == self.maxframecount:
            self.framecount = 0
            self.rects, self.weights = self.hog.detectMultiScale(frame, 
                winStride=(2,2), padding=(8,8), scale=1.3)
            # Sort the rectangles by area in descending order
            self.rects = sorted(self.rects, key=rectanglesorter,
            reverse=True)
            # An alternative implemention in case I needed the weights:
            #rectsweights = sorted(zip(self.rects, self.weights))
            #self.rects = [i[0] for i in rectsweights]
            #self.weights = [i[1] for i in rectsweights]
        else:
            self.framecount += 1
        return self.rects

# "Super-fast" gamma correction
# I wrote this while I was trying to figure out why the HOG
# detector couldn't reliably detect people (it turned out that
# the camera was not calibrated). HOG -detection has
# gamma correction as an initial step before the actual detection;
# I kept the class here in case I needed to adjust the parameters
# of that.
# Adapted from www.pyimagesearch.com/2015/10/05/opencv-gamma-correction

class gammaCorrecter():
    def __init__(self, gamma):
        # Build a lookup table mapping values [0,255] to adjusted ones.
        invgamma = 1.0 / gamma
        self.table = np.array([((i / 255.0) ** invgamma)*255 
             for i in range(256)]).astype("uint8")

def adjust_gamma(image):
    return cv2.lut(image, table)

# End gamma correcter
