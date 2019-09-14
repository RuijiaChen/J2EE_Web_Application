<%@page import="java.util.List"%>

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

        <form class="form-signin" style="padding-right: 0px;">
          <h2 class="form-signin-heading">Please register</h2>

    
          <form class="form-signin" style="margin-left: 100px;" action="Register" method="POST">
 
            <div class="form-group">
              <label for="fn">First Name:</label> <input type="text"
                class="form-control" name="firstName" value="${form.firstName}">
            </div>

            <div class="form-group">
              <label for="ln">Last Name:</label> <input type="text"
                class="form-control" name="lastName" value="${form.lastName}">
            </div>

            <div class="form-group">
              <label for="email">Email address:</label> <input type="text"
                class="form-control" name="email" value="${form.email}">
            </div>

            <div class="form-group">
              <label for="pw">Password:</label> <input type="password"
                class="form-control" name="password" value="${form.password}">
            </div>

            <div class="form-group">
              <label for="pw">Confirm Password:</label> <input type="password"
                class="form-control" name="confirm" value="${form.confirm}">
            </div>

            <input class="btn btn-secondary" type="submit" name="button"
              value="Register">
          </form>

          <%
            List<String> errors = (List<String>) request.getAttribute("errors");
            if (errors != null) {
              for (String error : errors) {
          %>
          <h3 style="color: red; margin-left: 40px;">
            <%=error%>
          </h3>
          <%
            }
            }
          %>
        </form>
      </div> 
    </div> 

  </div><!-- /container -->

</body>
</html>