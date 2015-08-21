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
function printTotal($uid, $yy, $mm){
    global $con;
    $querycom = "SELECT SUM(WORKTIME) FROM INSTLOG";
    $querycom = $querycom." WHERE UID='".$uid."'";
    $querycom = $querycom." AND YEAR(DAY)=".$yy." AND MONTH(DAY)=".$mm;
    if($result = $con->query($querycom)){
        if($rows = $result->fetch_array(MYSQL_NUM)){
            echo $rows[0];
        }
    }
}
function printLog($uid, $yy, $mm){
    global $con;
    $querycom = "SELECT DAY, CHECKIN, CHECKOUT, WORKTIME FROM INSTLOG";
    $querycom = $querycom." WHERE UID='".$uid."'";
    $querycom = $querycom." AND YEAR(DAY)=".$yy." AND MONTH(DAY)=".$mm;
    if($result = $con->query($querycom)){
        while($rows = $result->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";
            echo "      ";
            echo "<td align=\"center\" width=\"30%\">\n\r";
            echo "          ";
            echo $rows[0]."\n\r";
            echo "      ";
            echo "</td>\n\r";
            echo "  ";
            echo "</tr>\n\r";

            echo "  ";
            echo "<tr>\n\r";
            echo "      ";
            echo "<td align=\"center\" width=\"30%\">\n\r";
            echo "          ";
            echo $rows[1]."\n\r";
            echo "      ";
            echo "</td>\n\r";
            echo "  ";
            echo "</tr>\n\r";

            echo "  ";
            echo "<tr>\n\r";
            echo "      ";
            echo "<td align=\"center\" width=\"30%\">\n\r";
            echo "          ";
            echo $rows[2]."\n\r";
            echo "      ";
            echo "</td>\n\r";
            echo "  ";
            echo "</tr>\n\r";

            echo "  ";
            echo "<tr>\n\r";
            echo "      ";
            echo "<td align=\"center\" width=\"10%\">\n\r";
            echo "          ";
            echo $rows[3]."\n\r";
            echo "      ";
            echo "</td>\n\r";
            echo "  ";
            echo "</tr>\n\r";
        }
    }
}
?>
