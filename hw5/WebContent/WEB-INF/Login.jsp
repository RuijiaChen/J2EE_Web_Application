<%@ page language="java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
          <form method="GET" action="login.do">
            <button class="nav-link" type="submit">Login</button>
          </form>
        </li>
        <li class="nav-item">
          <form method="GET" action="register.do">
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

      <form class="w-md-3" style="padding-right: 0px;" action="login.do" method="POST">
        <div class="form-group">
          <label for="E-mail">E-mail:</label> 
          <input type="text" class="form-control" name="email" value="${form.email}">
        </div>

        <div class="form-group">
          <label for="Password">Password:</label> 
          <input type="password"
            class="form-control" name="password">
        </div>

        <input class="btn btn-secondary" type="submit" name="button" value="Login"/>
      </form>

        <c:if test="${!(empty errors)}">
          <c:forEach var="error" items="${errors}">
            <h3 style="color: red;">${error}</h3>
          </c:forEach>
        </c:if>
      </div> 
    </div> 
  </div><!-- /container -->

</body>
</html>