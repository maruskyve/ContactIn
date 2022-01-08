<?php
	require_once 'koneksi.php';
 
	$user_id = $_POST['user_id'];
 
	if(!$user_id){
		echo json_encode(array('message'=>'required field is empty'));
	} else {
		$query = mysqli_query($con, "DELETE FROM user WHERE user_id = '$user_id'");
 
		if ($query){
			echo json_encode(array('message'=>'user account successfully DELETED'));
		} else {
			echo json_encode(array('message'=>'user account failed to DELETE'));
		}
	}
?>
