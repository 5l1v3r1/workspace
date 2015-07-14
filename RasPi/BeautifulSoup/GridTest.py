# -*- coding: utf-8 -*-

import sys
import urllib
sys.path.append('./BeautifulSoup')
from BeautifulSoup import *

def getGrid() :
    data = urllib.urlopen('http://fx.keb.co.kr/FER1101C.web?schID=fex&mID=FER1101C')

    soup = BeautifulSoup(data)

    contries = soup.findAll('tr',attrs={'class':'odd'})

    for contry in contries :
        name = contry.findAll('td',attrs={'class':'first_child grid_code'})[0].find('a').text
        if name.endswith('USD'):
            print name
            grid_money_items = contry.findAll('td',attrs={'class':'grid_money'})
            purchase = 'purchase : ' + grid_money_items[0].text
            sell = 'sell     : ' + grid_money_items[1].text
            print purchase
            print sell
    return purchase, sell 
        

#print soup.prettify()
