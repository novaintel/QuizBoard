<?php
require_once("constants.php");

//create database connection
$connection = mysql_connect(DB_SERVER,DB_USER,DB_PASS);
if(!$connection){
	die('Error connecting to database: '.mysql_error());	
}

//selelect the database
$db = mysql_select_db(DB_NAME, $connection);
if(!$db){
	die('Error selecting database: '.mysql_error());
}
?>
