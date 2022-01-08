<?php
    require_once 'koneksi.php';
    
    $fk_user_id = $_POST['user_id'];

    $result = array();
    $query  = mysqli_query($con, "SELECT * FROM contact WHERE fk_user_id = '$fk_user_id'");
    
    while ($row = mysqli_fetch_assoc($query)){
        $result[]   = $row;
    }
    echo json_encode(array('result'=>$result));
?>