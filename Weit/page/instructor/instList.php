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
include('instListFunction.php');
tableOpen();
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
        serachDepart($con, $search);
    }
}
else{
    searchAll($con);
}
tableClose();
?>
</body>
</html>
