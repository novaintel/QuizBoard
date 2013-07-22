<?php require_once("../includes/session.php");?>
<?php require_once("../includes/connections.php");?>
<?php include_once("../includes/functions.php");?>
<?php confirm_logged_in()?>
<?php require("../includes/submit.php")?>
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>Quiz Board</title>
  
  <link rel="stylesheet" type="text/css" href="_css/signup_frm.css">
  </head>

  <body class="modalDialog">
    <div class="bg_style">
      <div id="console" class="buttonz"><a href="../includes/end_session.php">Sign out</a></div>
      <header>
          <div id="logo_wrapper">
              <h1>Quiz Board CMS</h1>
          </div>
      </header> 
      <section>
      	<div>
          <form action="fac_cms.php" method="post">
            <div class="border_line">
                <label>Question:</label>
                <textarea class="inpt" name="question"></textarea>                
                <div class="clear"></div>
                <label>Choice #1: </label>
                <input type="text" name="choice1" id="choiceId1" />
                <div class="clear"></div>
                <label>Choice #2:</label>
                <input type="text" name="choice2" id="choiceId2" />
                <div class="clear"></div>
                <label>Choice #3:</label>
                <input type="text" name="choice3" id="choiceId3" />
                <div class="clear"></div>
                <label>Choice #4:</label>
                <input type="text" name="choice4" id="choiceId4" />
                <div class="clear"></div>
                <label>Question type :</label>
                <select class="inpt" name="que_type">
                	<option value="phy">Physics</option>
                    <option value="eng">English</option>
                    <option value="math">Maths</option>
                    <option value="cs">Computer Sci</option>
                </select>
                <div class="clear"></div>
                <label>Answer :</label>
                <select class="inpt" name="ans">
                	<option value="choice1">Choice #1</option>
                    <option value="choice2">Choice #2</option>
                    <option value="choice3">Choice #3</option>
                    <option value="choice4">Choice #4</option>
                </select>
                <div class="clear"></div>
            </div>
            
           <div>
            <span>
                <input type="submit" name="fac_cms_sub" value="Submit" class="buttonz" />
                <input type="reset" name="reset" value="Cancel" class="buttonz" />
            </span>
          </div>
      	</form> 
        </div>     
      </section>
      <footer>
      	<p></p>
      	<div class="clear"></div>
      </footer>
    </div>
  </body>
    
</html>