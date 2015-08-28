<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
</html>
<?php
function hue(){
}

function getuid(){
    set_time_limit(0);
    $address = '127.0.0.1';
    $port = 1126;
    $sock = socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
    socket_set_option($sock, SOL_SOCKET, SO_REUSEADDR, 1);
    if(($bind_result = socket_bind($sock, $address, $port))==0){
        echo "이미 사용중인 포트입니다.";
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
$con = new mysqli("127.0.0.1","keti512","tinyos","PEOPLE");
if($con->connect_errno){
    printf("Connect failed: %s\n", $con->connect_error);
    exit();
}
if($result = $con->query("SELECT * FROM PEOPLE WHERE UID='".$uid."'")){
    if($row = $result->fetch_array(MYSQL_NUM)){
        if(strcmp($row[1],"")){
            echo "<script>
                alert(\"".$row[1]."님 반갑습니다!\");
                </script>";
            hue();
        }
        else{
            echo "<script>
                alert(\"등록되지 않은 카드입니다. 등록 후 사용해주십시오.\");
                </script>";
        }
    }
}
echo "<script>
    document.location.href='index.php';
    </script>";
?>
