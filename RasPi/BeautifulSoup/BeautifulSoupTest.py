import sys
import urllib
sys.path.append("./BeautifulSoup")
from BeautifulSoup import *

data = urllib.urlopen('http://comic.naver.com/webtoon/list.nhn?titleId=20853&weekday=fri')
soup = BeautifulSoup(data)
cartoons = soup.findAll('td', attrs={'class':'title'})

for cartoon in cartoons:
    title = cartoon.find('a').text
    print title
