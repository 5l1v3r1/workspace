<?php
$con = new mysqli("localhost","admin","academy","ACADEMY");
if($con->connect_errno){
    printf("Connect failed: %s\n", $con->connect_error);
    exit();
}

function searchAll($con){
    if($results = $con->query("SELECT NAME, PHONE, SUBJECT, UID FROM STUDENT ORDER BY NAME ASC")){
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"25%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            $uid = $rows[3];
            echo $uid."\"";
            echo " target=\"log\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[0];
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"40%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            echo $uid."\"";
            echo " target=\"log\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[1];
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"35%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            echo $uid."\"";
            echo " target=\"log\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[2];
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

function searchName($con, $name){
    if($results = $con->query("SELECT NAME, PHONE, SUBJECT, UID FROM STUDENT WHERE NAME LIKE '%".$name."%' ORDER BY NAME ASC")){
        
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"25%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            $uid = $rows[3];
            echo $uid."\"";
            echo " target=\"log\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[0];
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"40%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            echo $uid."\"";
            echo " target=\"log\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[1];
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"35%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            echo $uid."\"";
            echo " target=\"log\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[2];
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

function searchPhone($con, $phone){
    $querycom = "SELECT NAME, PHONE, SUBJECT, UID FROM STUDENT WHERE PHONE LIKE '%".$phone."%' OR PARENT LIKE '%".$phone."%' ORDER BY NAME ASC";
    if($results = $con->query($querycom)){
        
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"25%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            $uid = $rows[3];
            echo $uid."\"";
            echo " target=\"log\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[0];
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"40%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            echo $uid."\"";
            echo " target=\"log\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[1];
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"35%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            echo $uid."\"";
            echo " target=\"log\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[2];
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

function searchSubject($con, $subject){
    $querycom = "SELECT NAME, PHONE, SUBJECT, UID FROM STUDENT WHERE SUBJECT LIKE '%".$subject."%' ORDER BY NAME ASC";
    if($results = $con->query($querycom)){
        
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"25%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            $uid = $rows[3];
            echo $uid."\"";
            echo " target=\"log\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[0];
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"40%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            echo $uid."\"";
            echo " target=\"log\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[1];
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"35%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            echo $uid."\"";
            echo " target=\"log\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[2];
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
?>
