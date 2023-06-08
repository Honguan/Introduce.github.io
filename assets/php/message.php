<?php
require_once("dbtools.inc.php");
header("Content-type: text/html; charset=utf-8");

// $upload_dir =  "./images/";
// $to = iconv("UTF-8", "Big5", $_FILES["image"]["name"]);
// $upload_file = $upload_dir.$to;
// if (move_uploaded_file($_FILES["image"]["tmp_name"],".".$upload_file)){
//   echo "<strong>檔案上傳成功</strong><hr>";	
//   $upload_file = iconv("Big5", "UTF-8", $upload_file);
// }
// else{
//   echo "檔案上傳失敗 (" . $_FILES["image"]["error"] . ")<br><br>";
// }
date_default_timezone_set('Asia/Taipei');
// $id = $_POST["id"];
$name = $_POST["name"];
$email = $_POST["email"];
$message = $_POST["message"];
$ip = $_SERVER['REMOTE_ADDR'];
//$time = $_POST['time'];
$time = date('Y-m-d H:i:s');
session_start();


$link = create_connection();
$sql = "SELECT * FROM information";
$result = execute_sql($link, "contact", $sql);

// if (mysqli_num_rows($result) == 0){
    mysqli_free_result($result);
    $sql = "INSERT INTO information (`name`, `email`, `message`, `time`, `ip`) 
        VALUES ('$name','$email','$message','$time','$ip')";
    $result = execute_sql($link, "contact", $sql);
    echo $sql;
    header("location:../../");
// }
// else{
//   echo "<script>ID();</script>";
// }
mysqli_close($link);
?>