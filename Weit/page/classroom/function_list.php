<?php
$con = new mysqli("localhost","admin","academy","ACADEMY");
if($con->connect_errno){
    printf("Connect failed: %s\n", $con->connect_error);
    exit();
}

function searchAll($con){
    if($results = $con->query("SELECT MACADDR, NAME FROM CLASSROOM ORDER BY NAME ASC")){
        while($rows = $results->fetch_array(MYSQL_NUM)){
            echo "  ";
            echo "<tr>\n\r";

            echo "      ";
            echo "<td align=\"center\" width=\"100%\">\n\r";
            echo "          ";
            echo "<a href=\"manager.php?macaddr=";
            $macaddr = $rows[0];
            echo $macaddr."\"";
            //echo " target=\"iframe_name\"";
            echo ">\n\r";
            echo "              ";
            echo $rows[1];
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
