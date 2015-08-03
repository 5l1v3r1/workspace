#**2015-07-06**#

#### 온습도 센서와 LCD 모듈을 이용하여, LCD에 온도 표시하기

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


#**2015-07-07**#

#### 07-06에 했던 예제에 Co2 센서를 결합

BereCO2.py 을 참고하여 구현


#**~2015-07-23**#

#### NFC 모듈 조사, 각 언어로 라즈베리파이 코딩, Home Raspberry Pi에 서버구축

#**2015-08-03**#

#### Markdown Systax
[Web link](http://scriptogr.am/myevan/post/markdown-syntax-guide-for-scriptogram)
마크다운에 대한 기본적인 사용법이 적혀있음.

#### XlsxWriter : Python을 이용하여 Excel File을 만드는 프로그램
[Web Link](https://xlsxwriter.readthedocs.org/#)
Install :

git clone https://github.com/jmcnamara/XlsxWriter.git
cd XlsxWriter
sudo python setup.py install
