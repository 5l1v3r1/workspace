#include <stdio.h>
#include <unistd.h>
#include <errno.h>
#include <ifaddrs.h>
#include <netdb.h>
#include <sys/socket.h>

int main()
{
 // get host name
 char hostname[256];
 size_t hostnamelen = 256;
 if (gethostname(hostname, hostnamelen) == -1)
 {
  printf("can't read host name\n");
 }
 else
 {
  printf("%s\n", hostname);
 }

 // get ip address
 struct ifaddrs *ifp = NULL;
 if (getifaddrs(&ifp) < 0)
 {
  printf("getifaddrs() fail\n");
  return -1;
 }
 struct ifaddrs *ifa = NULL;
 for (ifa=ifp; ifa; ifa = ifa->ifa_next)
 {
  socklen_t salen;
  char ip[125];
  if (ifa->ifa_addr->sa_family == AF_INET)
  {
   salen = sizeof(struct sockaddr_in);
  }
  if (getnameinfo(ifa->ifa_addr, salen, ip, sizeof(ip), NULL, 0, NI_NUMERICHOST) < 0)
  {
   continue;
  }
  printf("ip : %s\n", ip);
 }
 return 0;
}
