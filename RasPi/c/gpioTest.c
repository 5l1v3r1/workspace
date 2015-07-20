#include <stdio.h>
#include <wiringPi.h>

int main(void){
    int pins[3] = {23,24,25};
    int i;

    if(wiringPiSetup() == -1)
        return -1;

    for(i=0; i<3; i++){
        pinMode(pins[i],OUTPUT);
    }
    while(1){
        for(i=0; i<3; i++){
            digitalWrite(pins[i],1);
            delay(1000);
            digitalWrite(pins[i],0);
        }
    }

    return 0;
}
