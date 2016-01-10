# Essentially defining the python equivalent of an "interface",
# an empty class, for controlling a flying robot of some kind, then
# defining a class that implements the methods for controlling
# the AR drone in particular. This is so the code can easily be
# ported to other drones.

import time, ps_drone

#class FlyingRobot:
    #def __init__():

    #def takeoff():

    #def turnAngle():

    #def getBattery():


class Quadcopter:
    def __init__(self):
        self.drone = ps_drone.Drone()
        self.drone.startup()
        self.drone.reset()
        while(self.drone.getBattery()[0]==-1): time.sleep(0.1)
        self.drone.useDemoMode(True)
        self.drone.setConfigAllID()
        self.drone.sdVideo()
        self.drone.frontCam()
        CDC = self.drone.ConfigDataCount
        while CDC==self.drone.ConfigDataCount: time.sleep(0.001)
        self.drone.midVideo()        

    def takeoff(self):
        self.drone.takeoff()

    def turnAngle(self, angle):
        self.drone.turnAngle(angle,1,1)

    def stop(self):
        self.drone.stop()

    def moveForward(self,amount):
        self.drone.moveForward(amount)

    def land(self):
        self.drone.land()

    def getBattery(self):
        return self.drone.getBattery()