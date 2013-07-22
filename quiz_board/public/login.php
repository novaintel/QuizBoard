<?php require_once("../includes/session.php");?>
<?php require_once("../includes/connections.php");?>
<?php include_once("../includes/functions.php");?>
<?php require("../includes/submit.php")?>
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>Quiz Board</title>
  
  <link rel="stylesheet" type="text/css" href="_css/login.css">
  </head>

  <body class="modalDialog">
    <div class="bg_style">
      <header>
          <div id="logo_wrapper">
              <h1>Quiz Board</h1>
              <a href="faculty_signup.php" title="click to create an account">Signup</a>
          </div>
      </header>
                    
      <section>
          <div>
              <form action="login.php" method="post" >
                  <div>
                      <label for="username">Username:</label>
                      <input type="text" id="username" name="username" />
                  
                      <div class="clear"></div>
                  
                      <label for="pwd">Password:</label>
                      <input type="password" id="pwd" name="pwd" />
                  </div>	
                  
                  <div class="clear"></div>
                  <div>
                      <span>
                          <input type="submit" name="login_sub" value="Enter" class="buttonz" />
                          <input type="reset" name="reset" value="Cancel" class="buttonz" />
                      </span>
                  </div>
              </form>				
          </div>
      </section>
        
      <footer class="head_style">
          <p><span>Ubicomp Project</span></p>
          <div class="clear"></div>
      </footer>
    </div>
  </body>
</html>