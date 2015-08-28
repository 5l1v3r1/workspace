<?php
    $name = $_GET['varname'];
    if(strcmp($name, "")==0){
        echo "<script>
            alert(\"이름을 입력해주십시오.\");
            document.location.href='./index.php';
            </script>";
    }
    include('function_nfc.php');
?>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>KETI Raspberry Pi</title>
</head>

<body align="center">
    <form name="mainform" method="get" action="nfc.php">
        <table border="0" align="center" height="90%" width="100%">
            <tr>
                <td align="center" valign="bottom" height="50%" width="100%">
                    <img src="./logo.JPG"></img>
                </td>
            </tr>
            <tr>
                <td align="center" valign="top" height="50%" width="50%" style="padding-top:15px;">
                    <?php echo $name."님 NFC카드를 읽혀주십시오."; ?>
                </td>
            </tr>
        </table>
    </form>
    <?php if(strcmp($name,"")!=0) register($name); ?>
</body>

</html>
