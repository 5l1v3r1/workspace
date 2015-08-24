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
include('function_manager.php');
$uid = addslashes($_GET['uid']);
?>
<form method="get" action="log.php" target="log">
<input name="uid" type="hidden" value="<?php echo $uid; ?>"/>
<table width="100%" border="0">
    <tr>
        <td width="15%" align="right">
            <font color="76b02a">이름 : </font>
        </td>

        <td width="23%" align="left">
            &nbsp;
            <?php printName($con, $uid); ?>
        </td>

        <td width="20%" align="right">
            <font color="76b02a">소속부서 : </font>
        </td>
        
        <td width="42%" align="left">
            &nbsp;
            <?php printDepart($con, $uid); ?>
        </td>
    </tr>

    <tr><td/></tr>

    <tr>
        <td width="15%" align="right">
            <font color="76b02a">연락처 : </font>
        </td>

        <td width="23%" align="left">
            &nbsp;
            <?php printPhone($con, $uid); ?>
        </td>

        <td width="20%" align="right">
            <font color="76b02a">E-mail : </font>
        </td>
        
        <td width="42%" align="left">
            &nbsp;
            <?php printEmail($con, $uid); ?>
        </td>
    </tr>

    <tr><td/></tr>

    <tr>
        <td width="80%" align="right" colspan="4" style="padding-right:40px">
            수정버튼
        </td>
    </tr>
    
    <tr><td/></tr>

    <tr>
        <td width="20%" align="right">
            <font color="76b02a">출퇴근기록 : </font>
        </td>

        <td width="20%" align="left" colspan="3">
            &nbsp;
            <select name="yy">
                <?php printYear() ?>
            </select>
            <select name="mm">
                <?php printMonth() ?>
            </select>
            <input name="submit" type="submit" value="검색" style="background-color:#b6f06a; border:0; width:43px; height:20px;"/>
        </td>
    </tr>

    <tr><td/></tr>
    
    <tr>
        <td width="100%" colspan="4">
            <iframe name="log" width="100%" height="350" src="
            <?php echo "log.php?uid=".$uid."&yy=".date("Y")."&mm=".date("m"); ?> " frameborder="0">
            </iframe>
        </td>
    </tr>

</table>
</form>
</body>
</html>
