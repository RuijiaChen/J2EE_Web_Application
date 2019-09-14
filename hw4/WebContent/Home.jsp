<%@page import="databean.UserBean"%>
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
            <form method="POST" action="Home">
              <button class="nav-link" type="submit">Home</button>
            </form>
          </li>
          <li class="nav-link">
            <form method="POST" action="Logout">
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
            <%
            UserBean userBean = (UserBean) session.getAttribute("user");
            if (userBean != null) {
              out.print(userBean.getFirstName() + " " + userBean.getLastName() + "'s Home Page");
            } else {
              out.print("Please Log in");
            }
          %>
          </h1>
        </div>

        <div class="row" style="margin-left: 20px;margin-right: 20px; margin-bottom: 10px;">
        <h4><button type="button" class="btn btn-default btn-sm">
          <span class="glyphicon glyphicon-remove"></span> 
        </button>
          I have really many assignments to do, and I didn't finsh them on time -- 11/15/2018 11:12pm</div></h4>

        <div class="row" style="margin-left: 80px;margin-right: 20px; margin-bottom: 10px;">
        <h4><button type="button" class="btn btn-default btn-sm">
          <span class="glyphicon glyphicon-remove"></span> 
        </button>
          Comment by Donald Trump - You should work harder. -- 11/15/2018 12:12pm</div></h4>

        <div class="row" style="margin-left: 20px;margin-right: 20px; margin-bottom: 10px;">
        <h4><button type="button" class="btn btn-default btn-sm">
          <span class="glyphicon glyphicon-remove"></span> 
        </button>
          I really want to have a vacation.-- 11/16/2018 1:22pm</div></h4>

        <div class="row" style="margin-left: 80px;margin-right: 20px; margin-bottom: 10px;">
        <h4><button type="button" class="btn btn-default btn-sm">
          <span class="glyphicon glyphicon-remove"></span> 
        </button>
          Comment by George Bush - Thanks giving is comming. -- 11/16/2018 8:30pm</div></h4>

        <div class="row" style="margin-top: 40px;">
          <h3>New Post:<textarea class="form-control w-40" rows="5"></textarea>
          <button type="button" style="margin-top: 20px;">Submit</button></h3>
        </div>

      </div> 
    </div> 

  </div><!-- /container -->

</body>
</html>