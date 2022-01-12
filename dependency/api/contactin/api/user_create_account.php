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

	// Required field (user_name, user_phone_number, user_email, user_password, user_gender)
	if (!$user_name || !$user_phone_number || !$user_email || !$user_password || !$user_gender) {
		echo json_encode(array('message'=>'Required field is empty'));
	} else {
		// Checking if username not exist in database user table
		// if (!mysqli_query($con, "SELECT user_name FROM user WHERE user_name = '$user_name'")->fetch_assoc()['user_name']) {
			$query = mysqli_query($con, "INSERT INTO user VALUES (
				'$user_id', 
				'$user_name',
				'$user_email',
				'$user_phone_number',
				'$user_password',
				'$user_fname',
				'$user_lname',
				'$user_gender',
				'$user_ppicture'
				)");
			if ($query){
				echo json_encode(array('message'=>'Account register successfully'));
			} else {
				echo json_encode(array('message'=>'Account register failed'));
			}
		// } else {
		// 	echo json_encode(array('message'=>'Username already taken, please choose another username'));
		// }
	}
?>