<%-- 
    Document   : newslist
    Created on : Aug 7, 2014, 1:22:54 PM
    Author     : Artsiom_Chuiko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insert page="template.jsp" flush="true">
    <tiles:put name="title" value="News list" />
    <tiles:put name="header" value="header.jsp" /> 
    <tiles:put name="menu" value="menu.jsp" />
    <tiles:put name="body" value="body.jsp" />
    <tiles:put name="footer" value="footer.jsp" />
</tiles:insert>
