<%-- 
    Document   : MENU
    Created on : Jul 9, 2012, 11:25:54 AM
    Author     : ahmed_amer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/tableStyle.css" /> 
    </head>
    <body>
    <td valign="top">
        <br/>
        <table id="table-2" width="100%" border="1" cellspacing="0" cellpadding="3">
            <tr>
                <th style="font-weight: bold">HR SYSTEM MAIN MENU</th>
            </tr>
            <tr>
                <td class="menu">
                    <a href="<%= request.getContextPath()%>/Employee/All_Employee_Template.jsp">Show&nbsp;All&nbsp;Employees  </a><br/>
                    <a href="<%= request.getContextPath()%>/Employee/AddEmployee_Template.jsp">Add&nbsp;Employee</a><br/>
                    <a href="<%= request.getContextPath()%>/CommonTemplates/AboutUs.jsp">About&nbsp;Us</a><br/>
                </td>
            </tr>
        </table>
    </td>
</body>
</html>
