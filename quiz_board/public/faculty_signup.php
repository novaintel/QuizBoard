<?php require_once("../includes/connections.php");?>
<?php include_once("../includes/functions.php");?>
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
      <header>
          <div id="logo_wrapper">
              <h1>Quiz Board CMS Signup</h1>
          </div>
      </header> 
      <section>
      	<div>
          <form action="faculty_signup.php" method="post">
            <div class="border_line">
                <label>Banner ID:</label>
                <input type="text" name="banner_id" id="banner_id" />
                <div class="clear"></div>
                <label>Username:</label>
                <input type="text" name="username" id="username" />
                <div class="clear"></div>
                <label>Department:</label>
                <select class="inpt" name="dept">
                	<option value="cs">Computer Science</option>
                	<option value="phy">Physics</option>
                    <option value="eng">English</option>
                    <option value="math">Maths</option>                    
                </select>
                <div class="clear"></div>
                <label>Password:</label>
                <input type="password" name="pwd" id="pwd" />
                <div class="clear"></div>
                <label>Confirm Password:</label>
                <input type="password" name="conf_pwd" id="conf_pwd" />
                <div class="clear"></div>
            </div>
            
           <div>
            <span>
                <input type="submit" name="fac_signup_sub" value="Submit" class="buttonz" />
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