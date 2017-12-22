<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!DOCTYPE html ">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/header.css">
</head>
<body>
	<nav class="navbar navbar-default menu" role="navigation">
		<div class="container-fluid">	
			<div class="navbar-header">				
				<div class="navbar-brand logo">人员管理系统&nbsp;</div>
			</div>	
			<div>
		        <ul class="nav navbar-nav" id="menu">
		            <li><a href="index">首页</a></li>
		            <c:if test="${user.type==1}">
		            	<li><a href="getData">人员管理</a></li>
		            </c:if>
		            
		        </ul>
		     </div>
		     
		     <ul class="nav navbar-nav navbar-right">
			      <li><a href="toUpdate?id=${user.id}"><img class="headmag" src="images/headr.png"> ${user.username}</a></li>
			      <li><a href="logout"><img class="headmag" src="images/logout.png"></a></li>
			      <c:if test="${user.type==1}">
			      	 <li><a href="toAdd"><img class="headmag" src="images/add.png"></a></li>
			      </c:if>
			     
		    </ul>
	    </div>
	</nav>
<script src="js/jquery.js"></script>	
<script type="text/javascript">
var urlstr = location.href;  
//alert(urlstr);  
var urlstatus=false;  
$("#menu a").each(function () {  
	  if ((urlstr + '/').indexOf($(this).attr('href')) > -1&&$(this).attr('href')!='') {  
	    $(this).addClass('cur'); urlstatus = true;  
	  } else {  
	    $(this).removeClass('cur');  
	  }  
}); 

if (!urlstatus) {
	$("#menu a").eq(0).addClass('cur');
} 
</script>	

<script src="js/bootstrap.js"></script>
</body>
</html>