<%-- 
    Document   : index
    Created on : Jul 9, 2012, 4:42:36 AM
    Author     : ahmed_amer
--%>

<%@page import="com.vodafone.controller.constants.constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/tableStyle.css" /> 
        <title><%= constants.APPLICATION_NAME%></title>
    </head>
    <body>
        <jsp:include page="../CommonTemplates/Banner.jsp"   />
        <table align="center" style="width: 60%">
            <tr>
                <jsp:include   page="../CommonTemplates/MENU.jsp" />

                <td align="center" valign="top">
                    <jsp:include   page="AddEmployee.jsp"  />
                </td>
            </tr>
        </table>
    </body>
</html>
