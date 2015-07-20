import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)

print "LED PIN TEST"

pins = [13,19,26]

for pin in pins :
    GPIO.setup(pin,GPIO.OUT)
    GPIO.output(pin,False)

try :
    while(True) :
        for pin in pins :
            GPIO.output(pin,True)
            time.sleep(1)
            GPIO.output(pin,False)

except KeyboardInterrupt :
    pass

finally :
    print "\nExit Program...Goodbye!"
    GPIO.cleanup()

