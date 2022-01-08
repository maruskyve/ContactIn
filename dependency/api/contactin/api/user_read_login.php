<?php
    require_once 'koneksi.php';

    $login_username = $_POST['login_username'];
    $login_password = $_POST['login_password'];

    $result = array();
    $query  = mysqli_query($con, "SELECT * FROM user WHERE user_name = '$login_username' AND user_password = '$login_password'");
    
    while ($row = mysqli_fetch_assoc($query)){
        $result[]   = $row;
    }
    echo json_encode(array('result'=>$result));

?>