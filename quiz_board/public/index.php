<?php require_once("../includes/connections.php");?>
<?php include_once("../includes/functions.php");?>
<?php include("../includes/quiz_board.php");?>
<!DOCTYPE HTML>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Quiz Board</title>
    
    <link rel="stylesheet" type="text/css" href="_css/main.css">
    <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
    <script src="_js/jquery-1.7.2.min.js" type="text/javascript"></script>
  	<script src="_js/jquery.thingbroker-0.3.0.min.js" type="text/javascript"></script>  
</head>

<body>
	<!--content div for the leader board-->
	<div id="content">
    	<div class="clear"></div>
        
    	<!--wraper div for the questions div-->
    	<div id="que_wraper" class="wraper_class">
          <h1>Quiz Board </h1>
            
          <form name="form1" method="post" action="">
              <h3>Question</h3>
            
              <p><?php echo $result_set['question']?></p>
              <ul>
               <li> <label>
                  <input type="radio" name="choices" value="choice1" id="choices_0">
                  <span id="choice1"><?php echo $result_set['choice_1']?></span></label></li>
                
               <li> <label>
                  <input type="radio" name="choices" value="choice2" id="choices_1">
                  <span id="choice2"><?php echo $result_set['choice_2']?></span></label></li>
               
                <li><label>
                  <input type="radio" name="choices" value="choice3" id="choices_2">
                  <span id="choice3"><?php echo $result_set['choice_3']?></span></label></li>
                
                <li><label>
                  <input type="radio" name="choices" value="choice4" id="choices_3">
                  <span id="choice4"><?php echo $result_set['choice_4']?></span></label></li>
               </ul>
               <input type="hidden" value="<?php echo $result_set['que_id']?>" id="que_id">
          </form>
    	</div>
        
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
            	<a href="http://localhost/quiz_board/public/statistics.php">View detailed Statistics</a>
            </div>
            
        </div>
        
        <div class="clear"></div>
    </div>
    <script src="_js/index.js"></script>
</body>
</html>