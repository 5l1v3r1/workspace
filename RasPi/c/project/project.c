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

#define MY_SERVER_IP "169.254.241.122"
#define PORT_NUMBER 1126
#define MAXLINE 1024
#define STATE_REGIT 1011
#define STATE_READ 2022

char *UID;
int UID_LEN;
struct sockaddr_in serveraddr;
int server_sockfd;
int client_len;
char buf[MAXLINE];

static void
print_hex(const uint8_t *pbtData, const size_t szBytes);

void mgpark_nfc_read(int argc);

void
mgpark_read(nfc_modulation nmMifare, nfc_device *pnd, nfc_target nt);

void tcp_send();

int server_state();

int main(int argc, char **argv){
    mgpark_nfc_read(argc);
    return 0;
}

int server_state(){
}

void
tcp_send(){
    struct sockaddr_in serveraddr;
    int server_sockfd;
    int client_len;
    char buf[MAXLINE];

    if((server_sockfd = socket(AF_INET, SOCK_STREAM,0))==-1){
        perror("error : ");
        return;
    }
    serveraddr.sin_family = AF_INET;
    serveraddr.sin_addr.s_addr = inet_addr(MY_SERVER_IP);
    serveraddr.sin_port = htons(PORT_NUMBER);

    client_len = sizeof(serveraddr);

    if(connect(server_sockfd, (struct sockaddr *)&serveraddr, client_len) == -1){
        perror("coonect error : ");
        return;
    }
    printf("%s\n%d\n",UID,UID_LEN);
    if(write(server_sockfd, UID+1, UID_LEN) <= 0){
        perror("write error : ");
        return;
    }
    memset(buf, 0x00, MAXLINE);
    close(server_sockfd);
}

void
mgpark_read(nfc_modulation nmMifare, nfc_device *pnd, nfc_target nt){
    if(nfc_initiator_select_passive_target(pnd, nmMifare, NULL, 0, &nt) > 0){
        print_hex(nt.nti.nai.abtUid, nt.nti.nai.szUidLen);
        tcp_send();
        while(nfc_initiator_target_is_present(pnd,NULL)==0){}
    }
}

static void 
print_hex(const uint8_t *pbtData, const size_t szBytes)
{
  size_t  szPos;
  char str[(int)szBytes*2];
  char temp[2];

  UID = (char*)malloc(szBytes*2);

  for (szPos = 0; szPos < szBytes; szPos++) {
    sprintf(temp, "%02x", pbtData[szPos]);
    strcat(str,temp);
  }
  strcpy(UID,str);
  UID_LEN = (int)szBytes*2;
}

void mgpark_nfc_read(int argc)
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
      mgpark_read(nmMifare,pnd,nt);
  // Close NFC device
  nfc_close(pnd);
  // Release the context
  nfc_exit(context);
  exit(EXIT_SUCCESS);
}
