<%@page import="databean.UserBean"%>

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

        <%
          UserBean userBean = (UserBean) session.getAttribute("user");
          if (userBean == null) {
        %>
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
        <%
          }
          else{
        %>
        <li class="nav-item">
          <form method="POST" action="Home">
            <button class="nav-link" type="submit">Home</button>
          </form>
        </li>
        <li class="nav-item">
          <form method="POST" action="Logout">
            <button class="nav-link" type="submit">Logout</button>
          </form>
        </li>

        <%
          }
        %>
        <hr>
        <jsp:include page="UserInBar.jsp"/>
        </div>
      </div>

      <div class="col-md-9" style="padding-right: 0px;">
        <div class="container">
          <h1 style="margin-left: 200px;margin-bottom: 50px;">Blog Content</h1>
        </div>
        <%
          if(userBean == null){
        %>
        <jsp:include page="commentNotLogin.jsp"/>
        <%
          }
          else{
        %>
        <jsp:include page="commentLogin.jsp"/>
        <%
          }
        %>
    </div> 

  </div><!-- /container -->

</body>
</html>