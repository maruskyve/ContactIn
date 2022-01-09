<?php
	require_once 'koneksi.php';
 
	$target = $_GET['target'];
	$file_path;

	if ($target == "user_ppicture") {
		$file_path = "assets/img/user_ppicture/";
	}

    if(!is_dir($file_path)){
        //Directory does not exist, so lets create it.
        mkdir($file_path, 0755, true);
    }
    
    $file_path = $file_path . basename( $_FILES['uploaded_file']['name']);
    if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path)) {
        echo "File upload success";
    } else{
        echo "File upload fail";
    }
?>