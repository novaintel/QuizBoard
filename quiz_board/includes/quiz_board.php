<?php

//this section of code is redundant find a more efficient way to deal with this later
$sql = "SELECT * FROM `question`";
$result = run_query($sql,$connection);
//*************************************************************************************
$num_rows = mysql_num_rows($result);
$rand_num = rand(1,$num_rows);
$sql = "SELECT `que_id`, `banner_id`, `question`, `choice_1`, `choice_2`, `choice_3`, `choice_4`, `answer`, `que_type` ";
$sql .= "FROM `question` WHERE `que_id` = {$rand_num}";

$result = run_query($sql,$connection);

$result_set = mysql_fetch_array($result);


$sql = "SELECT `played_games`.*, `player_info`.*\n"
    . "FROM `player_info`, `played_games`\n"
    . "WHERE `played_games`.`banner_id` = `player_info`.`banner_id`\n"
    . "ORDER BY `played_games`.`answered` DESC\n"
    . "\n"
    . " LIMIT 0, 10 ";
	
$result = run_query($sql,$connection);


?>