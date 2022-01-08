<?php
	require_once 'koneksi.php';

	$user_id = $_POST['user_id'];
	$user_name = $_POST['user_name'];
	$user_phone_number = $_POST['user_phone_number'];
	$user_email = $_POST['user_email'];
	$user_password = $_POST['user_password'];
	$user_fname = $_POST['user_fname'];
	$user_lname = $_POST['user_lname'];
	$user_gender = $_POST['user_gender'];
	$user_ppicture = $_POST['user_ppicture'];
		
	$query = mysqli_query($con, "UPDATE user SET 
		user_name='$user_name',
		user_phone_number='$user_phone_number',
		user_email='$user_email',
		user_password='$user_password',
		user_fname='$user_fname',
		user_lname='$user_lname',
		user_gender='$user_gender',
		user_ppicture='$user_ppicture'
		WHERE user_id = '$user_id'");
	
	if ($query){
		echo json_encode(array('message'=>'Profile successfully updated'));
	} else {
		echo json_encode(array('message'=>'Profile failed to update'));
	}
	
?>