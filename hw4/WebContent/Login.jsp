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
        <li class="nav-item">
          <form method="GET" action="Login">
            <button class="nav-link" type="submit">Login</button>
          </form>
        </li>
        <li class="nav-item">
          <form method="GET" action="Register">
            <button class="nav-link" type="submit">Register</button>
          </form>
        </li>
        <hr>
        <jsp:include page="UserInBar.jsp"/>
        </div>
      </div>

      <div class="col-md-9" style="padding-right: 0px;">
        <div class="container">
          <h1 style="margin-left: 200px;margin-bottom: 50px;">Login</h1>
        </div>
        <form class="w-md-3" style="padding-right: 0px;">
          <div class="form-group">
            <label for="E-mail">E-mail:</label> 
            <input type="text"
              class="form-control" id="E-mail">
          </div>
          <div class="form-group">
            <label for="Password">Password:</label> 
            <input type="password"
              class="form-control" id="Password">
          </div>
          <a class="btn btn-secondary" href="Home.jsp">Submit</a>
        </form>
      </div> 
    </div> 
  </div><!-- /container -->

</body>
</html>