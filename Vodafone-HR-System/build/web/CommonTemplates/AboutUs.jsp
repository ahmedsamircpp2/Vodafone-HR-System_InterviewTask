<%-- 
    Document   : AboutUs
    Created on : Jul 11, 2012, 8:21:52 AM
    Author     : ahmed_amer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="../CommonTemplates/Banner.jsp"   />
        <table align="center" style="width: 60%">
            <tr>
                <jsp:include   page="../CommonTemplates/MENU.jsp" />

                <td align="center" valign="top">
                    <img src="<%= request.getContextPath()%>/images/myPicture.jpg" height="300" width="400" />
                    <p>Author : Ahmed Samir Amer [ 01095967408- 01125727019 ]</p>
                    <p>Vodafone HR System version 1.0</p>
                    <p>Release Date : 11/7/2012</p>
                </td>
            </tr>
        </table>

    </body>
</html>
