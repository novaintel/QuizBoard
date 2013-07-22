<?php
session_start();

function logged_in(){
	return isset($_SESSION['banner_id']);
}

//confirm whether the user has logged in or not
function confirm_logged_in(){
	if(!logged_in()){
		redirect_to('http://localhost/quiz_board/public/login.php');
	}	
}
?>