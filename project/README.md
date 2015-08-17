### NFC-Module을 이용한 출결시스템

#### 설계
- Client RaspberryPi  
  
Client Raspi는 실행시에 UDP통신으로 Server의 IP를 받아와서  
자신이 읽은 uid를 보낼 IP를 설정해놓는다.  
그 후 자신이 읽은 NFC의 UID를 해당 IP로 TCP통신을 사용해 보낸다.  
  
- Server RasberryPi  

1. Server Raspi는 스레드를 사용하여 수시로 UDP통신과 TCP통신으로 데이터를 받는다.  
UDP는 수신을 받고, 응답으로 자신의 IP를 보낸다.  
TCP는 UID를 수신을 받고, 자신의 데이터베이스에 접속하여,  
Cliend로부터 받은 UID에 해당하는 사람의 데이터를 읽어오고,  
LOG Table에 해당 사람의 데이터를 기록한다.  
  
STULOG  
('현재날짜', '이름', '담당강사', 'Check In', 'Check Out')  
INSTLOG  
('현재날짜', '이름', 'Check In', 'Check Out', '근무시간')  
  
2. Web Page를 구현하여, 사용자 등록과 수정, 삭제를 할 수 있도록 만든다. (미구현)  
Client의 UID를 읽는 프로그램 외에, 자기 자신스스로 읽어서 등록을 하는 기능을 만든다.  
PHP를 사용하여 해당 데이터를 데이터베이스에 저장, 수정할 수 있도록 구현한다.
