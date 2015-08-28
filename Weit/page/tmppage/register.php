<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<?php
function insertdb($uid, $name){
    $con = new mysqli("127.0.0.1","keti512","tinyos","PEOPLE");
    if($con->connect_errno){
        printf("Connect failed: %s\n", $con->connect_error);
        exit();
    }
    if($result = $con->query("SELECT * FROM PEOPLE WHERE UID='".$uid."'")){
        if($row = $result->fetch_array(MYSQL_NUM)){
            if(strcmp($row[0],"")==0){
                if($con->query("INSERT INTO PEOPLE VALUES('".$uid."', '".$name."')")){
                return "success";
                }
                else{
                    return "fail";
                }
            }
            else{
                return "already";
            }
        }
        else{
            if($con->query("INSERT INTO PEOPLE VALUES('".$uid."', '".$name."')")){
            return "success";
            }
            else{
                return "fail";
            }
        }
    }
}
function getuid(){
    set_time_limit(0);
    $address = '127.0.0.1';
    $port = 1126;
    $sock = socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
    socket_set_option($sock, SOL_SOCKET, SO_REUSEADDR, 1);
    if(($bind_result = socket_bind($sock, $address, $port))==0){
        echo "이미 포트를 사용중입니다.";
        exit;
    }
    $listen_result = socket_listen($sock);
    $accept_result = socket_accept($sock);
    $uid = socket_read($accept_result,128);

    socket_close($accept_result);
    socket_close($sock);

    return $uid;
}

$uid = getuid();
$name = $_GET['name'];
$result = insertdb($uid, $name);
if(!strcmp($result,"success")){
    echo "<script>
        alert(\"등록에 성공하였습니다.\");
        </script>";
}
else if(!strcmp($result,"already")){
    echo "<script>
        alert(\"이미 존재하는 카드입니다.\");
        </script>";
}
else{
    echo "<script>
        alert(\"등록에 실패하였습니다.\");
        </script>";
}
echo "<script>
    document.location.href='index.php';
    </script>";
?>
