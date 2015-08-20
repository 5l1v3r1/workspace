<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <style type = "text/css">
        a {text-decoration:none; color:black;}
        table {border-collapse:collapse;}
    </style>
</head>
<body>
<?php
include('studentsLogFunction.php');
$key = addslashes($_GET['uid']);
if(strlen($key) > 0){
    printName($con, $key);
    tableOpen();
    printLOG($con, $key);
    tableClose();
}
?>
</body>
</html>
