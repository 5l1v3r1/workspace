#**2015-07-06**#

> ###온습도 센서와 LCD 모듈을 이용하여, LCD에 온도 표시하기

> - sht20.py 과 ip_adrr.py 를 참고하여 구현  
> [sht20_lcd.py](https://github.com/hello920922/mgpark_keti/blob/master/sht20_lcd.py "sht20_lcd.py")

> ### /home/user/.bashrc
> - user가 로그인하면 자동으로 실행되는 스크립트

> ### /etc/rc.local
> - RaspberryPi가 켜질 때 자동으로 실행되는 스크립트

> ### screen -dmS [screen name] [command]
> - [screen name] 이름의 Screen을 생성하고, 그 스크린에 [command]의 명령을 실행함.

> ### [command] &
> - 뒤에 &를 붙이면 백그라운드 앱으로 실행됨.


#**2015-07-07**#

> ### 07-06에 했던 예제에 Co2 센서를 결합

> - BereCO2.py 을 참고하여 구현


#**~2015-07-23**#

> ### NFC 모듈 조사, 각 언어로 라즈베리파이 코딩, Home Raspberry Pi에 서버구축

#**2015-08-03**#

> ### Markdown Syntax
> - http://scriptogr.am/myevan/post/markdown-syntax-guide-for-scriptogram  
>  마크다운에 대한 기본적인 사용법이 적혀있음.  
> - http://www.tablesgenerator.com  
>  마크다운 및 다른 언어에 테이블을 만들면 코드로 변환시켜주는 사이트.  

> ### /boot/cmdline.txt
> - 맨 끝에 ip=xxx.xxx.xxx.xxx를 붙이면 해당 아이피를 갖게됨.

> ### 라즈베리파이 초기설정 스크립트
> - vim 설치, APM설치 등의 [스크립트](https://github.com/hello920922/InitializeScript/)를 구현

> ### XlsxWriter : Python을 이용하여 Excel File을 만드는 프로그램
> #### [Web Link](https://xlsxwriter.readthedocs.org/#)  
  
> ####Install :  
>     git clone https://github.com/jmcnamara/XlsxWriter.git  
>     cd XlsxWriter  
>     sudo python setup.py install  
> ####Example :  
>     import xlsxwriter  
  
>     workbook = xlsxwriter.Workbook('hello.xlsx')  
>     worksheet = workbook.add_worksheet()  
  
>     worksheet.write('A1', 'Hello world')  
>     workbook.close()  

#**2015-08-04**#
> ### 실행파일을 어느 디렉토리에서나 사용할 수 있도록 명령어 등록하는 방법  
>     vi /etc/profile  
>     PATH에 지정된 디렉토리 중 하나 선택.  
>     cd [선택한 디렉토리 절대경로]  
>     ln [원본파일 절대경로] [링크파일 이름]  
>     sudo reboot  
>     cd  
>     [링크파일 이름]으로 테스트  
> ### PN532 라이브러리 설치  
> #### Setting Raspi
>     sudo raspi-config  
>     disable uart (Select Serial, Select No)  
>     sudo reboot  
> #### Download libnfc  
>     cd /home/pi/  
>     git clone https://github.com/nfc-tools/libnfc.git  
> #### Setup libnfc for the Pi  
>     cd libnfc/  
>     sudo mkdir /etc/nfc  
>     sudo mkdir /etc/nfc/devices.d  
>     sudo cp contrib/libnfc/pn532_uart_on_rpi.conf.sample /etc/nfc/devices.d/pn532_uart_on_rpi.conf  
>     sudo vi /etc/nfc/devices.d/pn532_uart_on_rpi.conf  
>     맨 밑에 아래의 문장 추가  
>     allow_intrusive_scan = true  
> #### Run Config  
>     sudo apt-get install autoconf  
>     sudo apt-get install libtool  
>     sudo apt-get install libpcsclite-dev libusb-dev  
>     autoreconf -vis  
>     ./configure --with-drivers=pn532_uart --sysconfdir=/etc --prefix=/usr  
> #### Build  
>     sudo make clean  
>     sudo make install all  
