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
        <title><%= constants.APPLICATION_NAME%></title>
    </head>

    <body>
        <jsp:include page="CommonTemplates/Banner.jsp" />
        <table align="center" style="width: 60%" >
            <tr>
                <td>
                    <jsp:include  page="CommonTemplates/MENU.jsp" />
                </td>
                <td align="right" valign="bottom">
                    <img src="<%= request.getContextPath()%>/images/vodafone/right.jpg" height="270" width="400" />
                </td>
            </tr>
        </table>

    </body>
</html>
