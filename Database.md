### 사용자 추가
- GRANT ALL PRIVILEGES ON dbname.* TO username@localhost IDENTIFIED BY 'password';  
password 비밀번호의 username의 유저를 생성하고 dbname에 대한 모든 권한을 갖게 한다.  
  
### 사용자 삭제
- DLETE FROM USER WHERE USER='username';  
FLUSH PRIVILEGES;  
