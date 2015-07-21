<?
$file_path = "/home/pi/save_file/";

echo $file_path;

$fp = fopen("/home/pi/save_file/hello.txt","a");
fwrite($fp,"hello");
fwrite($fp, $_FILES['uploaded_file'] . "\n");
fwrite($fp, basename($_FILES['uploaded_file']['name']) . "\n");

$file_path = $file_path . basename($_FILES['uploaded_file']['name']);

if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path)){
    echo "file upload success";
}else{
    echo "file upload fail";
}
?>
