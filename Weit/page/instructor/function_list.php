<?php
$con = new mysqli("localhost","admin","academy","ACADEMY");
if($con->connect_errno){
    printf("Connect failed: %s\n", $con->connect_error);
    exit();
}

function searchAll($con){
    if($results = $con->query("SELECT NAME, PHONE, DEPART, UID FROM INSTRUCTOR")){
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"20%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            $uid = $rows[3];
            echo $uid."\"";
            //echo " target=\"iframe_name\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[0];
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"50%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            echo $uid."\"";
            //echo " target=\"iframe_name\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[1];
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"30%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            echo $uid."\"";
            //echo " target=\"iframe_name\"";
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
    if($results = $con->query("SELECT NAME, PHONE, DEPART, UID FROM INSTRUCTOR WHERE NAME LIKE '%".$name."%'")){
        
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"20%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            $uid = $rows[3];
            echo $uid."\"";
            //echo " target=\"iframe_name\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[0];
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"50%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            echo $uid."\"";
            //echo " target=\"iframe_name\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[1];
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"30%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            echo $uid."\"";
            //echo " target=\"iframe_name\"";
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
    $querycom = "SELECT NAME, PHONE, DEPART, UID FROM INSTRUCTOR WHERE PHONE LIKE '%".$phone."%' OR PARENT LIKE '%".$phone."%'";
    if($results = $con->query($querycom)){
        
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"20%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            $uid = $rows[3];
            echo $uid."\"";
            //echo " target=\"iframe_name\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[0];
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"50%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            echo $uid."\"";
            //echo " target=\"iframe_name\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[1];
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"30%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            echo $uid."\"";
            //echo " target=\"iframe_name\"";
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
    $querycom = "SELECT NAME, PHONE, DEPART, UID FROM INSTRUCTOR WHERE DEPART LIKE '%".$depart."%'";
    if($results = $con->query($querycom)){
        
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"20%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            $uid = $rows[3];
            echo $uid."\"";
            //echo " target=\"iframe_name\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[0];
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"50%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            echo $uid."\"";
            //echo " target=\"iframe_name\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[1];
            echo "\n\r";
            echo "          ";
            echo "</a>\n\r";
            echo "      ";
            echo "</td>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"30%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?uid=";
            echo $uid."\"";
            //echo " target=\"iframe_name\"";
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
    echo "소속부서";

    echo "      ";
    echo "</td>\n\r";

    echo "  ";
    echo "</tr>\n\r";
}
function tableClose(){
    echo "</table>\n\r\n\r";
}

?>
