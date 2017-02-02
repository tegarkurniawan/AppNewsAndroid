<?php
	//database configuration
	$host ="localhost";
	$user ="root";
	$pass ="";
	$database = "news";
	$connect = new mysqli($host, $user, $pass,$database) or die("Error : ".mysql_error());
?>