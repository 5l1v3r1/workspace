<?php
$con = new mysqli("127.0.0.1","keti512","tinyos","PEOPLE");
if($con->connect_errno){
    printf("Connect failed: %s\n", $con->connect_error);
    exit();
}

function getuid(){
    $uid = shell_exec('getuid');
    return $uid;
}

function getname($con, $uid){
    if($result = $con->query("SELECT * FROM PEOPLE WHERE UID='".$uid."'")){
        if($row = $result->fetch_array(MYSQL_NUM)){
			if(strcmp($row,"")!=0){
				echo "<script>
					alert(\"".$row[0]."&#xB2D8; &#xBC18;&#xAC11;&#xC2B5;&#xB2C8;&#xB2E4;&#x0021;\");
					</script>";
				hue();
			}
		}
    }
}

function hue(){
	
}

function run($con){
	while(true){
		$uid = getuid();
		getname($con, $uid);
	}
}
?>
