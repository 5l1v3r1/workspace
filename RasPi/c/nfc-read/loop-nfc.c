#include <stdlib.h>
#include <stdio.h>

int main(){
  FILE *fp;
  int state;

  char buff[256];
  while(1){
    fp = popen("nfc-read","r");
    if(fp == NULL){
      perror("error : ");
      exit(0);
    }
    
    while(fgets(buff, 256, fp) != NULL){
      printf("%s",buff);
    }
    pclose(fp);
  }
}
