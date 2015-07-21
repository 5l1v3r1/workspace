<?php

$dbc = mysqli_connect('127.0.0.1','root','tasigmin132','jeehi') or die('Error connecting to MySQL server.');
mysqli_query($dbc, "set names utf8");

$path = $_GET[path];
$modify = $_GET[modify];

$query = "INSERT INTO IMAGE_FOLDER(FILE_PATH,FILE_DATE) values('$path','$modify');";

$result = mysqli_query($dbc, $query) or die('Error querying database');

echo $result;

mysqli_close($dbc);
?>
