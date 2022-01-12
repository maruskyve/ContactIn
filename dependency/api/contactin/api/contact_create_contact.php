<?php
	require_once 'koneksi.php';
 
	$contact_id = $_POST['contact_id'];
	$contact_phone_number = $_POST['contact_phone_number'];
	$contact_email = $_POST['contact_email'];
	$contact_fname = $_POST['contact_fname'];
	$contact_lname = $_POST['contact_lname'];
	$contact_ppicture = $_POST['contact_ppicture'];
	$contact_stars = $_POST['contact_stars'];
	$fk_contact_type_id = $_POST['fk_contact_type_id'];
	$fk_user_id = $_POST['fk_user_id'];
	
	// Required field (contact_phone_number, contact_email, contact_fname, contact_lname)
	if (!$contact_phone_number || !$contact_email || !$contact_fname || !$contact_lname) {
		echo json_encode(array('message'=>'Required field is empty'));
	} else {
		$query = mysqli_query($con, "INSERT INTO contact VALUES (
			'$contact_id',
			'$contact_phone_number',
			'$contact_email',
			'$contact_fname',
			'$contact_lname',
			'$contact_ppicture',
			'$contact_stars',
			'$fk_contact_type_id',
			'$fk_user_id')");

		if ($query){
			echo json_encode(array('message'=>'Contact successfully added'));
		} else {
			echo json_encode(array('message'=>'Failed to add contact'));
		}
	}
?>