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

	// DUMMY
	// $contact_id = "asdrf";
	// $contact_phone_number = "ASD";
	// $contact_email = "AUFJMJ";
	// $contact_fname = "AUFDHF@NSUDF";
	// $contact_lname = "adhna9doh";
	// $contact_ppicture = "9wf9";
	// $contact_stars = "0";
	// $fk_contact_type_id = "2";
	// $fk_user_id = "72";

	if(!$contact_id){
		echo json_encode(array('message'=>'required field is empty'));
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
			echo json_encode(array('message'=>'user data successfully added'));
		} else {
			echo json_encode(array('message'=>'user data failed to add'));
		}
	}
?>