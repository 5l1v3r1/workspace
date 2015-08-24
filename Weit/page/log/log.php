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
?>

<table width="100%" border="0">
    <tr>
        <td width="10%" align="center">
            <font color="76b02a">이름&nbsp: </font>
        </td>
        <td width="10%" align="center">
            <?php
            if(strlen($key)>0)
                printName($con, $key);
            ?>
        </td>
        <td width="80%"/>
    </tr>
    <tr heigth="20px"><td>&nbsp</td></tr>
</table>

<table width="100%" border="0">
    <tr bgcolor="b6f06a">
        <td width="30%" align="center"> 날짜 </td>
        <td width="25%" align="center"> 등원 </td> 
        <td width="25%" align="center"> 하원 </td>
        <td width="20%" align="center"> 강의실 </td>
    <?php
    if(strlen($key) > 0)
        printLOG($con, $key);
    ?>

</table>

</body>
</html>
