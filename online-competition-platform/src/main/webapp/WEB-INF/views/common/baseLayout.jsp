<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:insertAttribute name="title" /></title>

<link href="${contextPath}/resources/css/style.css" rel="stylesheet">
<link href="${contextPath }/resources/plugins/tables/css/datatable/dataTables.bootstrap4.min.css" rel="stylesheet">
<!-- 구글 폰트 설정: Noto Sans Korean -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link href="${contextPath}/resources/css/font.css" rel="stylesheet">

<!-- 파비콘 -->
<link rel="shortcut icon" href="https://img.icons8.com/metro/40/000000/share.png">

</head>
<body>
	<div id="outer_wrap">
		<div id="wrap">
			<header>
				   <tiles:insertAttribute name="header" />
			</header>
			<div class="clear"></div>
			<article>
			 	<tiles:insertAttribute name="body" />
			</article>
			<div class="clear"></div>
			<footer>
        		<tiles:insertAttribute name="footer" />
        	</footer>
		</div>
    </div>      
    
        <!--**********************************
        Scripts
    ***********************************-->
    <script src="${contextPath }/resources/plugins/common/common.min.js"></script>
    <script src="${contextPath }/resources/js/custom.min.js"></script>
    <script src="${contextPath }/resources/js/settings.js"></script>
    <script src="${contextPath }/resources/js/gleek.js"></script>
    <script src="${contextPath }/resources/js/styleSwitcher.js"></script>
    
    <!--**********************************
        Scripts(qna.jsp)
    ***********************************-->
<script src="${contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
<script src="${contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
<script src="${contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
    
      	
</body>      
</html>