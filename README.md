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
>     ln -s [원본파일 절대경로] [링크파일 이름]  
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
> #### Example  
> - http://www.libnfc.org/api/examples_page.html  
  
> ### Raspi에 Database 연동하기  
>     sudo apt-get install libmysql-java  
>     cd $JAVA_HOME/lib
>     mkdir java
>     cd /usr/share/java  
>     cp mysql-connector-java-x.x.xx.jar $JAVA_HOME/lib/java/mysql-connector-java.jar  
>     sudo vi /etc/profile 혹은 bashrc (개인 계정이면 홈디렉터리로)  
>     JAVA_HOME 밑에 다음의 내용추가  
  
>     CLASSPATH=$CLASSPATH:$JAVA_HOME/lib/java/*
>     export CLASSPATH  
>     sudo reboot  

#**2015-08-05**#

> ### 환경변수  
> - JAVA_HOME : 자바가 설치되어 있는 경로  
> - PATH : 자바명령어가 있는 경로  
> - JAVA_OPT : 자바를 실행할 때의 옵션.(설정해두면 일일이 치지 않아도 항상 이 명령어가 작동)  
> - CLASSPATH : 자바를 실행할 때, jar 파일등의 라이브러리를 지정해주는 경로.

> ### 라이브러리 설정
> JAVA_HOME이 설정되어 있는 상태에서 실행.

> 1. CLASSPATH=$CLASSPATH:$JAVA_HOME/lib/java/*  
> 2. $JAVA_HOME/lib/java/ 디렉터리에 jar파일등의 라이브러리들을 넣어두면 라이브러리를 자동으로 포함하여 실행.  
> - 자원낭비가 심하지 않도록 라즈베리파이를 실행할 때 꼭 필요한 것들만 넣는 것이 좋음. (ex)database, gpio 등  

> ### Raspi에 wiringPi 및 pi4j 설치방법
> #### Install wiringPi
>     git clone git://git.drogon.net/wiringPi
>     cd wiringPi
>     ./build

> #### Install pi4j
>     http://pi4j.com/download.html 에서 최신버전 확인
>     wget http://get.pi4j.com/download/pi4j-x.x.deb
>     sudo dpkg -i pi4j-x.x.deb
>     sudo cp /opt/pi4j/lib/* $JAVA_HOME/lib/java/

> ### SSH-Key
> - public-key : remote server에 알려줄 ssh-key  
> - private-key : remote client가 가지고 있을 ssh-key  

> #### SSH-Key가 존재하는지 확인
>     ls -a ~/.ssh
>     만약 .ssh 디렉터리가 없다면 SSH-Key가 존재하지 않는 것임.

> #### SSH-Key 생성  
>     ssh-keygen
>     cd ~/.ssh
>     cat id_rsa.pub

> - Enter file in which to save the key (/home/pi/.ssh/id_rsa):  
> SSH-Key가 저장될 공간을 선택하는 것인데, 로그인한 사용자의 홈디렉터리가 기본적이다.  
> - Enter passphrase (empty for no passphrase):  
> - Enter same passphrase again:  
> SSH-Key 비밀번호를 입력하는 것인데, 자동로그인을 위해서는 그냥 enter를 치면 된다.  
> 하지만 보안의 흠이 생길 수 있으니, 주의  

> #### id_rsa & id_rsa.pub
> - id_rsa : private-key로써 절대 공개되어서는 안된다.  
> - id_rsa.pub : publie-key로 remote server에 이 파일을 보내주면 된다.  

> #### SSH-Key 추가 방법
>     mv ./id_ras.pub ~/.ssh/[client name].pub
>     cat ~/.ssh/[client name].pub >> ~/.ssh/authorized_keys
> 1. client로 받은 id_ras.pub를 client이름으로 변경(필수는 아니지만 구분하기 쉬움)  
> 2. 변경한 id_ras.pub 파일을 ~/.ssh로 이동  
> 3. authorized_keys에 client로 받은 id_ras.pub의 내용을 추가  

#**2015-08-06**#
> ### C CommandLine 실행
>     #include <stdlib.h>
>     #include <stdio.h>
>     
>     int main(){
>       FILE *fp;
>       int state;
>     
>       char buff[256];
>       while(1){
>         fp = popen("nfc-read","r");
>         if(fp == NULL){
>           perror("error : ");
>           exit(0);
>         }
>     
>         while(fgets(buff, 256, fp) != NULL){
>           printf("%s",buff);
>         }
>         pclose(fp);
>       }
>     }
> ### 코딩없이 편집하여 웹페이지를 만들어주는 사이트
> [WIX](http://ko.wix.com/)

#**2015-08-07**#
> ### Linux C언어 TCP 통신 Client
>     #include <stdio.h>
>     #include <sys/socket.h>
>     #include <arpa/inet.h>
>     #include <sys/stat.h>
>     #include <string.h>
>     #include <unistd.h>
>     #include <stdlib.h>
>     
>     #define MY_SERVER_IP "[Server's host]"
>     #define MAXLINE 1024
>     
>     int main(int argc, char **argv){
>         struct sockaddr_in serveraddr;
>         int server_sockfd;
>         int client_len;
>         char buf[MAXLINE];
>     
>         if((server_sockfd = socket(AF_INET, SOCK_STREAM,0)) == -1){
>             perror("error : ");
>             return 1;
>         }
>         serveraddr.sin_family = AF_INET;
>         serveraddr.sin_addr.s_addr = inet_addr(MY_SERVER_IP);
>         serveraddr.sin_port = htons([port number]);
>     
>         client_len = sizeof(serveraddr);
>     
>         if(connect(server_sockfd,(struct sockaddr *)&serveraddr, client_len) == -1){
>             perror("connect error : ");
>             return 1;
>         }
>         memset(buf, 0x00, MAXLINE);
>         read(0, buf, MAXLINE);
>         if(write(server_sockfd, buf, MAXLINE) <= 0){
>             perror("write error: ");
>             return 1;
>         }
>         memset(buf, 0x00, MAXLINE);
>         close(server_sockfd);
>         return 0;
>     }
#**2015-08-10**#
> ### Java를 이용하여 Excel 파일 다루기
> - https://github.com/hello920922/workspace/RasPi/java/Excel\ Library.zip  
>   파일을 다운로드받아서 사용  
>  
> #### Excel 읽기
>     import org.apache.poi.ss.usermodel.Cell;
>     import org.apache.poi.ss.usermodel.Row;
>     import org.apache.poi.ss.usermodel.XSSFSheet;
>     import org.apache.poi.xssf.usermodel.XSSFWorkbook;
>     
>     public class Excel_Read {
>       public static void main(String[] args) {
>     		XSSFWorkbook wb = null;
>     		try {
>     			wb = new XSSFWorkbook("filename.xlsx");
>     		} catch (IOException e) {
>     			e.printStackTrace();
>     		}
>     		if(wb==null)
>     			System.exit(0);
>     		XSSFSheet sh = wb.getSheet("sheet name");
>     		if(sh==null)
>     			System.exit(0);
>     		for(Row row : sh){
>     			for(Cell cell : row){
>     				switch(cell.getCellType()){
>     					case Cell.CELL_TYPE_STRING :
>     						System.out.print(cell.getStringCellValue() + "\t");
>     						break;
>     					case Cell.CELL_TYPE_NUMERIC :
>     						System.out.print(cell.getNumericCellValue() + "\t");
>     						break;
>     				}
>     			}
>     			System.out.println();
>     		}
>     	}
>     }
> #### Excel 쓰기
>     import org.apache.poi.ss.usermodel.Cell;
>     import org.apache.poi.ss.usermodel.Row;
>     import org.apache.poi.xssf.usermodel.XSSFSheet;
>     import org.apache.poi.xssf.usermodel.XSSFWorkbook;
>     
>     public class Excel_Write {
>     	public static void main(String[] args) {
>     		XSSFWorkbook wb = null;
>     		wb = new XSSFWorkbook();
>     		
>     		if(wb==null)
>     			System.exit(0);
>     		XSSFSheet sh = wb.createSheet("sheet name");
>     		if(sh==null)
>     			System.exit(0);
>     		
>     		Row row = sh.createRow(0);
>     		Cell cell = row.createCell(0);
>     		
>     		cell.setCellValue("Data");
>     	}
>     }
#**2015-08-12**#
> ### Project 구현.
> #### 문자보내기
> http://www.coolsms.co.kr/ 참고
> #### Python을 이용한 메일보내기
> [sendMail.py](https://github.com/hello920922/workspace/blob/master/RasPi/python/sendMail/sendMail.py) 참고

#**2015-08-13**#
> ### Database 설계  
Database : ACADEMY  
  
Table : STUDENT  
  
| Field   | Type        | Null | Key | Default | Extra |
|:-------:|:-----------:|:----:|:---:|:-------:|:-----:|
| UID     | varchar(20) | NO   | PRI | NULL    |       |
| NAME    | varchar(10) | NO   |     | NULL    |       |
| BIRTH   | date        | NO   |     | NULL    |       |
| SUBJECT | varchar(30) | NO   |     | NULL    |       |
| START   | date        | NO   |     | NULL    |       |
| END     | date        | NO   |     | NULL    |       |
| PHONE   | varchar(13) | NO   |     | NULL    |       |
| PARENT  | varchar(13) | YES  |     | NULL    |       |
| NOTE    | mediumtext  | YES  |     | NULL    |       |
  
  
Table : STULOG  
  
| Field    | Type        | Null | Key | Default | Extra          |
|:--------:|:-----------:|:----:|:---:|:-------:|:--------------:|
| NO       | int(11)     | NO   | PRI | NULL    | auto_increment |
| UID      | varchar(20) | YES  |     | NULL    |                |
| DAY      | date        | YES  |     | NULL    |                |
| NAME     | varchar(10) | YES  |     | NULL    |                |
| CHECKIN  | time        | YES  |     | NULL    |                |
| CHECKOUT | time        | YES  |     | NULL    |                |
| MACADDR  | varchar(20) | YES  |     | NULL    |                |
  
  
Table : INSTRUCTOR  
  
| Field  | Type        | Null | Key | Default | Extra |
|:------:|:-----------:|:----:|:---:|:-------:|:-----:|
| UID    | varchar(20) | NO   | PRI |         |       |
| NAME   | varchar(10) | NO   | UNI | NULL    |       |
| DEPART | varchar(30) | NO   |     | NULL    |       |
| PHONE  | varchar(13) | NO   |     | NULL    |       |
| EMAIL  | varchar(30) | NO   |     | NULL    |       |
  
  
Table : INSTLOG  
  
| Field    | Type                | Null | Key | Default | Extra          |
|:--------:|:-------------------:|:----:|:---:|:-------:|:--------------:|
| NO       | int(11)             | NO   | PRI | NULL    | auto_increment |
| DAY      | date                | YES  |     | NULL    |                |
| NAME     | varchar(10)         | YES  |     | NULL    |                |
| CHECKIN  | time                | YES  |     | NULL    |                |
| CHECKOUT | time                | YES  |     | NULL    |                |
| WORKTIME | tinyint(3) unsigned | YES  |     | NULL    |                |
  
  
Table : UID  
  
| Field        | Type        | Null | Key | Default | Extra |
|:------------:|:-----------:|:----:|:---:|:-------:|:-----:|
| UID          | varchar(20) | NO   | PRI | NULL    |       |
| ISINSTRUCTOR | tinyint(1)  | NO   |     | 0       |       |
  
  
Table : CLASSROOM  
  
| Field   | Type        | Null | Key | Default | Extra |
|:-------:|:-----------:|:----:|:---:|:-------:|:-----:|
| MACADDR | varchar(20) | NO   | PRI | NULL    |       |
| NAME    | tinyint(1)  | NO   |     | 0       |       |
  
  
Table : SUBJECT  
  
| Field | Type        | Null | Key | Default | Extra |
|:-----:|:-----------:|:----:|:---:|:-------:|:-----:|
| NAME  | varchar(30) | NO   | PRI | NULL    |       |
| LINK  | mediumtext  | YES  |     | NULL    |       |

#**2015-08-18**#
- Client의 Mac address를 읽어 강의실로 저장하는 기능 추가

#**2015-08-19**#
- PHP로 데이터베이스의 학생 목록들을 불러와 HTML table로 만들어주는 기능 추가
- 서버로부터 Check status를 받아, LCD로 Check status와 현재시간을 표시하는 기능 추가  
  
#**2015-08-20**#
- 2015-08-19에 구현한 table에 정보를 클릭하면 해당 학생의 출결기록을 보여주는 기능 추가
