<?php
	require_once 'koneksi.php';
 
	$contact_id = $_POST['contact_id'];
 
	$query = mysqli_query($con, "DELETE FROM contact WHERE contact_id = '$contact_id'");

	if ($query){
		echo json_encode(array('message'=>'Contact delete successfully'));
	} else {
		echo json_encode(array('message'=>'Failed to delete contact'));
	}
?>
