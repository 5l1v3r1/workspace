<?php
$con = new mysqli("localhost","admin","academy","ACADEMY");
if($con->connect_errno){
    exit();
}
function printName($con, $uid){
    if(strlen($uid)==0){
        return ;
    }
    $querycom = "SELECT NAME FROM INSTRUCTOR WHERE UID='".$uid."'";
    if($result = $con->query($querycom)){
        if($row = $result->fetch_array(MYSQL_NUM)){
            echo $row[0];
        }
    }
}

function printDepart($con, $uid){
    if(strlen($uid)==0){
        return ;
    }
    $querycom = "SELECT DEPART FROM INSTRUCTOR WHERE UID='".$uid."'";
    if($result = $con->query($querycom)){
        if($row = $result->fetch_array(MYSQL_NUM)){
            echo $row[0];
        }
    }
}
function printEmail($con, $uid){
    if(strlen($uid)==0){
        return ;
    }
    $querycom = "SELECT EMAIL FROM INSTRUCTOR WHERE UID='".$uid."'";
    if($result = $con->query($querycom)){
        if($row = $result->fetch_array(MYSQL_NUM)){
            echo $row[0];
        }
    }
}
function printPhone($con, $uid){
    if(strlen($uid)==0){
        return ;
    }
    $querycom = "SELECT PHONE FROM INSTRUCTOR WHERE UID='".$uid."'";
    if($result = $con->query($querycom)){
        if($row = $result->fetch_array(MYSQL_NUM)){
            echo $row[0];
        }
    }
}
function printTotal($con, $uid, $yy, $mm){
    $querycom = "SELECT SUM(WORKTIME) FROM INSTLOG";
    $querycom = $querycom." WHERE UID='".$uid."'";
    $querycom = $querycom." AND YEAR(DAY)=".$yy." AND MONTH(DAY)=".$mm;
    if($result = $con->query($querycom)){
        if($rows = $result->fetch_array(MYSQL_NUM)){
            echo $rows[0];
        }
    }
}

function printYear(){
    $yy = date("Y");
    for($i=2010; $i<=$yy+5; $i++){
        if($i == $yy)
            echo "<option value=\"".$i."\" selected=\"selected\">";
        else
            echo "<option value=\"".$i."\">";
        echo $i;
        echo "</option>\n\r";
    }
}

function printMonth(){
    $mm = date("n");
    for($i=1; $i<=12; $i++){
        if($i == $mm)
            printf("<option value=\"%02d\" selected=\"selected\">",$i);
        else
           printf("<option value=\"%02d\">",$i);
        printf("%02d",$i);
        printf("</option>\n\r");
    }
}

?>
