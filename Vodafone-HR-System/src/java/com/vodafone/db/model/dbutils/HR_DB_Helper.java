/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.db.model.dbutils;

import com.vodafone.controller.constants.constants;
import com.vodafone.db.model.dto.Department;
import com.vodafone.db.model.dto.Employee;
import com.vodafone.db.model.connection.Connect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author ahmed_amer
 */
public class HR_DB_Helper {
    /**
     * get New Key In EMployee Table  
     * @return  Current Key in EmployeeTable +1
     */
    public int getPrimaryKey() {
        String queryInsertEmployee = "select COALESCE(max(ID_EMPLOYEE),0) + 1 as MAX_ID from employee";
        Statement stmt = null;
        Connection co = Connect.getConnection();
        try {
            stmt = co.createStatement();
            ResultSet resultSet = stmt.executeQuery(queryInsertEmployee);
            if (resultSet.next()) {
                return resultSet.getInt("MAX_ID");
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return 1;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }
                stmt = null;
            }
        }
        return 0;
    }

    /**
     * Insert Employee into Database
     * @param employee
     * @return Status (Done,ErrorHapper)
     */
    public String insert(Employee employee) {
        int nunOfRow_Affected = 0;
        String queryInsertEmployee = "insert into employee"
                + "(ID_EMPLOYEE,ID_MANAGER,ID_DEPARTEMENT,STR_EMPLOYEE_FULLNAME,STR_IMAGE_URL,STR_MOBILE,ENUM_GENDER) "
                + "values(" + getPrimaryKey() + "," + employee.getIdManager() + "," + employee.getIdDepartment() + ",'" + employee.getStrEmployeeFullname()
                + "','" + employee.getStrImageUrl() + "','" + employee.getStrMobile() + "','" + employee.getEnumGender() + "')";
        Statement stmt = null;
        Connection co = Connect.getConnection();
        try {
            stmt = co.createStatement();
            stmt.execute(queryInsertEmployee);
            if ((nunOfRow_Affected = stmt.getUpdateCount()) > 0) {
                System.out.println(nunOfRow_Affected + " : row inserted  [OK]");
                return constants.SUCCESS_CODE + " : " + nunOfRow_Affected + " : row inserted  [OK]";
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return constants.ERROR_CODE + ":" + ex.getMessage();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }
                stmt = null;
            }
        }
        return constants.SUCCESS_CODE + " : " + nunOfRow_Affected + " : row inserted  [OK]";
    }
   /**
     * Update Employee
     * @param employee
     * @return Status (Done,ErrorHapper)
     */
    public String update(Employee employee) {
        int nunOfRow_Affected = 0;
        String queryUpdateEmployee = "update employee"
                + " set ID_MANAGER = " + employee.getIdManager() + " , ID_DEPARTEMENT=" + employee.getIdDepartment()
                + " , STR_EMPLOYEE_FULLNAME = '" + employee.getStrEmployeeFullname() + "' , STR_IMAGE_URL = '"
                + employee.getStrImageUrl()
                + "' , STR_MOBILE = '" + employee.getStrMobile() + "' , ENUM_GENDER = '" + employee.getEnumGender()
                + "' where ID_EMPLOYEE = " + employee.getIdEmployee();
        Statement stmt = null;
        Connection co = Connect.getConnection();
        try {
            stmt = co.createStatement();
            stmt.executeUpdate(queryUpdateEmployee);
            if ((nunOfRow_Affected = stmt.getUpdateCount()) > 0) {
                System.out.println(nunOfRow_Affected + " : row Updated  [OK]");
                return  constants.SUCCESS_CODE +":"+nunOfRow_Affected + " : row Updated  [OK]";
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return constants.ERROR_CODE;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }
                stmt = null;
            }
        }
        return constants.SUCCESS_CODE;
    }
    /**
     * delete Employee 
     * @param id
     * @return Status (true,false)
     */
    public boolean delete(int id) {
        int nunOfRow_Affected = 0;
        String queryUpdateEmployee = "delete from employee where  ID_EMPLOYEE = " + id;
        Statement stmt = null;
        Connection co = Connect.getConnection();
        try {
            stmt = co.createStatement();
            stmt.execute(queryUpdateEmployee);
            if ((nunOfRow_Affected = stmt.getUpdateCount()) > 0) {
                System.out.println(nunOfRow_Affected + " : row Deleted  [OK]");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }
                stmt = null;
            }
        }
        return true;
    }
    /**
     * get All  Employees by name
     * @param strEmployeeName
     * @return  Set Of Founded Employees
     */
    public Set<Employee> select_By_Name(String strEmployeeName) {
        int numOfRowSelected = 0;
        String querySelectEmployee = "select * from employee e where  e.STR_EMPLOYEE_FULLNAME like \"%" + strEmployeeName + "%\"";
        Statement stmt = null;
        Connection co = Connect.getConnection();
        Set<Employee> foundedEmployees = new HashSet<Employee>(0);
        try {
            stmt = co.createStatement();
            System.out.println("------------------------------------Begin Executing------------------------------");
            System.out.println("Query : " + querySelectEmployee);
            ResultSet selectedEmployees = stmt.executeQuery(querySelectEmployee);
            // System.out.println("Fetch Size  = "+stmt.getResultSet().getFetchSize());
            while (selectedEmployees.next()) {
                Employee obj = new Employee();
                obj.setEnumGender(selectedEmployees.getString("ENUM_GENDER"));
                obj.setIdDepartment(selectedEmployees.getInt("ID_DEPARTEMENT"));
                obj.setIdEmployee(selectedEmployees.getInt("ID_EMPLOYEE"));
                obj.setIdManager(selectedEmployees.getInt("ID_MANAGER"));
                obj.setStrEmployeeFullname(selectedEmployees.getString("STR_EMPLOYEE_FULLNAME"));
                obj.setStrImageUrl(selectedEmployees.getString("STR_IMAGE_URL"));
                obj.setStrMobile(selectedEmployees.getString("STR_MOBILE"));
                foundedEmployees.add(obj);
                numOfRowSelected += 1;
            }
            System.out.println(numOfRowSelected + " : row Selected  [OK]");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }
                stmt = null;
            }
        }
        return foundedEmployees;
    }
/**
     * get Employees by ID
     * @param employeeID
     * @return  Set Of Founded Employees
     */
    public Set<Employee> select_By_ID(Integer employeeID) {
        int numOfRowSelected = 0;
        String querySelectEmployee = "select * from employee e where  e.ID_EMPLOYEE =" + employeeID;
        Statement stmt = null;
        Connection co = Connect.getConnection();
        Set<Employee> foundedEmployees = new HashSet<Employee>(0);
        try {
            stmt = co.createStatement();
            System.out.println("------------------------------------Begin Executing------------------------------");
            System.out.println("Query : " + querySelectEmployee);
            ResultSet selectedEmployees = stmt.executeQuery(querySelectEmployee);
            // System.out.println("Fetch Size  = "+stmt.getResultSet().getFetchSize());
            while (selectedEmployees.next()) {
                Employee obj = new Employee();
                obj.setEnumGender(selectedEmployees.getString("ENUM_GENDER"));
                obj.setIdDepartment(selectedEmployees.getInt("ID_DEPARTEMENT"));
                obj.setIdEmployee(selectedEmployees.getInt("ID_EMPLOYEE"));
                obj.setIdManager(selectedEmployees.getInt("ID_MANAGER"));
                obj.setStrEmployeeFullname(selectedEmployees.getString("STR_EMPLOYEE_FULLNAME"));
                obj.setStrImageUrl(selectedEmployees.getString("STR_IMAGE_URL"));
                obj.setStrMobile(selectedEmployees.getString("STR_MOBILE"));
                foundedEmployees.add(obj);
                numOfRowSelected += 1;
            }
            System.out.println(numOfRowSelected + " : row Selected  [OK]");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }
                stmt = null;
            }
        }
        return foundedEmployees;
    }
    
    /**
     * get Employee by it's Departments 
     * @param departmentID
     * @return  Set Of Founded Employees
     */
    public Set<Employee> select_By_DepartmentID(Integer departmentID) {
        int numOfRowSelected = 0;
        String querySelectEmployee = "select * from employee e where  e.ID_DEPARTEMENT = " + departmentID;
        Statement stmt = null;
        Connection co = Connect.getConnection();
        Set<Employee> foundedEmployees = new HashSet<Employee>(0);
        try {
            stmt = co.createStatement();
            System.out.println("------------------------------------Begin Executing------------------------------");
            System.out.println("Query : " + querySelectEmployee);
            ResultSet selectedEmployees = stmt.executeQuery(querySelectEmployee);
            // System.out.println("Fetch Size  = "+stmt.getResultSet().getFetchSize());
            while (selectedEmployees.next()) {
                Employee obj = new Employee();
                obj.setEnumGender(selectedEmployees.getString("ENUM_GENDER"));
                obj.setIdDepartment(selectedEmployees.getInt("ID_DEPARTEMENT"));
                obj.setIdEmployee(selectedEmployees.getInt("ID_EMPLOYEE"));
                obj.setIdManager(selectedEmployees.getInt("ID_MANAGER"));
                obj.setStrEmployeeFullname(selectedEmployees.getString("STR_EMPLOYEE_FULLNAME"));
                obj.setStrImageUrl(selectedEmployees.getString("STR_IMAGE_URL"));
                obj.setStrMobile(selectedEmployees.getString("STR_MOBILE"));
                foundedEmployees.add(obj);
                numOfRowSelected += 1;
            }
            System.out.println(numOfRowSelected + " : row Selected  [OK]");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }
                stmt = null;
            }
        }
        return foundedEmployees;
    }
    /**
     * get ALl Employee
     * @return  all DB Employee
     */
    public Set<Employee> All_Employees() {
        int numOfRowSelected = 0;
        String querySelectEmployee = "select * from employee ";
        Statement stmt = null;
        Connection co = Connect.getConnection();
        Set<Employee> foundedEmployees = new HashSet<Employee>(0);
        try {
            stmt = co.createStatement();
            System.out.println("------------------------------------Begin Executing------------------------------");
            System.out.println("Query : " + querySelectEmployee);
            ResultSet selectedEmployees = stmt.executeQuery(querySelectEmployee);
            // System.out.println("Fetch Size  = "+stmt.getResultSet().getFetchSize());
            while (selectedEmployees.next()) {
                Employee obj = new Employee();
                obj.setEnumGender(selectedEmployees.getString("ENUM_GENDER"));
                obj.setIdDepartment(selectedEmployees.getInt("ID_DEPARTEMENT"));
                obj.setIdEmployee(selectedEmployees.getInt("ID_EMPLOYEE"));
                obj.setIdManager(selectedEmployees.getInt("ID_MANAGER"));
                obj.setStrEmployeeFullname(selectedEmployees.getString("STR_EMPLOYEE_FULLNAME"));
                obj.setStrImageUrl(selectedEmployees.getString("STR_IMAGE_URL"));
                obj.setStrMobile(selectedEmployees.getString("STR_MOBILE"));
                foundedEmployees.add(obj);
                numOfRowSelected += 1;
            }
            System.out.println(numOfRowSelected + " : row Selected  [OK]");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }
                stmt = null;
            }
        }
        return foundedEmployees;
    }
    /**
     * get ALl departments
     * @return 
     */
    public Set<Department> All_Departments() {
        int numOfRowSelected = 0;
        String querySelectDepartments = "select * from Department ";
        Statement stmt = null;
        Connection co = Connect.getConnection();
        Set<Department> foundeDepartments = new HashSet<Department>(0);
        try {
            stmt = co.createStatement();
            System.out.println("------------------------------------Begin Executing------------------------------");
            System.out.println("Query : " + querySelectDepartments);
            ResultSet selectedEmployees = stmt.executeQuery(querySelectDepartments);
            // System.out.println("Fetch Size  = "+stmt.getResultSet().getFetchSize());
            while (selectedEmployees.next()) {
                Department department = new Department();
                department.setIdDepartement(selectedEmployees.getInt("ID_DEPARTEMENT"));
                department.setStrDepartementName(selectedEmployees.getString("STR_DEPARTEMENT_NAME"));
                foundeDepartments.add(department);
                numOfRowSelected += 1;
            }
            System.out.println(numOfRowSelected + " : row Selected  [OK]");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }
                stmt = null;
            }
        }
        return foundeDepartments;
    }
    /**
     * get ALl genders
     * @param gender
     * @return 
     */
    public Set<Employee> select_By_Gender(String gender) {
        int numOfRowSelected = 0;
        String querySelectEmployee = "select * from employee e where  e.ENUM_GENDER = '" + gender + "'";
        Statement stmt = null;
        Connection co = Connect.getConnection();
        Set<Employee> foundedEmployees = new HashSet<Employee>(0);
        try {
            stmt = co.createStatement();
            System.out.println("------------------------------------Begin Executing------------------------------");
            System.out.println("Query : " + querySelectEmployee);
            ResultSet selectedEmployees = stmt.executeQuery(querySelectEmployee);
            // System.out.println("Fetch Size  = "+stmt.getResultSet().getFetchSize());
            while (selectedEmployees.next()) {
                Employee obj = new Employee();
                obj.setEnumGender(selectedEmployees.getString("ENUM_GENDER"));
                obj.setIdDepartment(selectedEmployees.getInt("ID_DEPARTEMENT"));
                obj.setIdEmployee(selectedEmployees.getInt("ID_EMPLOYEE"));
                obj.setIdManager(selectedEmployees.getInt("ID_MANAGER"));
                obj.setStrEmployeeFullname(selectedEmployees.getString("STR_EMPLOYEE_FULLNAME"));
                obj.setStrImageUrl(selectedEmployees.getString("STR_IMAGE_URL"));
                obj.setStrMobile(selectedEmployees.getString("STR_MOBILE"));
                foundedEmployees.add(obj);
                numOfRowSelected += 1;
            }
            System.out.println(numOfRowSelected + " : row Selected  [OK]");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }
                stmt = null;
            }
        }
        return foundedEmployees;
    }
    /**
     * get Employees by Mobile
     * @param mobile
     * @return  Set Of Founded Employees
     */
    public Set<Employee> select_By_Mobile(String mobile) {
        int numOfRowSelected = 0;
        String querySelectEmployee = "select * from employee e where  e.STR_MOBILE like \"%" + mobile + "%\"";
        Statement stmt = null;
        Connection co = Connect.getConnection();
        Set<Employee> foundedEmployees = new HashSet<Employee>(0);
        try {
            stmt = co.createStatement();
            System.out.println("------------------------------------Begin Executing------------------------------");
            System.out.println("Query : " + querySelectEmployee);
            ResultSet selectedEmployees = stmt.executeQuery(querySelectEmployee);
            // System.out.println("Fetch Size  = "+stmt.getResultSet().getFetchSize());
            while (selectedEmployees.next()) {
                Employee obj = new Employee();
                obj.setEnumGender(selectedEmployees.getString("ENUM_GENDER"));
                obj.setIdDepartment(selectedEmployees.getInt("ID_DEPARTEMENT"));
                obj.setIdEmployee(selectedEmployees.getInt("ID_EMPLOYEE"));
                obj.setIdManager(selectedEmployees.getInt("ID_MANAGER"));
                obj.setStrEmployeeFullname(selectedEmployees.getString("STR_EMPLOYEE_FULLNAME"));
                obj.setStrImageUrl(selectedEmployees.getString("STR_IMAGE_URL"));
                obj.setStrMobile(selectedEmployees.getString("STR_MOBILE"));
                foundedEmployees.add(obj);
                numOfRowSelected += 1;
            }
            System.out.println(numOfRowSelected + " : row Selected  [OK]");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }
                stmt = null;
            }
        }
        return foundedEmployees;
    }

    public static void main(String[] args) {
        Employee employee = new Employee(2, 4, "rambo", "222", "MALE");
        employee.setIdEmployee(4);
        HR_DB_Helper hrdbh = new HR_DB_Helper();
        //hrdbh.update(employee);
        //hrdbh.select_By_Name("ah");
        //hrdbh.select_By_ID(1);
//        hrdbh.select_By_Mobile("010");
//        hrdbh.delete(4);
        //   hrdbh.insert(employee);
//        System.out.println(hrdbh.getPrimaryKey());
//         System.out.println(hrdbh.All_Employees().size());
        System.out.println(hrdbh.All_Departments().size());

    }
}
