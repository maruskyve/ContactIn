<?php
	include 'koneksi.php';
 
	$kodeFakultas = $_POST['kode_fakultas'];
	$namaFakultas = $_POST['nama_fakultas'];
 
	if(!$kodeFakultas || !$namaFakultas){
		echo json_encode(array('message'=>'required field is empty'));
	} else {
		$query = mysqli_query($con, "INSERT INTO tb_fakultas VALUES ('', '$kodeFakultas', '$namaFakultas')");
 
		if ($query){
			echo json_encode(array('message'=>'fakultas data successfully added'));
		} else {
			echo json_encode(array('message'=>'fakultas data failed to add'));
		}
	}
?>