<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link href="${contextPath}/resources/plugins/tables/css/datatable/dataTables.bootstrap4.min.css" rel="stylesheet" />
<link href="${contextPath}/resources/css/style.css" rel="stylesheet" />

<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 보기</title>
<style>
     #tr_btn_modify{
       display:none;
     }
   
   </style>
</head>

<body>
<h1 style="text-align:center; font-weight: bold;">문의 게시판</h1>
    <div class=qna_box style="width: 100%;  padding: 0%; ">
        <form 
          name="articleForm" 
          method="post"   
          action="${contextPath}"  
          enctype="multipart/form-data"
          style="width: 50%; position: relative; left: 23%; margin: 40px;" >
         
             <div class="form-group"  style="width:30%; height:50px ; "  >
                <input type="text" size="20" class="form-control bg-transparent" name="articleNO" value="${article.compet_qna_id }" placeholder="글번호"  readonly>
             </div> 
             
              <div  style="width:30%;  position: relative; left: 33%; bottom: 70px;">
                <div class="form-group"  >
                  <input type="text" class="form-control bg-transparent" name="date" value="${article.compet_qna_date }" placeholder="등록일자"  readonly>
                </div>
              </div>   
             <!-- 글 작성자 회원 확인 -->
            <div style=" position: relative; bottom: 65px;"> 
              <c:if test="${article.parent_id == 0}">
               <div class="form-group"  >
                <input type="text" class="form-control bg-transparent" name="mem_id" value="${article.mem_id }" readonly placeholder=" 작성자">
            </div> 
            </c:if>
             <!-- 글 작성자 관리자 확인 -->
            <c:if test="${article.parent_id !=  0}">
           <div class="form-group"  >
                <input type="text" class="form-control bg-transparent" name="mem_id" value="${article.admin_id }" readonly placeholder=" 작성자">
            </div>
            </c:if>
            
            <div class="form-group" >
                <input type="text" class="form-control bg-transparent" name="title"  id="i_title"  value="${article.compet_qna_title }" readonly placeholder=" 글제목">
            </div>
            <div class="form-group">
                <textarea class="textarea_editor form-control bg-light" rows="15" name="content"  id="i_content" placeholder="글내용"  readonly>${article.compet_qna_text }</textarea>
            </div>
            <!-- 수정 반영하기  -->
             <div   id="tr_btn_modify"  align="center"  >           
                <button type="submit" class="btn btn-outline-secondary"  onClick="fn_modify_article(articleForm)">수정반영하기</button>
                <button type="submit" class="btn btn-outline-secondary"  onClick="backToList(articleForm)">취소</button>           
             </div>
             <!-- 수정하기 버튼  -->  
             <div id="tr_btn">                       
                  <c:choose>
	                  <c:when test="${loginInfo != null}">
					  	<c:choose>
							<c:when test="${loginStatus == 'member' }">
								<c:if test="${loginInfo.mem_id ==  article.mem_id}">
									<button class="btn btn-outline-secondary" onClick="fn_enable(event)">수정하기</button>
									<button  class="btn btn-outline-secondary" onClick="fn_remove_article(${article.compet_qna_id})">삭제하기</button>
									<button type="button"  id="toList" class="btn btn-outline-secondary" >목록보기</button>
								</c:if>
								<c:if test="${loginInfo.mem_id !=  article.mem_id}">
								<button type="button"  id="toList" class="btn btn-outline-secondary" >목록보기</button>
								</c:if>
							</c:when>
							<c:when  test="${loginStatus == 'admin' }"> 
							    <button class="btn btn-outline-secondary" onClick="fn_enable(event)">수정하기</button>                    
								<button  class="btn btn-outline-secondary" onClick="fn_remove_article(${article.compet_qna_id})">삭제하기</button>                          
								<button type="button" class="btn btn-outline-secondary" onClick="fn_reply_form( ${article.compet_qna_id},${article.compet_id} )">답글쓰기</button>                                       
								<button type="button" id="toList"class="btn btn-outline-secondary">목록보기</button>
							</c:when>						
						</c:choose>
	                  </c:when>  
	                  <c:otherwise>
	                  	<button type="button" id="toList" class="btn btn-outline-secondary">목록보기</button>
	                  </c:otherwise>
	                 
                                                   
             </c:choose>    
                              
                    
             </div>
           
           
           </div>         
        </form>
        
        
        
    </div> 
    
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
	 document.getElementById("toList").addEventListener("click", ()=>{
		location.href="${contextPath}/qna/qna.do?compet_id=${article.compet_id}";
	}); 
	 
// 	  function backToList(){
// 	    location.href="${contextPath}/qna/qna.do?compet_id=${article.compet_id}";

// 	  }
	  function fn_enable(event){
		 	 event.preventDefault();
		 	 document.getElementById("i_title").removeAttribute("readonly");
		 	 document.getElementById("i_content").removeAttribute("readonly");
			 document.getElementById("tr_btn_modify").style.display="block";		 
			 document.getElementById("tr_btn").style.display="none";
		 }
	  function fn_modify_article(obj){
			 obj.action="${contextPath}/qna/modArticle.do";
			 obj.submit();
		 }
	  
	  function fn_remove_article(articleNO, compet_id){
		     event.preventDefault();
			 var form = document.createElement("form");
			 form.setAttribute("method", "post");
			 form.setAttribute("action", "${contextPath}/qna/removeArticle.do?articleNO=${article.compet_qna_id}&compet_id=${article.compet_id}");
		     var articleNOInput = document.createElement("input");
		     articleNOInput.setAttribute("type","hidden");
		     articleNOInput.setAttribute("name","articleNO");
		     articleNOInput.setAttribute("value", articleNO);
		     articleNOInput.setAttribute("name","compet_id");
		     articleNOInput.setAttribute("value", compet_id);
			 /* form.action = "${contextPath}/compet/removeArticle.do"; */
		     form.appendChild(articleNOInput);
		     document.body.appendChild(form);
		     form.submit()	 
		 }

	  function fn_reply_form(parentNO, compet_id){
			 var form = document.createElement("form");
			 form.setAttribute("method", "get");
			 form.setAttribute("action", "${contextPath}/qna/qnaReplyForm.do");
		     var parentNOInput = document.createElement("input");
		     parentNOInput.setAttribute("type","hidden");
		     parentNOInput.setAttribute("name","parentNO");
		     parentNOInput.setAttribute("value", parentNO);
		     console.log('parentNo', parentNO);
		     
		     var competIdInput = document.createElement("input");
		     competIdInput.setAttribute("name","compet_id");
		     competIdInput.setAttribute("value", compet_id);
		     console.log('compet_id', compet_id);
		     
		     form.appendChild(parentNOInput);
		     form.appendChild(competIdInput);
		     document.body.appendChild(form);
			 form.submit();
		 }
		 
</script>
</body>
</html>