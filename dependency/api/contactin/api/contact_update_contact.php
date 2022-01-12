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
	
	$query = mysqli_query($con, "UPDATE contact SET 
		contact_phone_number='$contact_phone_number', 
		contact_email='$contact_email',
		contact_fname='$contact_fname',
		contact_lname='$contact_lname',
		contact_ppicture='$contact_ppicture',
		contact_stars='$contact_stars',
		fk_contact_type_id='$fk_contact_type_id' WHERE contact_id = '$contact_id'");
	
	if ($query){
		echo json_encode(array('message'=>'Contact data successfully updated'));
	} else {
		echo json_encode(array('message'=>'Failed to update contact data'));
	}
	
?>