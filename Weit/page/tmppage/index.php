<html>

<head>

    <script>
        var state = true;
        var name = "";
        function read(uid){
            if(state){
                document.location.href='./read.php?uid='+uid;
            }
            else{
                document.location.href='./register.php?uid='+uid;
                state = true;
                name = "";
            }
        }
        function stateRegit(){
            name = document.getElementById('varname').value;
            if(name == ""){
                alert("이름을 입력해주십시오.");
            }
            else{
                alert("NFC카드를 읽혀주십시오.");
                document.location.href='register.php?name='+name;
            }
        }
        function stateRead(){
            alert("NFC카드를 읽혀주십시오");
            document.location.href='read.php';
        }
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>KETI Raspberry Pi</title>

</head>

<body>
    <form name="mainform">
        <table border="0" align="center" height="90%" width="100%">
            <tr>
                <td align="center" valign="bottom" height="50%" width="100%">
                    <img src="./logo.JPG"></img>
                </td>
            </tr>
            <tr>
                <td align="center" valign="top" height="50%" width="50%" style="padding-top:15px;">
                    <input id="varname" name="varname" type="textfield" size="10" style="height:9%;"/>
                    <input name="button" type="button" value="&#xB4F1;&#xB85D;" style="height:9%;border:0px;background-color:#025696;color:white;" onclick="stateRegit()" />
                    <input name="button" type="button" value="검색" style="height:9%;border:0px;background-color:#025696;color:white;" onclick="stateRead()" />
                </td>
            </tr>
        </table>
    </form>
</body>

</html>
