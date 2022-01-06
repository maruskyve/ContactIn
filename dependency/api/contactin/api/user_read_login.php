<?php
    require_once 'koneksi.php';
    
    $login_username = $_GET['login_username'];
    $login_password = $_GET['login_password'];

    $result = array();
    $query;

    try {
        $query  = mysqli_query($con, "SELECT * FROM user WHERE user_name = '$login_username' AND user_password = '$login_password'");
        while ($row = mysqli_fetch_assoc($query)){
            $result[] = $row;
        }
        echo "credential match";
    } catch(Exception $e){
        echo "your credential !match";
    }
    echo json_encode(array('result'=>$result));
?>