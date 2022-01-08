<?php
    require_once 'koneksi.php';

    $result = array();
    $query  = mysqli_query($con, "SELECT * FROM contact_type");
    
    while ($row = mysqli_fetch_assoc($query)){
        $result[] = $row;
    }
    echo json_encode(array('result'=>$result));

?>