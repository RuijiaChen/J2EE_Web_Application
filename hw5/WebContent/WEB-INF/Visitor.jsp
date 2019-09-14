<%@page import="databean.UserBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <div class="jumbotron">
      <h1 class="text-center">Blog Master</h1>
      <p class="text-center">Tell your story</p4> 
    </div>

    <div class="row">
      <div class="col-md-3" style="padding-left: 0px;">
        <div class="nav nav-pills nav-stacked">
        <c:choose>
          <c:when test="${empty user}">
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
          </c:when>
          <c:otherwise>
            <li class="nav-item">
              <form method="POST" action="home.do">
                <button class="nav-link" type="submit">Home</button>
              </form>
            </li>
            <li class="nav-item">
              <form method="POST" action="logout.do">
                <button class="nav-link" type="submit">Logout</button>
              </form>
            </li>
          </c:otherwise>
        </c:choose>
        <hr>
        <jsp:include page="UserInBar.jsp"/>
        </div>
      </div>

      <div class="col-md-9" style="padding-right: 0px;">
        <div class="container">
          <h1 style="margin-left: 200px;margin-bottom: 50px;">
            <c:choose>
              <c:when test="${(empty user)}">
                Please Login
              </c:when>
              <c:otherwise>
                ${userSelected.firstName} ${userSelected.lastName}'s Blog
              </c:otherwise>
            </c:choose>
          </h1>
        </div>

      <c:if test="${!(empty posts)}">
        <c:forEach var="post" items="${posts}">
          <div class="row" style="margin-left: 20px; margin-right: 20px;">
            ${post.content}--${post.datetime}
          </div>

          <c:if test="${!(empty postIdToCommentsMap)}">
            <c:forEach var="comment" items="${postIdToCommentsMap[post.postId]}">
              <div class="row" style="margin-left: 40px; margin-bottom: 10px;">


                <form method="POST" action="deleteCommentVisitor.do">
                  <input type="hidden" name="commentEmail" value="${ comment.commentEmail }">
                  <input type="hidden" name="commentId" value="${comment.commentId}"> 
                  <input type="hidden" name="userEmail" value="${userSelected.email}">
                  <input type="submit" value="X">
                </form>

                Comment by ${emailToFullNameMap[comment.commentEmail]} - ${comment.content}--${comment.datetime}
              </div>
            </c:forEach>
          </c:if>
            <div class="form-inline" style="margin-left: 40px; margin-bottom: 10px;">
              <form method="POST" action="visit.do">
                <input type="hidden" name="userEmail" value="${ userSelected.email }">
                <input type="hidden" name="postId" value="${ post.postId }" /> 
                <input type="text" class="form-control w-50" name="comment" value="">
                <input class="btn btn-secondary" type="submit" name="button" style="margin-left: 10px;" value="Comment">
              </form>
            </div>

        </c:forEach>
      </c:if>
      <c:if test="{empty user}">
        <h3> Please log in to comment, Click <a href="login.do">here</a>.</h3>
      </c:if>
      <c:if test="${!(empty errors)}">
        <c:forEach var="error" items="${errors}">
          <h3 style="color:red">${error}</h3>
        </c:forEach>
      </c:if>
    </div>
      </div>
  </div><!-- /container -->
</body>
</html>