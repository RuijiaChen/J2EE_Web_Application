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
        <li class="active"><a href="Home.jsp">Home</a></li>
        <li><a href="Login.jsp">Logout</a></li>
        <hr>
        </div>
      </div>

      <div class="col-md-9" style="padding-right: 0px;">
        <div class="container">
          <h1 style="margin-left: 200px;margin-bottom: 50px;">Rita Chen's Blog</h1>
        </div>

        <div class="row" style="margin-left: 20px;margin-right: 20px; margin-bottom: 10px;">
        <h4>I have really many assignments to do, and I didn't finsh them on time -- 11/15/2018 11:12pm</div></h4>

        <div class="row" style="margin-left: 80px;margin-right: 20px; margin-bottom: 10px;">
          <h4><button type="button" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-remove"></span> 
          </button>
            Comment by Donald Trump - You should work harder. -- 11/15/2018 12:12pm</div>
          </h4>
          <div class="form-group" style="margin-left: 80px;margin-right: 20px; margin-bottom: 10px;">
            <div class="col-sm-10">
              <input type="password" class="form-control" id="inputPassword"
              placeholder="Input comment here">
            </div>
            <button for="inputPassword">Comment</button>
          </div>

        <div class="row" style="margin-left: 20px;margin-right: 20px; margin-bottom: 10px;">
        <h4>I really want to have a vacation.-- 11/16/2018 1:22pm</div></h4>

        <div class="row" style="margin-left: 80px;margin-right: 20px; margin-bottom: 10px;">
        <h4><button type="button" class="btn btn-default btn-sm">
          <span class="glyphicon glyphicon-remove"></span> 
        </button>
          Comment by George Bush - Thanks giving is comming. -- 11/16/2018 8:30pm</div></h4>
        <div class="form-group" style="margin-left: 80px;margin-right: 20px; margin-bottom: 10px;">
            <div class="col-sm-10">
              <input type="password" class="form-control" id="inputPassword"
              placeholder="Input comment here">
            </div>
            <button for="inputPassword">Comment</button>
          </div>

      </div> 
    </div> 

  </div><!-- /container -->

</body>
</html>