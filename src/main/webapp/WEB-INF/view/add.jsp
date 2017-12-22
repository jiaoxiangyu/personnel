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
		
	  <div class="div1 " id="" >
 	<h1 class="personMessageHead">注 册</h1>
         <div class="div3">
                <form action="add" method="post" role="form" class="form-horizontal formm"  >
                    
                     <div class="form-group">
                         <label class="col-sm-3 control-label">姓名</label>
                         <div class="col-sm-9">
                             <input name="username" type="text" class="form-control" >
                         </div>
                     </div>
                     
                     <div class="form-group">
                         <label class="col-sm-3 control-label">性别</label>
                         <div class="col-sm-9">
                             <input name="sex" type="text" class="form-control" >
                         </div>
                     </div>
                     
                    
                      
                      <div class="form-group">
                         <label class="col-sm-3 control-label">年龄</label>
                         <div class="col-sm-9">
                             <input name="age" type="number" class="form-control"  >
                         </div>
                     </div> 
                     
                     <div class="form-group">
                         <label class="col-sm-3 control-label">民族</label>
                         <div class="col-sm-9">
                             <input name="nation" type="text" class="form-control" >
                         </div>
                     </div>
                     <div class="form-group">
                         <label  class="col-sm-3 control-label">政治面貌</label>
                         <div class="col-sm-9">
                             <input name="politicalStatus" type="text" class="form-control" >
                         </div>
                     </div>
                     <div class="form-group">
                         <label  class="col-sm-3 control-label">手机号</label>
                         <div class="col-sm-9">
                             <input name="phone" type="text" class="form-control" >
                         </div>
                     </div>
                     <div class="form-group">
                         <label  class="col-sm-3 control-label">邮箱</label>
                         <div class="col-sm-9">
                             <input name="email" type="text" class="form-control" >
                         </div>
                     </div>
                     
                     <div class="form-group">
                         <label  class="col-sm-3 control-label">学历</label>
                         <div class="col-sm-9">
                             <input name="education" type="text" class="form-control" >
                         </div>
                     </div>
                                        
                     <div class="form-group">
                         <label  class="col-sm-3 control-label">行业</label>
                         <div class="col-sm-9">
                             <input name="industry" type="text" class="form-control" >
                         </div>
                     </div>

                     <div class="form-group">
                         <label  class="col-sm-3 control-label">身份</label>
                         <div class="col-sm-9">
                         	<select name="type" class="selectpicker form-control"
                         	 style="width: 300px;">
                         		<option value="1">管理员</option>
                         		<option value="0">普通用户</option>
                         	</select>                         	
                         </div>
                     </div>
                     
                     <div class="form-group">
                         <label  class="col-sm-3 control-label">密码</label>
                         <div class="col-sm-9">
                             <input name="password" type="password" class="form-control">
                         </div>
                     </div>   
                     
                     <div class="form-group">
                         <label  class="col-sm-3 control-label">地址</label>
                         <div class="col-sm-9">
                             <input name="address" type="text" class="form-control" >
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