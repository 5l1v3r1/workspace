<?php
$con = new mysqli("localhost","admin","academy","ACADEMY");
if($con->connect_errno){
    printf("Connect failed: %s\n", $con->connect_error);
    exit();
}
$row = null;
function search($con, $uid){
    global $row;

    if(strlen($uid)==0)
        return;
    $querycom = "SELECT * FROM INSTRUCTOR WHERE UID='".$uid."'";
    if($results = $con->query($querycom)){
        $row = results->fetch_array(MYSQL_NUM);
    }
    else{
        return;
    }
}
function printName(){
    global $row;

    if($row==null)
        echo "";
    else
        echo $row[1];
}
function printDepart(){
    global $row;
    if($row==null)
        echo "";
    else
        echo $row[2];
}
function printPhone(){
    global $row;
    if($row==null)
        echo "";
    else
        echo $row[3];
}
function printEmail(){
    global $row;
    if($row==null)
        echo "";
    else
        echo $row[4];
}
function printLog($yy, $mm){
    global $con;
    $querycom = "SELECT DAY, CHECKIN, CHECKOUT, WORKTIME";
    $querycom = $querycom." WHERE YEAR(DAY)=".$yy." AND MONTH(DAY)=".$mm;
    if($result=$con->query($querycom)){
        while
    }
}
?>
