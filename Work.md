#**2015-07-06**#

####온습도 센서와 LCD 모듈을 이용하여, LCD에 온도 표시하기

sht20.py 과 ip_adrr.py 를 참고하여 구현
[sht20_lcd.py](https://github.com/hello920922/mgpark_keti/blob/master/sht20_lcd.py "sht20_lcd.py")

#### /home/user/.bashrc
user가 로그인하면 자동으로 실행되는 스크립트

#### /etc/rc.local
RaspberryPi가 켜질 때 자동으로 실행되는 스크립트

#### screen -dmS [screen name] [command]
[screen name] 이름의 Screen을 생성하고, 그 스크린에 [command]의 명령을 실행함.

#### [command] &
뒤에 &를 붙이면 백그라운드 앱으로 실행됨.
