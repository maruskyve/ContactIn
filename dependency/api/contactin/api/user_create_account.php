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
 
	// DUMMY
	// $user_id = "9231";
	// $user_fname = "userfname";
	// $user_lname = "userlname";
	// $user_name = "username";
	// $user_gender = "usergender";
	// $user_phone_number = "userphone";
	// $user_email = "useremail";
	// $user_password = "userpassword";
	// $user_ppicture = "userppicture";


	if(!$user_id){
		echo json_encode(array('message'=>'required field is empty'));
	} else {
		$query = mysqli_query($con, "INSERT INTO user VALUES (
		'$user_id', 
		'$user_fname',
		'$user_lname',
		'$user_name',
		'$user_gender',
		'$user_phone_number',
		'$user_email',
		'$user_password',
		'$user_ppicture'
		)");
 
		if ($query){
			echo json_encode(array('message'=>'user data successfully added'));
		} else {
			echo json_encode(array('message'=>'user data failed to add'));
		}
	}
?>