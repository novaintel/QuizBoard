<?php require_once("../includes/connections.php");?>
<?php include_once("../includes/functions.php");?>
<?php include("../includes/quiz_board.php");?>
<?php
if(!empty($_GET)){
	$que_id = $_GET['queId'];
	$sql = "SELECT `que_id`, `banner_id`, `question`, `choice_1`, `choice_2`, `choice_3`, `choice_4`, `answer`, `que_type` ";
	$sql .= "FROM `question` WHERE `que_id` = {$que_id}";	
	$result = run_query($sql,$connection);	
	$result_set = mysql_fetch_array($result);
	
	if($result_set['answer'] == $_GET['choice']){
		$result = array('answer'=> 'correct');
		echo json_encode($result);
	}else{
		$result = array('answer' => 'wrong');
		echo json_encode($result);
	}
	
}
?>