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
include('function_log.php');
$key = addslashes($_GET['uid']);
$yy = addslashes($_GET['yy']);
$mm = addslashes($_GET['mm']);
?>

<table width="100%" border="0">
    <tr>
        <td colspan="3" align="right" style="padding-right:30px">
            <font color="2b8b9b"> 총 근무시간 : </font>
        </td>
        <td> <?php printTotal($con, $key, $yy, $mm); ?> 
        </td>
    <tr bgcolor="2b8b9b">
        <td width="30%" align="center"> 날짜 </td>
        <td width="25%" align="center"> 등원 </td> 
        <td width="25%" align="center"> 하원 </td>
        <td width="20%" align="center"> 근무시간 </td>
    </tr>
    <?php
    if(strlen($key) > 0)
        printLOG($con, $key, $yy, $mm);
    ?>

</table>

</body>
</html>
