<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    

<!DOCTYPE html>
<html class="h-100" lang="en">

<head>
    <meta charset="utf-8">

</head>

<div class="container">
    <!-- sub-header -->
    <div class="row my-3">
        <h2><b>대회 목록</b></h2>
        <hr class="w-100 my-2">
    </div>
    <!-- dropdowns -->
    <div class="row my-lg-3">
        <div class="col-lg-9">
            <div class="row mb-3">
                <div class="basic-dropdown col-lg-2 mb-1">
                    <div class="dropdown">
                        <button type="button" id="categ-btn" class="btn btn-success dropdown-toggle"
                            data-toggle="dropdown">
                            <b>${dropdown }</b>
                        </button>
                        <div class="dropdown-menu" id="dropdown-menu">
							<a class="dropdown-item" href="#">전체보기</a>
                        	<c:forEach var="categ" items="${categList }">
								<a class="dropdown-item" href="#">${categ.categ_name }</a>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:forEach var="compet" items="${competList }">
		<!-- competition start -->
		<div class="row">
			<div class="card mb-3 col-lg-12">

				<div class="row g-0">
					<div class="col-lg-4">
						<img src="/ocp/competFile_download.do?compet_id=${compet.compet_id }&compet_file_name=${compet.compet_file_name }&compet_file_type=${compet.compet_file_type}" class="img-fluid h-100 p-1" alt="...">
					</div>
					<div class="col-lg-6">
						<div class="card-body">
							<h5 class="card-title">${compet.compet_name }</h5>
							<p class="card-text">${compet.compet_text }</p>
							<p class="card-text"><small class="text-muted">${compet.compet_start } ~ ${compet.compet_end}</small></p>
						</div>
					</div>
					<div class="col-lg-2 mb-md-3 my-auto text-center">
						<div class="btn btn-outline-success" onclick="location.href='${contextPath }/compet/competInfo.do?compet_id=${compet.compet_id}'">
							<b>자세히 보기</b>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- competition end -->
	</c:forEach>

</div>

<!--**********************************
        Scripts
    ***********************************-->
<script>
    const dropdownItems = Array.from(document.getElementById("dropdown-menu").children); //카테고리 드랍다운 메뉴들

    dropdownItems.forEach(e => {
        e.addEventListener('click', (evt) => {
            window.location.href = '${contextPath}/compet/competList.do?categ_name=' + evt.target.innerText;
        })
    });
</script>
</body>

</html>