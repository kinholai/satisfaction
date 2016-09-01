<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/webpage/context/top.jsp"%>

 <div class="container marketing">
      <hr class="featurette-divider">
	<c:forEach items="${surveys }" var="survey">
      <div class="row">
        <div class="col-md-7 col-md-push-5">
          <h2 class="heading"><c:if test="${survey.prority == 1}"><font color="red">[置顶]</font></c:if>${survey.title }</h2><span class="article_item_time"><fmt:formatDate value="${survey.createTime }" type="date"/></span>
          <p class="lead"><c:if test="${!empty survey.description }">${survey.description }</c:if></p>
          <p><a class="btn btn-default" href="<%=basePath%>surveys/${survey.surveyId }" role="button">查看详情 &raquo;</a></p>
        </div>
        <div class="col-md-5 col-md-pull-7">
        	<c:if test="${empty survey.image }">
	          <img class="image img-responsive center-block" data-src="holder.js/500x500/auto"
	               src="plug-in/img/carver001.jpg" alt="Generic placeholder image">
             </c:if>
             <c:if test="${!empty survey.image }">
	          <img class="image img-responsive center-block" data-src="holder.js/500x500/auto"
	               src="${survey.image }" alt="Generic placeholder image">
             </c:if>
        </div>
      </div>

      <hr class="featurette-divider">
	</c:forEach>
	
      <hr class="divider">
      <div class="row">
        <div class="col-md-4 col-md-push-5">
          <ul class="pagination">
            <c:if test="${page-1>0 }"><li><a href="<%=basePath%>surveys?page=${page-1}">&laquo;</a></li></c:if>
            <li><a href="#">${page }</a></li>
            <c:if test="${page*cpp+1<=size }"><li><a href="<%=basePath%>surveys?page=${page+1}">&raquo;</a></li></c:if>
          </ul>
        </div>
      </div>
      
<%@include file="/webpage/context/bottom.jsp"%>