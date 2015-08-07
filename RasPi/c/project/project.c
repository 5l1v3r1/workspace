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

char *UID;

static void
print_hex(const uint8_t *pbtData, const size_t szBytes);

void
mgpark_nfc_read(int argc);

void
mgpark_read(nfc_modulation nmMifare, nfc_device *pnd, nfc_target nt);

int main(int argc, char **argv){
    mgpark_nfc_read(argc);
    return 0;
}

void
mgpark_read(nfc_modulation nmMifare, nfc_device *pnd, nfc_target nt){
    if(nfc_initiator_select_passive_target(pnd, nmMifare, NULL, 0, &nt) > 0){
        print_hex(nt.nti.nai.abtUid, nt.nti.nai.szUidLen);
        printf("%s\n",UID);
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

  /*if (nfc_initiator_select_passive_target(pnd, nmMifare, NULL, 0, &nt) > 0){
    print_hex(nt.nti.nai.abtUid, nt.nti.nai.szUidLen);
    printf("%s\n",UID);
    while(nfc_initiator_target_is_present(pnd, NULL)==0){}
  }*/
  mgpark_read(nmMifare,pnd,nt);
  // Close NFC device
  nfc_close(pnd);
  // Release the context
  nfc_exit(context);
  exit(EXIT_SUCCESS);
}
