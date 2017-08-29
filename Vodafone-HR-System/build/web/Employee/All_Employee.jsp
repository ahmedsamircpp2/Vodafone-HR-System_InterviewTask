<%-- 
    Document   : All_Employee
    Created on : Jul 9, 2012, 10:01:45 AM
    Author     : ahmed_amer
--%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.Set"%>
<%@page import="com.vodafone.db.model.dbutils.HR_DB_Helper"%>
<%@page import="com.vodafone.db.model.dto.Employee"%>
<script src="<%= request.getContextPath()%>/js/jquery.js" type="text/javascript" language="javascript"></script>
<script type="text/javascript" language="javascript">

    $(function () {

        var offsetX = 20;
        var offSetY = 10;

        $("a img").hover(function (e) {

            var myhref = $(this).attr("src");
            $('<img id="largeImg" src ="' + myhref + '"/>')
            .css('top', e.pageY + offSetY)
            .css('left', e.pageX + offsetX).appendTo('body');
        }, function () {
            $('#largeImg').remove();

        })


        $("a").mousemove(function (e) {

            $('#largeImg').css('top', e.pageY + offSetY).css('left', e.pageX + offsetX);

        })

    });
    
</script>
<style type="text/css">
    a img
    {
        border:none;
        width:100px;
        height:80px;
    }
    #largeImg
    {
        position:absolute;
        width:200px;
        height:200px;
        border:4px solid Gray;
    }

</style>

<html>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/tableStyle.css" /> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <div align="center" >
            <form action="All_Employee_Template.jsp?type=search" >
                <table>
                    <tr>
                        <th></th>
                        <th>Search</th>
                        <th>
                            <select name="searchColumn">
                                <option value="empId" >EmployeeID</option>
                                <option value="name" >EmployeeName</option>
                                <option value="deptId" >DepartmentID</option>
                                <option value="gender" >Gender</option>
                            </select>
                        </th>
                        <th> <input type="text" name="searchedValue" /> </th>
                        <th> <input type="submit" name="search" /> </th>
                        <th></th>
                    </tr>
                </table>
            </form>
            <table  id="table-2" >
                <!--                <caption> <h1 > ALL Employees </h1> </caption>-->

                <thead>
                    <tr>
                        <th>Employee-Name</th>
                        <th>Employee-Mobile</th>
                        <th>Gender</th>
                        <th>IMAGE URL</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody>
                    <%
                        HR_DB_Helper obj = new HR_DB_Helper();
                        Set<Employee> allEmployees = null;
                        String path = getServletContext().getRealPath("/");
                        String newpath = "";
                        newpath += path.substring(0, path.length() - 11);
                        newpath += "\\web\\" + "images\\upload\\";


                        String queryString = request.getQueryString();
                        String actionType =  "";
                        //if (queryString!=null) {
                         //   actionType = queryString.split("&")[1].split("=")[1];
                       // }


                        if ( queryString!=null) {
                            String searchColumn = request.getParameter("searchColumn");
                            String value = request.getParameter("searchedValue");

                            if (searchColumn.equals("empId")) {
                                try {
                                    allEmployees = obj.select_By_ID(Integer.parseInt(value));
                                } catch (Exception ex) {
                                }
                            } else if (searchColumn.equals("name")) {
                                try {
                                    allEmployees = obj.select_By_Name(value);
                                } catch (Exception ex) {
                                }
                            } else if (searchColumn.equals("deptId")) {
                                try {
                                    allEmployees = obj.select_By_DepartmentID(Integer.parseInt(value));
                                } catch (Exception ex) {
                                }
                            } else if (searchColumn.equals("gender")) {
                                try {
                                    allEmployees = obj.select_By_Gender(value);
                                } catch (Exception ex) {
                                }
                            }
                        } else {

                            allEmployees = obj.All_Employees();
                        }
                        if(allEmployees ==null) allEmployees =new HashSet<Employee>(0);
                        for (Iterator it = allEmployees.iterator(); it.hasNext();) {
                            Employee employee = (Employee) it.next();
                    %>
                    <tr>
                        <td><%= employee.getStrEmployeeFullname()%></td>
                        <td><%= employee.getStrMobile()%></td>
                        <td><%= employee.getEnumGender()%></td>
                        <td> 
                            <a href="<%= request.getContextPath()%>/images/upload/<%=employee.getStrImageUrl()%>">
                                <img src="<%= request.getContextPath()%>/images/upload/<%=employee.getStrImageUrl()%>" 
                                     alt="Loading ....."  height="70" width="100" /> 
                            </a>
                        </td>
                        <td>
                            <a href="UpdateEmployee.jsp?employeeName=<%= employee.getStrEmployeeFullname()%>&mobile=<%=  employee.getStrMobile()%>&departmentID=<%= employee.getIdDepartment()%>&managerID=<%= employee.getIdManager()%>&imageUrl=<%= request.getContextPath()%>/images/upload/<%=employee.getStrImageUrl()%>&gender=<%= employee.getEnumGender()%>&id=<%= employee.getIdEmployee()%>&imageOnly=<%= employee.getStrImageUrl()%>" > Update </a>&nbsp;&nbsp;
                            <a href="../DeleteEmployee?employeeID=<%= employee.getIdEmployee()%>&imageName=<%= employee.getStrImageUrl()%>" > Delete </a>&nbsp;&nbsp;
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>

    </body>
</html>
