<!doctype html>
<html lang="ko">
<head>

<%@ include file="/WEB-INF/include/include-header.jspf" %>
  <!-- Meta Tag -->
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <!-- SEO -->
  <meta name="description" content="150 words">
  <meta name="author" content="uipasta">
  <meta name="url" content="http://www.yourdomainname.com">
  <meta name="copyright" content="company name">
  <meta name="robots" content="index,follow">
  <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- All CSS Plugins -->
<link rel="stylesheet" type="text/css" href="/css/plugin.css">

<!-- Main CSS Stylesheet -->
<link rel="stylesheet" type="text/css" href="/css/style.css">
<!-- Google Web Fonts  -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:400,300,500,600,700">


</head>
<body>



  <!-- Home & Menu Section Start -->
      <header id="home" class="home-section">

          <div class="header-top-area">
              <div class="container">
                  <div class="row">

                      <div class="col-sm-3">
                          <div class="logo">
                              <a href="#">Web Portfolio</a>
                          </div>
                      </div>

                      <div class="col-sm-9">
                          <div class="navigation-menu">
                              <div class="navbar">
                                  <div class="navbar-header">
                                      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                          <span class="sr-only">Toggle navigation</span>
                                          <span class="icon-bar"></span>
                                          <span class="icon-bar"></span>
                                          <span class="icon-bar"></span>
                                      </button>
                                  </div>
                                  <div class="navbar-collapse collapse">
                                      <ul class="nav navbar-nav navbar-right">
                                          <li class="active"><a class="smoth-scroll" href="#home">Home <div class="ripple-wrapper"></div></a>
                                          </li>
                                          <li><a class="smoth-scroll" href="#about">About</a>
                                          </li>
                                          <li><a class="smoth-scroll" href="#accountbook" name="accountbook">AccountBook</a>
                                          </li>
                                          <li><a class="smoth-scroll" href="#board" name="board">Board</a>
                                          </li>                                   
                                          <li><a class="smoth-scroll" href="#login" name="login">LogIn</a>
                                          </li>
                                      </ul>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
          </div>

          <div class="home-section-background" data-stellar-background-ratio="0.6">
              <div class="display-table">
                  <div class="display-table-cell">
                      <div class="container">
                          <div class="row">
                              <div class="col-md-12 text-center">
                                  <div class="header-text">
                                      <p>Hi! It's Portfolio</p>
                                      <h2><span class="typing"></span></h2>

                                      <div class="margin-top-60">
                            <a class="button button-style button-style-icon fa fa-long-arrow-right smoth-scroll" href="#portfolio">View Portfolio</a>
                                    </div>

                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
          </div>

      </header>
      <!-- Home & Menu Section End-->

	  <!-- About Section Start -->
	  <section id="about" class="about-section">
	  	<div class="row">
	  		<div class="col-md-6 col-sm-12 col-xs-12">
              <img class="img-responsive" src="" draggable="false" alt="">
            </div>
                
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="about-me section-space-padding">
                  <h2>Skill Set</h2>
                  <p>Java(jdk1.7)<br>
                  	Spring(3.2.9.RELEASE)<br>
                  	Tomcat8.0<br>
                  	Maven<br>
                  	JQuery,Ajax<br>
                  	Bootstrap<br>
                  	MyBatis<br>
                  	Oracle 11g<br>
                  	Eclipse Neon<br>
                  	Github(url:https://github.com/pch8388/origin.git)
                  </p>
                </div>   
            </div>
	  	</div>
	  </section>
	  <!-- About Section End -->
	 
<script type="text/javascript">
	$(document).ready(function(){
		$("a[name='login']").on("click",function(e){
			e.preventDefault();
			fn_login();
		});
		$("a[name='accountbook']").on("click",function(e){
			e.preventDefault();
			fn_accountbook();
		});
		$("a[name='board']").on("click",function(e){
			e.preventDefault();
			fn_board();
		});
	});
	
	function fn_login(){
		location.href="/user/login";
	}
	function fn_accountbook(){
		window.open("/account/account_book");
	}
	function fn_board(){
		window.open("/sample/openBoardList");
	}
</script>
</body>
</html>
