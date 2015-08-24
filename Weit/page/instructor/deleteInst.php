<?php
$con = new mysqli("localhost","admin","academy","ACADEMY");
if($con->connect_errno){
    exit();
}
function deleteInst($con, $uid){
    $querycom = "DELETE FROM INSTRUCTOR WHERE UID='".$uid."'";
    $con->query($querycom);
    $querycom = "DELETE FROM INSTLOG WHERE UID='".$uid."'";
    $con->query($querycom);
    $querycom = "DELETE FROM UID WHERE UID='".$uid."'";
    $con->query($querycom);
}
$uid = $GLOBALS['HTTP_RAW_POST_DATA'];
deleteInst($con, $uid);
?>
