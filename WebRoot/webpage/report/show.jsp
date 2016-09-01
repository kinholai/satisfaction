<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/webpage/context/top.jsp"%>

 <div class="container marketing">
      <hr class="featurette-divider">
      <div class="row">
        ${report.content }
        <br><br><br>
        <c:if test="${!empty report.attachment }"><center><a class="btn btn-success" href="${ctxname }/satisfactionController.do?download&id=${report.id }">下载原文件 &raquo;</a></center></c:if>
      </div>

      <hr class="featurette-divider">
      
<%@include file="/webpage/context/bottom.jsp"%>