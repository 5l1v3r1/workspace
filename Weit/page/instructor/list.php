<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <style type = "text/css">
        a {text-decoration:none; color:black;}
        table {border-collapse:collapse;}
        td {font-size:90%;}
    </style>
</head>

<body>

<table border="0" width="100%" cellspacing="0">
    <tr bgcolor="#b6f06a">
        <td width="25%" align="center">이름</td>
        <td width="40%" align="center">전화번호</td>
        <td width="35%" align="center">소속부서</td>
    </tr>
<?php
include('function_list.php');
$key = addslashes($_GET['key']);
$search = addslashes($_GET['search']);
if(strlen($search) > 0){
    if(!strcmp($key, 'name')){
        searchName($con, $search);
    }
    else if(!strcmp($key, 'phone')){
        searchPhone($con, $search);
    }
    else if(!strcmp($key, 'depart')){
        searchDepart($con, $search);
    }
}
else{
    searchAll($con);
}
?>

</table>

</body>
</html>
