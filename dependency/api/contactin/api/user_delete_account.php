<?php
	require_once 'koneksi.php';
 
	$user_id = $_POST['user_id'];
 	
	$query = mysqli_query($con, "DELETE FROM user WHERE user_id = '$user_id';");

	if ($query){
		echo json_encode(array('message'=>'Your account successfully deleted'));
	} else {
		echo json_encode(array('message'=>'Failed to delete your account'));
	}
	
?>
