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
    $querycom = "SELECT * FROM STUDENT WHERE UID='".$uid."'";
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
function printBirth(){
    global $row;
    if($row==null)
        echo "";
    else
        echo $row[2];
}
function printSubject(){
    global $row;
    if($row==null)
        echo "";
    else
        echo $row[3];
}
function printStart(){
    global $row;
    if($row==null)
        echo "";
    else
        echo $row[4];
}
function printEnd(){
    global $row;
    if($row==null)
        echo "";
    else
        echo $row[5];
}
function printPhone(){
    global $row;
    if($row==null)
        echo "";
    else
        echo $row[6];
}
function printParent(){
    global $row;
    if($row==null)
        echo "";
    else if($row[7] != null || strlen($row[7]) > 0)
        echo $row[7];
    else
        echo "";
}
function printNote(){
    global $row;
    if($row==null)
        echo "";
    else if($row[8] != null || strlen($row[8] > 0)
        echo $row[8];
    else
        echo "";
}
?>
