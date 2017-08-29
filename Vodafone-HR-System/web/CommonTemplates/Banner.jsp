<%-- 
    Document   : Banner
    Created on : Jul 9, 2012, 11:24:14 AM
    Author     : ahmed_amer
--%>

<%@page import="com.vodafone.controller.constants.constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <table align="center" id="banner" width="60%"  border="1" >
            <tr>
                <td align="left" style="width:130px" colspan="3">
                    <a href="<%= constants.VODAFONE_WEBSITE%>">
                        <img src="<%= request.getContextPath()%>/<%= constants.VODAFONE_LOGO%>" height="100" width="637" 
                             alt="Vodafone Serviecs"/>
                    </a>
                </td>
                
            </tr>
        </table>
    </body>
</html>
