<%@ page language="java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8"> 
<title>Power Blog</title>
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
          <li class="nav-link">
            <form method="POST" action="home.do">
              <button class="nav-link" type="submit">Home</button>
            </form>
          </li>
          <li class="nav-link">
            <form method="POST" action="logout.do">
              <button class="nav-link" type="submit">Logout</button>
            </form>
          </li>
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
                Welcome, ${user.firstName} ${user.lastName}
              </c:otherwise>
            </c:choose>
          </h1>
        </div>

        <c:if test="${!(empty posts)}">
          <c:forEach var="post" items="${posts}">
            <div class="row" style="margin-left: 20px;margin-right: 20px; margin-bottom: 10px;">
              <c:if test="${!(empty user)}">
                <form method="POST" action="deletePost.do">
                  <input type="hidden" name="postId" value="${ post.postId }"> 
                  <input type="hidden" name="postEmail" value="${ post.postEmail }"> 
                  <input type="hidden" name="userEmail" value="${ userSelected.email }">
                  <input type="submit" value="X">
                </form>
              </c:if>
              ${post.content}--${post.datetime}
            </div>

            <c:if test="${!(empty postIdToCommentsMap)}">
              <c:forEach var="comment" items="${postIdToCommentsMap[post.postId]}">
                <div class="row" style="margin-left: 40px; margin-bottom: 10px;">
                  <c:if test="${!(empty user)}">
                    <form method="POST" action="deleteCommentHome.do">
                      <input type="hidden" name="commentId" value="${ comment.commentId }"> 
                      <input type="hidden" name="commentEmail" value="${ comment.commentEmail }">
                      <input type="hidden" name="userLogin" value="${ user.email }"> 
                      <input type="hidden" name="userEmail" value="${ userSelected.email }">
                      <input type="submit" value="X">
                    </form>
                  </c:if>
                    Comment by ${emailToFullNameMap[comment.commentEmail]} - ${comment.content}--${comment.datetime}
                </div>
              </c:forEach>
            </c:if>

            <div class="form-inline" style="margin-left: 40px; margin-bottom: 10px;">
              <c:choose>
                <c:when test="${empty user}">
                  <h3> Please log in to comment, Click <a href="login.do">here</a>.</h3>
                </c:when>

                <c:otherwise>
                  <form method="POST" action="home.do">         
                    <input type="hidden" name="userEmail" value="userSelected.email }">
                    <input type="hidden" name="postId" value="${ post.postId }"> 
                    <input type="text" class="form-control w-50" name="comment" value="">
                    <input class="btn btn-secondary" type="submit" name="button"
                      style="margin-left: 10px;" value="Comment">
                  </form>
                </c:otherwise>
              </c:choose>
            </div>
          </c:forEach>
        </c:if>
        <c:if test="${!(empty user)}">
          <div style="margin-left: 80px; margin-top: 40px;">
            <h3>New Post:
            <form method="POST" action="home.do">
              <textarea class="form-control w-40" rows="5" name="post"
                value="${postForm.content}"></textarea>
              <input class="btn btn-secondary" type="submit" name="button" style="margin-top: 20px;" value="Submit">
            </form>
            </h3>
          </div>
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