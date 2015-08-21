<?php
$con = new mysqli("localhost","admin","academy","ACADEMY");
if($con->connect_errno){
    printf("Connect failed: %s\n", $con->connect_error);
    exit();
}
function getSubject($con, $uid){
    $querycom = "SELECT SUBJECT FROM STUDENT WHERE UID='".$uid."'";
    if($results = $con->query($querycom){
        if($row = $results->fetch_array(MYSQL_NUM)){
            return $row[0];
        }
    }
}
function getLink($con, $subject){
    $querycom = "SELECT LINK FROM SUBEJCT WHERE NAME='".$subject."'";
    if($results = $con->query($querycom){
        if($row = $results->fetch_array(MYSQL_NUM)){
            return $row[0];
        }
    }
}
function searchAll($con, $macaddr){
    $querycom = "SELECT UID, NAME, CHECKIN  FROM STULOG";
    $querycom = $querycom." WHERE MACADDR='".$macaddr."' AND";
    $querycom = $querycom." DAY='".date("Y-m-d")."' AND";
    $querycom = $querycom." CHECKOUT IS NULL ORDER BY NAME ASC";
    if($results = $con->query($querycom)){
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"30%\">\n\r";
            echo "          ";
            echo $rows[1];
            echo "\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"40%\">\n\r";
            echo "          ";
            echo $rows[2];
            echo "\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"30%\">\n\r";
            echo "          ";
            echo "<a href=\"";
            $subject = getSubject($con, $uid);
            $link = getLink($con, $subject); 
            echo $link."\"";
            echo " target=\"_blank\"";
            echo ">\n\r";
            echo "              ";
            echo $subject;
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
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
    echo "<td align=\"center\" width=\"20%\">\n\r";

    echo "          ";
    echo "이름";

    echo "      ";
    echo "</td>\n\r";

    echo "      ";
    echo "<td align=\"center\" width=\"50%\">\n\r";

    echo "          ";
    echo "전화번호";

    echo "      ";
    echo "</td>\n\r";

    echo "      ";
    echo "<td align=\"center\" width=\"30%\">\n\r";

    echo "          ";
    echo "수강과목";

    echo "      ";
    echo "</td>\n\r";

    echo "  ";
    echo "</tr>\n\r";
}
function tableClose(){
    echo "</table>\n\r\n\r";
}

?>
