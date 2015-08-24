<?php
$con = new mysqli("localhost","admin","academy","ACADEMY");
if($con->connect_errno){
    printf("Connect failed: %s\n", $con->connect_error);
    exit();
}

function searchAll($con){
    if($results = $con->query("SELECT NAME, PHONE, DEPART, UID FROM INSTRUCTOR ORDER BY NAME ASC")){
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"25%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            $uid = $rows[3];
            echo $uid."\"";
            echo " target=\"manager\"";
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
            echo " target=\"manager\"";
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
            echo " target=\"manager\"";
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
    if($results = $con->query("SELECT NAME, PHONE, DEPART, UID FROM INSTRUCTOR WHERE NAME LIKE '%".$name."%' ORDER BY NAME ASC")){
        
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"25%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            $uid = $rows[3];
            echo $uid."\"";
            echo " target=\"manager\"";
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
            echo " target=\"manager\"";
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
            echo " target=\"manager\"";
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
    if($results = $con->query("SELECT NAME, PHONE, DEPART, UID FROM INSTRUCTOR WHERE PHONE LIKE '%".$phone."%' ORDER BY NAME ASC")){
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"25%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            $uid = $rows[3];
            echo $uid."\"";
            echo " target=\"manager\"";
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
            echo " target=\"manager\"";
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
            echo " target=\"manager\"";
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

function searchDepart($con, $depart){
    if($results = $con->query("SELECT NAME, PHONE, DEPART, UID FROM INSTRUCTOR WHERE DEPART LIKE '%".$depart."%' ORDER BY NAME ASC")){
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"25%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            $uid = $rows[3];
            echo $uid."\"";
            echo " target=\"manager\"";
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
            echo " target=\"manager\"";
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
            echo " target=\"manager\"";
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
