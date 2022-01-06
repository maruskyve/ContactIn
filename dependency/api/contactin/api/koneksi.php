<?php
 
    $con = mysqli_connect("localhost", "root", "", "contactin");
    if (mysqli_connect_errno()){
        echo "Failed to connect database".mysqli_connect_errno();
    }
    
?>