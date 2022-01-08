<?php
	require_once 'koneksi.php';
	
	$idFakultas = $_POST['id_fakultas'];
	$kodeFakultas = $_POST['kode_fakultas'];
	$namaFakultas = $_POST['nama_fakultas'];
	
	if(!$kodeFakultas || !$namaFakultas){
		echo json_encode(array('message'=>'required field is empty'));
	} else {
		$query = mysqli_query($con, "UPDATE fakultas SET kode_fakultas='$kodeFakultas', 
			nama_fakultas='$namaFakultas' WHERE id_fakultas = '$idFakultas'");
		
		if ($query){
			echo json_encode(array('message'=>'fakultas data successfully updated'));
		} else {
			echo json_encode(array('message'=>'fakultas data failed to update'));
		}
	}
?>