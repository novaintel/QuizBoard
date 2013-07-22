<?php require_once("../includes/connections.php");?>
<?php include_once("../includes/functions.php");?>
<?php include("../includes/quiz_board.php");?>
<!DOCTYPE HTML>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Quiz Board</title>
    
    <link rel="stylesheet" type="text/css" href="_css/main.css">
    <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'> 
</head>

<body>
	<!--content div for the leader board-->
	<div id="content">
    	<div class="clear"></div>
        
    	<!--wraper div for the questions div-->
    	<div id="que_wraper" class="wraper_class">
        	<div class="stat_div"> stats #1</div>
            <div class="stat_div"> stats #2</div>
            <div class="stat_div"> stats #3</div>
            <div class="stat_div"> stats #4</div>
    	</div>
        
        <!--wraper div for the leader board-->
        <!--wraper div for the leader board-->
        <div id="lb_wraper" class="wraper_class">
        	<div id="lb_logo"><h2>Leader board</h2></div>
            <div>
            	<ul>					
				<?php 
				  	while($query_result = mysql_fetch_array($result)){
						echo" <li>
							<a href=\"#\">
							  <h4>{$query_result['username']}</h4>
							  <p>{$query_result['player_dept']}
							  <span class=\"lb_score\">{$query_result['total_played']}</span>
							  </p>
							</a>                        
						</li>"; 
					}
                 ?>		       
			  </ul>
            </div>
            
            <div id="lb_butt">
            	<a href="http://localhost/quiz_board/public/index.php">Back to Quiz Board</a>
            </div>
            
        </div>
        
        <div class="clear"></div>
    </div>
</body>
</html>