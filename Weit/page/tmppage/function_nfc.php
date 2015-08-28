<?php
function getuid(){
    $uid = shell_exec('getuid');
    return $uid;
}
function insertdb($uid, $name){
    $con = new mysqli("127.0.0.1","keti512","tinyos","PEOPLE");
    if($con->connect_errno){
        printf("Connect failed: %s\n", $con->connect_error);
        exit();
    }
    if($result = $con->query("SELECT * FROM PEOPLE WHERE UID='".$uid."'")){
        if($row = $result->fetch_array(MYSQL_NUM)){
            if(strcmp($row,"")==0){
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
function register($name){
    $uid = getuid();
    
    $result = insertdb($uid, $name);

    if(!strcmp($result,"success")){
        echo "<script>
            alert(\"등록에 성공하였습니다.\");
            document.location.href='./index.php'
            </script>";
    }
    else if(!strcmp($result,"already")){
        echo "<script>
            alert(\"이미 등록된 카드입니다.\");
            document.location.href='./index.php'
            </script>";
    }
    else{
        echo "<script>
            alert(\"등록에 싶패하였습니다.\");
            document.location.href='./index.php'
            </script>";
    }
}
?>
