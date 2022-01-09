<?php
    require_once 'koneksi.php';

    $requestContext = $_GET['requestContext'];
    $result = array();

    if ($requestContext == 'login') {   
        $login_username = $_GET['login_username'];
        $login_password = $_GET['login_password'];
        $query  = mysqli_query($con, "SELECT * FROM user WHERE user_name = '$login_username' AND user_password = '$login_password'");
        while ($row = mysqli_fetch_assoc($query)){
            $result[]   = $row;
        }
    } else if ($requestContext == 'fetch') {
        $user_id = $_GET['user_id'];
        $query  = mysqli_query($con, "SELECT * FROM user WHERE user_id = '$user_id'");
        while ($row = mysqli_fetch_assoc($query)){
            $result[]   = $row;
        }
    }
    echo json_encode(array('result'=>$result));
    
    // require_once 'koneksi.php';

    // $login_username = $_POST['login_username'];
    // $login_password = $_POST['login_password'];

    // $result = array();
    // $query  = mysqli_query($con, "SELECT * FROM user WHERE user_name = '$login_username' AND user_password = '$login_password'");
    
    // while ($row = mysqli_fetch_assoc($query)){
    //     $result[]   = $row;
    // }
    // echo json_encode(array('result'=>$result));

?>