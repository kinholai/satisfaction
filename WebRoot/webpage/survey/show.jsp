<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/webpage/context/top.jsp"%>

<style type="text/css">
div#header {
	text-align: center;
	align: center;
}
div#foot {
	background-color: #99bbbb;
	text-align: center;
	align: center;
}

div#last {
	text-align: center;
	padding: 30px;
}

.test {
	border-bottom: 0px solid #ddd;
	padding: 10px 10px;
	text-align: left;
}

.login-btn {
	width: 170px;
	height: 30px;
	border: none;
	background: #056daa;
	color: #ffffff;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	margin-right: 50px;
}

.row2 {
	border: 0px solid #ddd;
	padding: 10px 10px;
	text-align: center;
}

.target {
	width: 90%;
	height: auto;
	border: 1px solid #ddd;
	text-align: left;
	padding: 5%;
}

.label {
	margin: 20px;
	padding-left: 20px;
	display: inline;
}

.suit{
    margin:30px auto ;
}
</style>

 <div class="container marketing">
      <hr class="featurette-divider">
      <div class="row">
        	<c:if test="${sHead.publishState == 0}">
				<h1  class = "suit">抱歉，该调查问卷未开放，请联系管理员。</h1>
			</c:if>
			<c:if test="${sHead.publishState != 0}">
				<div id="header">
					<h1>
						<font> ${sHead.head }</font>
					</h1>
				</div>
				<form name="surveydata" action="<%=basePath%>surveys/saveAnswers" method="post">
					<input type="hidden" name="sHeadId" id="sHeadId" value="${sHead.id }">
					<input type="hidden" name="num" id="num" value="${num}">
						<div class="row2">
							
							${sHead.descriptionHtml }
								<c:forEach items="${sqList}" var="poVal">
									${poVal.html }
								</c:forEach>
								
						</div>
			
						<div id="last">
							<input type="submit" class="login-btn" value="提交">
						</div>
					</form>
			</c:if>
      </div>

      <hr class="featurette-divider">
      
<%@include file="/webpage/context/bottom.jsp"%>