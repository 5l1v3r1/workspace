<?php
$con = new mysqli("localhost","admin","academy","ACADEMY");
if($con->connect_errno){
    printf("Connect failed: %s\n", $con->connect_error);
    exit();
}
function getClassRoom($con, $macaddr){
    $querycom = "SELECT NAME FROM CLASSROOM WHERE MACADDR='".$macaddr."'";
    if($results = $con->query($querycom)){
        $rows = $results->fetch_array(MYSQL_NUM);
        echo $rows[0];
    }
}
function printName($con, $uid){
    $querycom = "SELECT NAME FROM STULOG WHERE UID='".$uid."'";
    if($results = $con->query($querycom)){
        $rows = $results->fetch_array(MYSQL_NUM);
        echo $rows[0]."\n\r";
    }
}
function printLOG($con, $uid){
    $querycom = "SELECT DAY, CHECKIN, CHECKOUT, MACADDR FROM STULOG";
    $querycom = $querycom." WHERE UID='".$uid."'";
    $querycom = $querycom." ORDER BY DAY ASC, CHECKOUT ASC";
    if($results = $con->query($querycom)){
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"30%\">\n\r";
            echo "          ";
            echo $rows[0];
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"25%\">\n\r";
            echo "          ";
            echo $rows[1];
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"25%\">\n\r";
            echo "          ";
            echo $rows[2];
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"20%\">\n\r";
            echo "          ";
            getClassRoom($con, $rows[3]);
            echo "      ";
            echo "</td>\n\r";

            echo "  ";
            echo "</tr>\n\r";
        }
    }
}



function tableOpen(){
    echo "\n\r<table border=\"1\" width=\"100%\">\n\r";

    echo "  ";
    echo "<tr>\n\r";

    echo "      ";
    echo "<td align=\"center\" width=\"30%\">\n\r";
    echo "          ";
    echo "날짜";
    echo "      ";
    echo "</td>\n\r";

    echo "      ";
    echo "<td align=\"center\" width=\"25%\">\n\r";
    echo "          ";
    echo "등원";
    echo "      ";
    echo "</td>\n\r";

    echo "      ";
    echo "<td align=\"center\" width=\"25%\">\n\r";
    echo "          ";
    echo "하원";
    echo "      ";
    echo "</td>\n\r";

    echo "      ";
    echo "<td align=\"center\" width=\"20%\">\n\r";
    echo "          ";
    echo "강의실";
    echo "      ";
    echo "</td>\n\r";

    echo "  ";
    echo "</tr>\n\r";
}
function tableClose(){
    echo "\n\r";
    echo "</table>\n\r";
}

?>
