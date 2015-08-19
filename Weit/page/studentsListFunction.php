<?php
$con = new mysqli("localhost","admin","academy","ACADEMY");
if($con->connect_errno){
    printf("Connect failed: %s\n", $con->connect_error);
    exit();
}

function searchAll($con){
    if($results = $con->query("SELECT NAME, PHONE, SUBJECT FROM STUDENT")){
        echo "\n\r<table border=\"1\" width=\"100%\">\n\r";
        
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"20%\">\n\r";
            echo "          ";
            echo "<a href=\"view.php?uid=";
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
            echo "<a href=\"view.php?uid=";
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
            echo "<a href=\"view.php?uid=";
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
        echo "</table>\n\r";
    }
}

function searchName($con, $name){
    if($results = $con->query("SELECT NAME, PHONE, SUBJECT FROM STUDENT WHERE NAME LIKE '%".$name."%'")){
        echo "\n\r<table border=\"1\" width=\"100%\">\n\r";
        
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"20%\">\n\r";
            echo "          ";
            echo "<a href=\"view.php?uid=";
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
            echo "<a href=\"view.php?uid=";
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
            echo "<a href=\"view.php?uid=";
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
        echo "</table>\n\r";
    }
}

function searchPhone($con, $phone){
    $querycom = "SELECT NAME, PHONE, SUBJECT FROM STUDENT WHERE PHONE LIKE '%".$phone."%' OR PARENT LIKE '%".$phone."%'";
    if($results = $con->query($querycom)){
        echo "\n\r<table border=\"1\" width=\"100%\">\n\r";
        
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"20%\">\n\r";
            echo "          ";
            echo "<a href=\"view.php?uid=";
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
            echo "<a href=\"view.php?uid=";
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
            echo "<a href=\"view.php?uid=";
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
        echo "</table>\n\r";
    }
}

function searchSubject($con, $subject){
    $querycom = "SELECT NAME, PHONE, SUBJECT FROM STUDENT WHERE SUBJECT LIKE '%".$subject."%'";
    if($results = $con->query($querycom)){
        echo "\n\r<table border=\"1\" width=\"100%\">\n\r";
        
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"20%\">\n\r";
            echo "          ";
            echo "<a href=\"view.php?uid=";
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
            echo "<a href=\"view.php?uid=";
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
            echo "<a href=\"view.php?uid=";
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
        echo "</table>\n\r";
    }
}
?>
