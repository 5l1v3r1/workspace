<?php
$con = new mysqli("localhost","admin","academy","ACADEMY");
if($con->connect_errno){
    printf("Connect failed: %s\n", $con->connect_error);
    exit();
}

function printLOG($con, $uid, $yy, $mm){
    $querycom = "SELECT DAY, CHECKIN, CHECKOUT, WORKTIME FROM INSTLOG";
    $querycom = $querycom." WHERE UID='".$uid."' AND";
    $querycom = $querycom." YEAR(DAY)='".$yy."' AND MONTH(DAY)='".$mm."'";
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
            echo $rows[3];
            echo "      ";
            echo "</td>\n\r";

            echo "  ";
            echo "</tr>\n\r";
        }
    }
}
?>
