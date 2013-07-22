<?php include_once("functions.php");?>
<?php 
//find session
session_start();

//unset all the session
$_SESSION = array();

//destroy the session
if(isset ($_COOKIE[session_name()])){
	setcookie(session_name(),'',  time()-42000,'/');
}

//destroy session
session_destroy();

redirect_to('http://localhost/quiz_board/public/login.php');

?>