<%-- 
    Document   : template
    Created on : Aug 7, 2014, 1:06:47 PM
    Author     : Artsiom_Chuiko
--%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <html>
       <head>
           <title><tiles:getAsString name="title" ignore="true" /></title>
           <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
       </head>
	   
       <body>
            <div id="header">
                <tiles:insert attribute="header"/>
            </div>
            <div class="content">
                <div id="menu">
                    <tiles:insert attribute="menu"/>
                </div>
                <div id="body"> 
                     <tiles:insert attribute="body"/>
                </div>
            </div>
            <div id="footer">
                 <tiles:insert attribute="footer" />
            </div>
       </body>
  </html>
