<?php
	require_once 'koneksi.php';
 
	$contact_id = $_POST['contact_id'];
 
	if(!$contact_id){
		echo json_encode(array('message'=>'required field is empty'));
	} else {
		$query = mysqli_query($con, "DELETE FROM contact WHERE contact_id = '$contact_id'");
 
		if ($query){
			echo json_encode(array('message'=>'contact data successfully DELETED'));
		} else {
			echo json_encode(array('message'=>'contact data failed to DELETE'));
		}
	}
?>
