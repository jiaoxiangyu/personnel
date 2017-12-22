<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员管理系统</title>
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/bootstrap.css">

</head>
<body>
	<%@ include file="header.jsp"%> 
	
    <!-- 主题 -->
        <div class=" body">
                <div class="bodyOne" >
                        <div class=" sector" id="sex">
                         
                        </div>
                        <div class=" sector" id="education">
                            
                        </div>
                        <div class=" sector" id="industry">
                          
                        </div>        
                </div>

                <div class=" sector bodyTwo" id="address">
                    
                </div>
            
        </div>      

	<%@ include file="footer.jsp"%> 
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<!-- 引入 ECharts 文件 -->
    <script src="js/echarts.min.js"></script>
    <script src="js/china.js"></script>
<script type="text/javascript" src="js/drawEcharts.js"></script>
</body>
</html>