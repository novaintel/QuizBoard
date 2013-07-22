<?php 
//this script handles all new addition entries.
if (!empty($_POST))
{
    // Array of post values for each different form on your page.
    $postNameArr = array('fac_cms_sub', 'fac_signup_sub','login_sub');        

    // Find all of the post identifiers within $_POST
    $postIdentifierArr = array();
        
    foreach ($postNameArr as $postName)
    {
        if (array_key_exists($postName, $_POST))
        {
             $postIdentifierArr[] = $postName;
        }
    }
    // Only one form should be submitted at a time so we should have one
    // post identifier.  The die statements here are pretty harsh you may consider
    // a warning rather than this. 
    if (count($postIdentifierArr) != 1)
    {
        count($postIdentifierArr) < 1 or
            die("\$_POST contained more than one post identifier: " .
               implode(" ", $postIdentifierArr));

        // We have not died yet so we must have less than one.
        die("\$_POST did not contain a known post identifier.");
    }
    
	//based on the triggered post identifier a database insertion will be made     
    switch ($postIdentifierArr[0]){
		case "fac_cms_sub":
			$banner_id = $_SESSION['banner_id'];
			$question  = mysql_prep($_POST['question']);
			$choice1   = mysql_prep($_POST['choice1']);
			$choice2   = mysql_prep($_POST['choice2']);
			$choice3   = mysql_prep($_POST['choice3']);
			$choice4   = mysql_prep($_POST['choice4']);
			$que_type  = mysql_prep($_POST['que_type']);
			$ans       = mysql_prep($_POST['ans']);
			
			$sql = "INSERT INTO `quiz_board`.`question` (`que_id`, `banner_id`, `question`,";
			$sql .= " `choice_1`, `choice_2`, `choice_3`, `choice_4`, `answer`, `que_type`) ";
			$sql .= "VALUES (NULL, '{$banner_id}', '{$question}', '{$choice1}', '{$choice2}', ";
			$sql .= "'{$choice3}', '{$choice4}', '{$ans}', '{$que_type}');";
			run_query($sql,$connection);
			break;
			
		case "fac_signup_sub":
			$banner_id = mysql_prep($_POST["banner_id"]);
			$username  = mysql_prep($_POST["username"]);
			$dept      = mysql_prep($_POST["dept"]);
			$pwd       = sha1($_POST["pwd"]);
			
		
			$sql = "INSERT INTO `quiz_board`.`faculty` (`faculty_id`, `banner_id`, `username`,"; 
			$sql .= "`pwd`, `dept`) VALUES (NULL, '{$banner_id}', '{$username}', '{$pwd}', '{$dept}');";		
			run_query($sql,$connection);
			
			
			break;
		case "login_sub":
			$username = $_POST['username'];
			$pwd = sha1($_POST['pwd']);
			
			$sql = "SELECT `banner_id`, `username`, `pwd` FROM `faculty` ";
			$sql .= "WHERE `username` = '{$username}' AND `pwd` = '{$pwd}'";
			$result_set = run_query($sql,$connection);
			
			if(mysql_num_rows($result_set) == 1){
				$result_set = mysql_fetch_array($result_set);
				$_SESSION['username']  = $result_set['username'];
				$_SESSION['banner_id'] = $result_set['banner_id'];
				redirect_to('http://localhost/quiz_board/public/fac_cms.php');
			}
			break;
		default: 
			echo "<p>Invalid action please try again</p>";
			break;
	}
}

if(!empty($_GET)){
	$result = $_GET;
	echo json_encode($result);
}
?>