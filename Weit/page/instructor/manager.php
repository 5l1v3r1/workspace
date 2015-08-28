<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <style type = "text/css">
        a {text-decoration:none; color:black;}
        table {border-collapse:collapse;}
        td {font-size:95%;}
    </style>
</head>

<?php
include('function_manager.php');
$uid = addslashes($_GET['uid']);
?>

<body>

<script>
function deletebox(){
    var ok = confirm("정말 삭제하시겠습니까?");
    if(ok){
        alert("삭제되었습니다.");
    }
}
</script>

<form method="get" action="log.php" target="log">
<input name="uid" type="hidden" value="<?php echo $uid; ?>"/>
<table width="100%" border="0">
    <tr>
        <td width="15%" align="right">
            <font color="2b8b9b">이름 : </font>
        </td>

        <td width="23%" align="left">
            &nbsp;
            <?php printName($con, $uid); ?>
        </td>

        <td width="20%" align="right">
            <font color="2b8b9b">소속부서 : </font>
        </td>
        
        <td width="42%" align="left">
            &nbsp;
            <?php printDepart($con, $uid); ?>
        </td>
    </tr>

    <tr><td/></tr>

    <tr>
        <td width="15%" align="right">
            <font color="2b8b9b">연락처 : </font>
        </td>

        <td width="23%" align="left">
            &nbsp;
            <?php printPhone($con, $uid); ?>
        </td>

        <td width="20%" align="right">
            <font color="2b8b9b">E-mail : </font>
        </td>
        
        <td width="42%" align="left">
            &nbsp;
            <?php printEmail($con, $uid); ?>
        </td>
    </tr>

    <tr><td/></tr>

    <tr>
        <td width="80%" align="right" colspan="4" style="padding-right:40px">
        <?php
        if(!($uid=="")){
           echo "<input type=\"button\" value=\"수정\" onclick=\"deletebox()\" style=\"background-color:#b6f06a; border:0; width:43px; height:20px;\"/>\n\r";
           echo "<input type=\"button\" value=\"삭제\" onclick=\"deletebox()\" style=\"background-color:#b6f06a; border:0; width:43px; height:20px;\"/>\n\r";
        }
        else{
           echo "&nbsp;\n\r";
        }
        ?>
        </td>
    </tr>
    
    <tr><td/></tr>

    <tr>
        <td width="20%" align="right">
            <font color="2b8b9b">출퇴근기록 : </font>
        </td>

        <td width="10%" valign="buttom" align="right" style="padding-right:10px">
            &nbsp;
            <select name="yy" height="100%">
                <?php printYear() ?>
            </select>
            <select name="mm" height="100%">
                <?php printMonth() ?>
            </select>
        </td>
        <td width="80%" align="left" colspan="2">
            <input name="submit" type="image" src="../images/button.gif" height="100%"/>
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
