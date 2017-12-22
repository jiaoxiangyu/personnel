<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员管理系统</title>
<link rel="stylesheet" href="css/info.css">
<link rel="stylesheet" href="css/bootstrap.css">

</head>
<body>
<%@ include file="header.jsp"%> 
		<!-- 完善个人信息 -->
	  <div class="div1 " id="" >
 	<h1 class="personMessageHead">个人基本信息</h1>
         <div class="div3">
                <form action="update" method="post" role="form" class="form-horizontal formm"  >
                     <input type="hidden" name="id" value="${userInfo.id}" > 
                     <div class="form-group">
                         <label class="col-sm-3 control-label">姓名</label>
                         <div class="col-sm-9">
                             <input name="username" type="text" class="form-control" 
                                    placeholder="${userInfo.username}" value="${userInfo.username}">
                         </div>
                     </div>
                     
                     <div class="form-group">
                         <label class="col-sm-3 control-label">性别</label>
                         <div class="col-sm-9">
                             <input name="sex" type="text" class="form-control" 
                                    placeholder="${userInfo.sex}" value="${userInfo.sex}">
                         </div>
                     </div>
                     
                    
                      
                      <div class="form-group">
                         <label class="col-sm-3 control-label">年龄</label>
                         <div class="col-sm-9">
                             <input name="age" type="text" class="form-control"   placeholder="${userInfo.age}"
                             value="${userInfo.age}">
                         </div>
                     </div> 
                     
                     <div class="form-group">
                         <label class="col-sm-3 control-label">民族</label>
                         <div class="col-sm-9">
                             <input name="nation" type="text" class="form-control" 
                                    placeholder="${userInfo.nation}" value="${userInfo.nation}">
                         </div>
                     </div>
                     <div class="form-group">
                         <label  class="col-sm-3 control-label">政治面貌</label>
                         <div class="col-sm-9">
                             <input name="politicalStatus" type="text" class="form-control" 
                                    placeholder="${userInfo.politicalStatus}" value="${userInfo.politicalStatus}">
                         </div>
                     </div>
                     <div class="form-group">
                         <label  class="col-sm-3 control-label">手机号</label>
                         <div class="col-sm-9">
                             <input name="phone" type="text" class="form-control"  value="${userInfo.phone}"
                                    placeholder="${userInfo.phone}">
                         </div>
                     </div>
                     <div class="form-group">
                         <label  class="col-sm-3 control-label">邮箱</label>
                         <div class="col-sm-9">
                             <input name="email" type="text" class="form-control" value="${userInfo.email}"
                                    placeholder="${userInfo.email}">
                         </div>
                     </div>
                     
                     <div class="form-group">
                         <label  class="col-sm-3 control-label">学历</label>
                         <div class="col-sm-9">
                             <input name="education" type="text" class="form-control" value="${userInfo.education}"
                                    placeholder="${userInfo.education}">
                         </div>
                     </div>
                                        
                     <div class="form-group">
                         <label  class="col-sm-3 control-label">行业</label>
                         <div class="col-sm-9">
                             <input name="industry" type="text" class="form-control" 
                                    placeholder="${userInfo.industry}" value="${userInfo.industry}">
                         </div>
                     </div>

                     <div class="form-group">
                         <label  class="col-sm-3 control-label">身份</label>
                         <div class="col-sm-9">
                         	 <c:choose>
						    	<c:when test="${userInfo.type==1}">
						    		<input  type="text" disabled="disabled" class="form-control" placeholder="管理员">
						    	</c:when>
						    	<c:otherwise>
						    		<input type="text" disabled="disabled" class="form-control" placeholder="普通用户">
						    	</c:otherwise>
						    </c:choose>  
                         </div>
                     </div>
                     
                     <div class="form-group">
                         <label  class="col-sm-3 control-label">密码</label>
                         <div class="col-sm-9">
                             <input name="password" type="password" class="form-control" 
                                    placeholder="${userInfo.password}" value="${userInfo.password}">
                         </div>
                     </div>   
                     
                     <div class="form-group">
                         <label  class="col-sm-3 control-label">地址</label>
                         <div class="col-sm-9">
                             <input name="address" type="text" class="form-control" 
                                    placeholder="${userInfo.address}" value="${userInfo.address}">
                         </div>
                     </div>   
	                  <div id="confirm">
	                  		<label></label>
	               	  		<input type="submit" value="确认"  class="btn btn-default btn-primary">
	          		  </div>
          </form> 
        </div>    			
    </div>
    
    <h3 style="color: red;text-align: center;">${msg }</h3>
    <%@ include file="footer.jsp"%> 
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>	
</body>
</html>