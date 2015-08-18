// To compile this simple example:
// $ gcc -o quick_start_example1 quick_start_example1.c -lnfc

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <nfc/nfc.h>

#include <sys/socket.h>
#include <arpa/inet.h>
#include <sys/stat.h>
#include <unistd.h>

#define PORT_NUMBER 1126
#define MAXLINE 1024
#define STATE_REGIT 1011
#define STATE_READ 2022

char *UID;
int UID_LEN;
int UID_SHIFT;
char *macaddr;

struct sockaddr_in serveraddr;
int server_sockfd;
int client_len;
char buf[MAXLINE];

static void
print_hex(const uint8_t *pbtData, const size_t szBytes);

void mgpark_nfc_read(int argc, char *ip);

void
mgpark_read(nfc_modulation nmMifare, nfc_device *pnd, nfc_target nt, char *ip);

void tcp_send(char *ip);

int main(int argc, char **argv){
    if(argc < 2)
        printf("input ip");
    else if(argc < 3)
        printf("input macaddr");
    else{
        macaddr = argv[2];
        printf("mac address : %s\n",macaddr);
        mgpark_nfc_read(argc,argv[1]);
    }
    return 0;
}

void
tcp_send(char *ip){
    struct sockaddr_in serveraddr;
    int server_sockfd;
    int client_len;
    char buf[MAXLINE];

    if((server_sockfd = socket(AF_INET, SOCK_STREAM,0))==-1){
        perror("error : ");
        return;
    }
    serveraddr.sin_family = AF_INET;
    serveraddr.sin_addr.s_addr = inet_addr(ip);
    serveraddr.sin_port = htons(PORT_NUMBER);

    client_len = sizeof(serveraddr);

    if(connect(server_sockfd, (struct sockaddr *)&serveraddr, client_len) == -1){
        perror("conect error : ");
        return;
    }
    printf("%s\n%d\n",UID+UID_SHIFT,UID_LEN);
    if(send(server_sockfd, UID+UID_SHIFT, UID_LEN, 0) <= 0){
        perror("send error : ");
        return;
    }
    if(send(server_sockfd, "\n", 1, 0) <= 0){
        perror("send error : ");
        return;
    }
    if(send(server_sockfd, macaddr, strlen(macaddr), 0) <= 0){
        perror("send error : ");
        return;
    }
    /*if(write(server_sockfd, UID+UID_SHIFT, UID_LEN) <= 0){
        perror("write error : ");
        return;
    }*/
    memset(buf, 0x00, MAXLINE);
    close(server_sockfd);
    free(UID);
}

void
mgpark_read(nfc_modulation nmMifare, nfc_device *pnd, nfc_target nt, char *ip){
    if(nfc_initiator_select_passive_target(pnd, nmMifare, NULL, 0, &nt) > 0){
        print_hex(nt.nti.nai.abtUid, nt.nti.nai.szUidLen);
        tcp_send(ip);
        while(nfc_initiator_target_is_present(pnd,NULL)==0){}
    }
}

static void 
print_hex(const uint8_t *pbtData, const size_t szBytes)
{
  size_t  szPos;
  char str[(int)szBytes*2];
  char temp[2];

  UID = (char*)malloc(szBytes*20);

  for (szPos = 0; szPos < szBytes; szPos++) {
    sprintf(temp, "%02x", pbtData[szPos]);
    strcat(str,temp);
  }
  strcpy(UID,str);
  UID_SHIFT = strlen(UID) - (int)szBytes*2;
  UID_LEN = strlen(UID+UID_SHIFT);
}

void mgpark_nfc_read(int argc, char *ip)
{
  nfc_device *pnd;
  nfc_target nt;

  // Allocate only a pointer to nfc_context
  nfc_context *context;

  // Initialize libnfc and set the nfc_context
  nfc_init(&context);
  if (context == NULL) {
    exit(EXIT_FAILURE);
  }

  // Display libnfc version
  const char *acLibnfcVersion = nfc_version();
  (void)argc;

  // Open, using the first available NFC device which can be in order of selection:
  //   - default device specified using environment variable or
  //   - first specified device in libnfc.conf (/etc/nfc) or
  //   - first specified device in device-configuration directory (/etc/nfc/devices.d) or
  //   - first auto-detected (if feature is not disabled in libnfc.conf) device
  pnd = nfc_open(context, NULL);

  if (pnd == NULL) {
    exit(EXIT_FAILURE);
  }
  // Set opened NFC device to initiator mode
  if (nfc_initiator_init(pnd) < 0) {
    nfc_perror(pnd, "nfc_initiator_init");
    exit(EXIT_FAILURE);
  }


  // Poll for a ISO14443A (MIFARE) tag
  nfc_modulation nmMifare;
  nmMifare.nmt = NMT_ISO14443A;
  nmMifare.nbr = NBR_106;

  while(1)
      mgpark_read(nmMifare,pnd,nt,ip);
  // Close NFC device
  nfc_close(pnd);
  // Release the context
  nfc_exit(context);
  exit(EXIT_SUCCESS);
}
