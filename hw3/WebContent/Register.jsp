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
        <li><a href="Login.jsp">Login</a></li>
        <li class="active"><a href="Register.jsp">Register</a></li>
        <hr>
        <jsp:include page="user.html"/>
        </div>
      </div>
      

      <div class="col-md-9" style="padding-right: 0px;">

        <form class="form-signin"style="padding-right: 0px;">
          <h2 class="form-signin-heading">Please register</h2>
          <label for="inputEmail">Email address</label>
          <input type="email" id="inputEmail" class="form-control" placeholder="Email address">
          <hr>
          <label for="inputPassword">Password</label>
          <input type="password" id="inputPassword" class="form-control" placeholder="Password">
          <hr>

          <label for="firstName">First Name</label>
          <input type="password" id="firstName" class="form-control" placeholder="First Name">
          <hr>

          <label for="lastName">Last Name</label>
          <input type="password" id="lastName" class="form-control" placeholder="Last Name">
          <hr>
          <a class="btn btn-secondary" href="Home.jsp">Submit</a>
        </form>
      </div> 
    </div> 

  </div><!-- /container -->

</body>
</html>s