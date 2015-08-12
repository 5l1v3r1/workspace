# -*- coding:utf-8 -*-
import os, smtplib
from email.MIMEMultipart import MIMEMultipart
from email.MIMEBase import MIMEBase
from email.MIMEText import MIMEText
from email.header import Header
from email import Encoders
import time
import datetime
import random
import datetime

gmail_user="wecomitacademy@gmail.com"
gmail_pwd="tasigmin132"
attach_file="/home/pi/workspace/RasPi/python/sendMail/We_IT.xlsx"

def send_gmail(to, attach):
    contents = datetime.datetime.today().strftime("%Y-%m-%d") + "우리IT컴퓨터학원 현황"

    msg=MIMEMultipart('alternative')

    msg.attach(MIMEText(contents, _charset='utf-8'))
    msg['From']=gmail_user
    msg['To']=to
    msg['Subject']=Header(contents,'utf-8') # 제목 인코딩
    
    part=MIMEBase('application','octet-stream')
    part.set_payload(open(attach, 'rb').read())
    Encoders.encode_base64(part)
    part.add_header('Content-Disposition','attachment; filename="%s"' % os.path.basename(attach))
    msg.attach(part)
    
    mailServer=smtplib.SMTP("smtp.gmail.com",587)
    mailServer.ehlo()
    mailServer.starttls()
    mailServer.ehlo()
    mailServer.login("wecomitacademy",gmail_pwd)
    mailServer.sendmail(gmail_user, to, msg.as_string())
    mailServer.close()


def mainLoop():
    print "Program Ready"
    print "----------------------"
    f = open("list.txt", "r")
    emails = f.readlines()
    for email in emails:
        rand = random.randrange(1,5)       # Set range of the waiting time.
        email = email.strip()              # Removing White spaces.
        if email == "" :
            continue
        print "[" + str(datetime.datetime.now()) + "] Sending email to " + email + "..."
        send_gmail(email,attach_file)
        print "[" + str(datetime.datetime.now()) + "] Complete... Waiting for " + str(rand) +" seconds."
        time.sleep(rand)

    print "Mails have just been sent. The program is going to end." 


if __name__ == "__main__":
    mainLoop()
