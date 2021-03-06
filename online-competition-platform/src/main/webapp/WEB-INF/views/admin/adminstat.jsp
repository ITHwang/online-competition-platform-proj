<%@ page language="java" contentType="text/html; charset=UTF-8"
  	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<%
request.setCharacterEncoding("UTF-8");
%>



<!--     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> 차트 링크 -->
<!-- <script -->
<%-- 	src="${contextPath}/resources/plugins/jqueryui/js/jquery-ui.min.js"></script> --%>
<%-- <script src="${contextPath}/resources/plugins/moment/moment.min.js"></script> --%>
<!-- <script -->
<%-- 	src="${contextPath}/resources/plugins/fullcalendar/js/fullcalendar.min.js"></script> --%>
<!-- <script -->
<%-- 	src="${contextPath}/resources/js/plugins-init/fullcalendar-init.js"></script> --%>
   

<style>
.chart{
       display: flex;
       justify-content: center;
       position: relative;
       left: 240px;
   } 
</style>
</head>

<body>
   
    <div class="container">
        <div class="row my-3">
    <span style="font-size: 25px; font-weight:bold; color:black;">카테고리별 통계</span>
        </div>
        <div class="row">
                    <!-- Pie Chart -->
                    <div class="col-lg-12">
                        <div class="card">
                        
                            <div class="card-body">
                            <div class="row">
                            <div class="col-lg-3">
                            	<h5 class="text-center">컨텐츠 개수</h5>
                                <canvas id="pieChart" width="300" height="250"></canvas>
                               </div>
                               <div class="col-lg-3">
                            	<h5 class="text-center">좋아요 개수</h5>
                                <canvas id="pieChart1" width="300" height="250"></canvas>
                                </div>
                                <div class="col-lg-3">
                            	<h5 class="text-center">댓글 개수</h5>
                                <canvas id="pieChart2" width="300" height="250"></canvas>
                                </div>
                                <div class="col-lg-3">
                            	<h5 class="text-center">조회수 개수</h5>
                                <canvas id="pieChart3" width="300" height="250"></canvas>
                                </div>
                               </div>
                            </div>
                        </div>
                    </div>
                </div>
                </div>

    <div class="container-fluid">
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">전체조회</h4>
							<div class="table-responsive">
							<table class="table table-striped table-bordered zero-configuration">
									
											
											<div class="general-button">
									            <button type="button"  class="float-right" id="node" onclick="downloadEXCEL()">엑셀출력</button>
											
												
										    </div>
								    
									
									
                               <div class="row">
										<div class="col-sm-12">
										<div class="table-responsive">
											<table class="table table-striped table-bordered zero-configuration">
												<thead id="listhead">
													<tr role="row"  align="center">
														<th class="No" tabindex="0"
															
															style="width: 95.7188px;">No.</th>
														<th class="categ_name" tabindex="0"
															
															id="categ_name"
															style="width: 145.531px;">카테고리</th>
														<th class="compet_name" tabindex="0"
														
															id="compet_name"
															style="width: 145.531px;">대회</th>
														<th class="cotents_name" tabindex="0"
															
															id="cotents_name"
															style="width: 236.656px;">컨텐츠</th>
														<th class="mem_nickname" tabindex="0"
															
															id="mem_nickname"
															style="width: 105.656px;">닉네임</th>
														<th class="contents_processing_date" tabindex="0"
															
															id="contents_processing_date"
															style="width: 95.7188px;">작성일</th>
														<th class="contents_view" tabindex="0"
															
															id="contents_view"
															style="width: 79.8906px;">조회수</th>
														<th class="likes1" tabindex="0"
															
															id="likes1"
															style="width: 90px;">좋아요</th>
														<th class="cmt1" tabindex="0"
															
															id="cmt1"
															style="width: 90px;">댓글</th>
														
													</tr>
												</thead>
											
												 <tbody id="admin_StatList">
												
                                            <c:if test="${adminAllList !=null }">
                                                <c:forEach var="admin_StatList" items="${adminAllList}" varStatus="i">
                                                    <tr align="center">
                        								<td width="5%">${i.count}</td>
                                                        <td width="15%" id="ctg_name">${admin_StatList.ctg_name}</td>
                                                        <td width="20%" id="compet_name">${admin_StatList.compet_name }</td>
                                                        <td width="20%" id="contents_name">${admin_StatList.contents_name}</td>
                                                        <td width="10%" id="mem_nickname">${admin_StatList.mem_nickname}</td>
                                                        <td width="15%" id="contents_processing_date">${admin_StatList.contents_processing_date}</td>
                                                        <td width="12%" id="contents_view">${admin_StatList.contents_view}</td>
                                                        <td width="18%" id="likes1">${admin_StatList.likes1}</td>
                                                        <td width="15%" id="cmt1">${admin_StatList.cmt1}</td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>

                                        </tbody>
											</table>
											</div>
										</div>
									</div>
								
								
							</table>
						</div>
					</div>
				</div>
			</div>
		
	</div>
</div>
	<script src="${contextPath }/resources/plugins/chart.js/Chart.bundle.min.js"></script>
	
	
    <script>
	    const ctgNames = []
	    const ctgNamess = []
	    const ctgNamesss = []
	    const ctgNamessss = []
	    const Numcontents = []
	    const Numlikes = []
	    const Numcmt = []
	    const Numview = [] 
    	<c:forEach items="${adminContentsList}" var="adcontents">
	    	ctgNames.push("${adcontents.ctg_name}");
	    	Numcontents.push("${adcontents.cnt}");
	    </c:forEach>
	    
	    <c:forEach items="${adminLikesList}" var="adlikes">
    	ctgNamess.push("${adlikes.ctg_name}");
    	Numlikes.push("${adlikes.like}");
        </c:forEach>
        
        <c:forEach items="${adminCmtList}" var="adcmt">
    	ctgNamesss.push("${adcmt.ctg_name}");
    	Numcmt.push("${adcmt.cmt}");
        </c:forEach>
        
        <c:forEach items="${adminViewList}" var="adview">
    	ctgNamessss.push("${adview.ctg_name}");
    	Numview.push("${adview.viw}");
        </c:forEach>
    	
	    	
	    	
        
      //pie chart
        var ctx = document.getElementById("pieChart");
        ctx.height = 300;
        var myChart = new Chart(ctx, {
            type: 'pie',
            data: {
                datasets: [{
                    data: Numcontents,
                    backgroundColor: [
                        "rgba(051, 204, 204,0.9)",
                        "rgba(204, 255, 000,0.8)",
                       
                    ],
                    hoverBackgroundColor: [
                        "rgba(051, 204, 204,0.9)",
                        "rgba(204, 255, 000,0.8)",
                    ]

                }],
                labels: ctgNames
            },
            options: {
                responsive: true
            }
        });
        
        ctx = document.getElementById("pieChart1");
        ctx.height = 300;
        myChart = new Chart(ctx, {
            type: 'pie',
            data: {
                datasets: [{
                    data: Numlikes,
                    backgroundColor: [
                        "rgba(117, 113, 249,0.9)",
                        "rgba(117, 113, 249,0.7)",
                       
                    ],
                    hoverBackgroundColor: [
                        "rgba(117, 113, 249,0.9)",
                        "rgba(117, 113, 249,0.7)",
                    ]

                }],
                labels: ctgNamess
            },
            options: {
                responsive: true
            }
        });
        
        ctx = document.getElementById("pieChart2");
        ctx.height = 300;
        myChart = new Chart(ctx, {
            type: 'pie',
            data: {
                datasets: [{
                    data: Numcmt,
                    backgroundColor: [
                        "rgba(117, 113, 249,0.9)",
                        "rgba(117, 113, 249,0.7)",
                        "rgba(117, 113, 249,0.7)",
                        "rgba(117, 113, 249,0.7)",
                    ],
                    hoverBackgroundColor: [
                        "rgba(117, 113, 249,0.9)",
                        "rgba(117, 113, 249,0.7)",
                        "rgba(117, 113, 249,0.7)",
                        "rgba(117, 113, 249,0.7)",
                    ]

                }],
                labels: ctgNamesss
            },
            options: {
                responsive: true
            }
        });
        
        ctx = document.getElementById("pieChart3");
        ctx.height = 300;
        myChart = new Chart(ctx, {
            type: 'pie',
            data: {
                datasets: [{
                    data: Numview,
                    backgroundColor: [
                        "rgba(255, 102, 000,0.9)",
                        "rgba(255, 153, 051,0.7)",
                       
                       
                    ],
                    hoverBackgroundColor: [
                        "rgba(255, 102, 000,0.9)",
                        "rgba(255, 153, 051,0.7)",
                        
                    ]

                }],
                labels: ctgNamessss
            },
            options: {
                responsive: true
            }
        }); 
        
        
     
       

    </script>
     <script>
   function downloadEXCEL() {
      const fileName = "statView.csv";
      const EXCEL = convertNodeToExcelString();

      var link = document.createElement("a");
      var blob = new Blob([ "\uFEFF" + EXCEL ], {
         type : 'text/;charset=utf-8'
      });
      var url = URL.createObjectURL(blob);
      $(link).attr({
         "download" : fileName,
         "href" : url
      });
      link.click();
   }

   function convertNodeToExcelString() {
      var result = "";
      var node = $("#listhead").children();

      $(node).each(function(index, value) {
         result += $(value).find("#categ_name").text() + ",";
         result += $(value).find("#compet_name").text() + ",";
         result += $(value).find("#cotents_name").text() + ",";
         result += $(value).find("#mem_nickname").text() + ",";
         result += $(value).find("#contents_processing_date").text() + ",";
         result += $(value).find("#contents_view").text() + ",";
         result += $(value).find("#likes1").text() + ",";
         result += $(value).find("#cmt1").text() + ",\n";
      })

      var itemNodes = document.getElementById("admin_StatList").children;
      
      const trList = Array.prototype.slice.call( itemNodes )
      for (tr of trList){
    	  const tdList = Array.prototype.slice.call( tr.children );
    	  for (td of tdList){
           result += td.innerText + ",";
    	  }
    	  result += "\n";
      }
//       $(itemNodes).each(function(index, value) {
//          result += $(value).find("#ctg_name").text() + ",";
//          result += $(value).find("#compet_name").text() + ",";
//          result += $(value).find("#contents_name").text() + ",";
//          result += $(value).find("#mem_nickname").text() + ",";
//          result += $(value).find("#contents_processing_date").text() + ",";
//          result += $(value).find("#contents_view").text() + ",";
//          result += $(value).find("#likes1").text() + ",";
//          result += $(value).find("#cmt1").text() + ",\n";
//       })

      return result;
   }
</script>
</body>

</html>
