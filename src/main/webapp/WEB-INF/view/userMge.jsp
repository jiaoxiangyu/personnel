<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员管理系统</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/userMge.css">
<link rel="stylesheet" href="css/modal.css">
<script type="text/javascript">
	function search() {
		 var a=document.getElementById("search").value; 
		 
		 document.document.getElementById("searchFrom").action="getData?search="+a;
		 document.document.getElementById("searchFrom").submit(); 
	}
</script>
</head>
<body>
	<%@ include file="header.jsp"%> 
	<div class="cont">
		<div class = "cont2">
			
			  <div class = "conttwo">
			  	<form action="getData" method="get" id="searchForm">
			  		<input type="search" name="search" id="search" class = "inputsearch">
			  		<input type="submit" value="搜索" class = "inputsearch2" onclick="search()">
			  		
			  	</form>
			 </div>
			 <div class ="conttwo aright">
			  	<a href="javascript:void(0)" data-toggle="modal" data-target="#myModal2">导入 </a>
			  	<a href="javascript:void(0)" data-toggle="modal" data-target="#myModal"> 导出</a> 	
			 </div>
		</div>
	  
	  <table class="table table-striped table-hover " >	  
	  <thead>
	    <tr>	      
	      <th>姓名</th>
	      <th>性别</th>
	      <th>年龄</th>
	      <th>民族</th>
	      <th>学历</th>
	      <th>政治面貌</th>
	      <th>行业</th>
	      <th>地址</th>
	      <th>手机号</th>
	      <th>邮箱</th>
	      <th>身份</th>
	      <th>修改</th>
	      <th>删除</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<c:forEach var="u"   items="${pc.data}"   varStatus="i">
	  		<tr>	      
		      <td>${u.username}</td>
		      <td>${u.sex}</td>
		      <td>${u.age}</td>
		      <td>${u.nation}</td>
		      <td>${u.education}</td>
		      <td>${u.politicalStatus}</td>
		      <td>${u.industry}</td>
		      <td>${u.address}</td>
		      <td>${u.phone}</td>
		      <td>${u.email}</td>
		      <c:choose>
		      	<c:when test="${u.type==0}">
		      		<td>管理员</td>
		      	</c:when>
		      	<c:otherwise>
		      		<td>普通用户</td>
		      	</c:otherwise>
		      </c:choose>
		      <td><a href="toUpdate?id=${u.id}">修改</a></td>
		      <td><a href="delete?id=${u.id}">删除</a></td>		      
		    </tr>
	  	</c:forEach>

	  </tbody>
	</table>
	
	<div id="pagecut">
				<ul class="pagination">
					<li><a href="getData?curr=${pc.prePage}&search=${search}">上一页</a></li>
					<c:if test="${1 < pc.currentPage -3}">
						<li><a href="getData?curr=1&search=${search}">1</a></li>
					</c:if>

					<c:forEach var="i"
						begin="${pc.currentPage-3>0?pc.currentPage-3:1 }"
						end="${pc.currentPage+3>pc.pageNum?pc.pageNum:pc.currentPage+3  }">
						<c:choose>
							<c:when test="${i>0 && i == pc.currentPage }">
								<li class="active"><a
									href="getData?curr=${i}&search=${search}">${i}</a></li>
							</c:when>

							<c:when test="${i>0 && i != postPS.currentPage }">
								<li><a href="getData?curr=${i}&search=${search}">${i}</a></li>
							</c:when>
						</c:choose>
					</c:forEach>
					<li><a
						href="getData?curr=${pc.nextPage}&search=${search}">下一页</a></li>
				</ul>

			</div>

		

		<%-- <c:if test="${ empty pc.data}">
			<h3
				style="height: 200px; margin-left: 550px; margin-top: 160px; color: #DB0B18;">暂时没有党费缴纳记录！</h3>
		</c:if> --%>
	</div>
	<h3 style="color: red;text-align: center;">${msg}</h3>
	
	<div class="modal fade" id="myModal" tabindex="-1"><!--淡入淡出在最外层添加-->
        <!--窗口声明-->
    	<div class="modal-dialog modal-md"><!--小窗口在窗口声明处添加-->
        <!-- modal-content 内容声明 -->
            <div class="modal-content modalContent">
                <!--头部-->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                    <h4 class="modal-title">人员信息Excel导出</h4>
                </div>
                <!--主体-->
                <div class="modalbody">
				    <div class="container container-width formdiv">
						
				       <form class="form-horizontal " role="form" action="outputExcel" method="post">
							<div class=" fromgroup">
								<div class="col-sm-6">
									<select name="condition" class="selectpicker form-control ">
										<option value="all">所有</option>
										<option value="sex">性别</option>
										<option value="education">学历</option>
										<option value="industry">行业</option>										
									</select>
								</div>
								<div class="col-sm-6">
									<input name="content" type="text" class="form-control" id="name" 
                                           placeholder="内容">
								</div>
							</div>
							 <!--注脚-->
			                <div class="confirm">
			                	<input type="submit" value="确认" class="btn  btn-primary ">
			                </div>
						</form>
				    </div>
    
                </div>
               
            </div>
    	</div>
    </div>
	
	<div class="modal fade" id="myModal2" tabindex="-1">
    	<div class="modal-dialog modal-md">
            <div class="modal-content modalContent2">
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                    <h4 class="modal-title">人员信息Excel导入</h4>
                </div>
                <!--主体-->
                <div class="modalbody">
				    <div class="container container-width formdiv">
						
				       <form class="form-horizontal " role="form" action="inputExcel" method="post"  enctype="multipart/form-data">
							<div class=" fromgroup">
								
								<div class="col-sm-12 ">
								<input name="file" class="fileinput" type="file" value = "000000">
								</div>
							</div>
							<div class=" confirm2">
                				<button type="submit" class="btn  btn-primary ">确认</button>
                			</div>
						</form>
				    </div>
    
                </div>
                <!--注脚-->
                
            </div>
    </div>
</div>

	
	
	<%@ include file="footer.jsp"%> 
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>	
</body>
</html>