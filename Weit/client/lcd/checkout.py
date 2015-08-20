#!/usr/bin/python
# Author : ipmstyle, https://github.com/ipmstyle
#        : jeonghoonkang, https://github.com/jeonghoonkang

# for the detail of HW connection, see lcd_connect.py

import sys
import time
sys.path.append("./lib")
from lcd import *


def main():
  # Initialise display
  lcd_init()
  now = time.localtime()

  yellowLCDon();
  lcd_string("CHECK OUT", LCD_LINE_1, 2);
  lcd_string("%02d:%02d:%02d" % (now.tm_hour, now.tm_min, now.tm_sec),
          LCD_LINE_2, 2);
  time.sleep(2);

if __name__ == '__main__':

  try:
    main()
  except KeyboardInterrupt:
    pass
  finally:
    lcd_byte(0x01, LCD_CMD)
    lcd_string("Goodbye!",LCD_LINE_1,2)
    GPIO.cleanup()
