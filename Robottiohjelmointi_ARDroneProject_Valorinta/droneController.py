# Class for controlling the drone in a separate thread

import ps_drone
import thread
import time

class droneController:

    def __init__(self, drone):
        self.drone = drone
        # Deviation, or "dev", measures the distance of the
        # detected object (human in this case) from the center
        # of the drone's viewpoint along the x-axis. This
        # variable is then used to control the turning of the drone.
        self.dev = 0
        # This variable, updated from the main thread like
        # deviation above, tells the drone control script whether
        # it can currently see any humans; if it can't, the drone
        # simply stops in place. 
        self.humansDetected = False
        # The following variable defines whether the bot should
        # continue or land and stop running the control script.
        self.continueFlying = True

    def start(self):
        self.drone.takeoff()
        thread.start_new_thread(self.nextAction, ())

    # The loop that controls drone movements based on the
    # current value of deviation
    def nextAction(self):
        while(self.continueFlying):
            # If there is a person in our field of view...
            if self.humansDetected:
            # ...turn to face them, (using deviation/20 as angle)
                if abs(self.dev/20) > 1:
                    self.drone.turnAngle(self.dev/20)
                    #print "Turn around, angle: " + str(self.dev/20)
            # If abs(dev/20) <= 1, the person is near enough
            # to the center, and the drone should start approaching
                else:
                    #print "Move forward."
                    self.drone.moveForward(0.05)
            # or we can't see a human at all, in which case we
            # should stop.
            else:
                 #print "Stop."
                 self.drone.stop()
            # Whatever the action chosen, wait 1 second
            # before trying another action. (This is so that the
            # drone won't get congested with conflicting commands)
            time.sleep(1)

    def setDeviation(self,dev):
        self.dev = dev

    def setHumansDetected(self,bool):
         self.humansDetected = bool

    def land(self):
         self.continueFlying = False
         time.sleep(1)
         self.drone.land()

# End droneController
