<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8"> 
<title>Log in Page</title>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
  <div class="container">
    <div class="jumbotron" >
      <h1 class="text-center">Blog Master</h1>
      <p class="text-center">Tell your story</p4> 
    </div>

    <div class="row">
      <div class="col-md-3" style="padding-left: 0px;">
        <div class="nav nav-pills nav-stacked">
        <li class="active"><a href="Login.jsp">Login</a></li>
        <li><a href="Register.jsp">Register</a></li>
        <hr>
        <jsp:include page="userLogin.html"/>
        </div>
      </div>

      <div class="col-md-9" style="padding-right: 0px;">
        <div class="container">
          <h1 style="margin-left: 200px;margin-bottom: 50px;">Rita Chen's Blog</h1>
        </div>

        <div class="row" style="margin-left: 20px;margin-right: 20px; margin-bottom: 10px;">
        <h4>
          I have really many assignments to do, and I didn't finsh them on time -- 11/15/2018 11:12pm</div></h4>

        <div class="row" style="margin-left: 80px;margin-right: 20px; margin-bottom: 10px;">
        <h4>
          Comment by Donald Trump - You should work harder. -- 11/15/2018 12:12pm</div></h4>

        <div class="row" style="margin-left: 20px;margin-right: 20px; margin-bottom: 10px;">
        <h4>
          I really want to have a vacation.-- 11/16/2018 1:22pm</div></h4>

        <div class="row" style="margin-left: 80px;margin-right: 20px; margin-bottom: 10px;">
        <h4>
          Comment by George Bush - Thanks giving is comming. -- 11/16/2018 8:30pm</div></h4>
      </div> 
    </div> 

  </div><!-- /container -->

</body>
</html>s