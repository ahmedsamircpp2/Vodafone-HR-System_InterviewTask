package com.vodafone.db.model.dto;

public class Employee  implements java.io.Serializable {

     private Integer idEmployee;
     private Integer idDepartment;
     private int idManager;
     private String strEmployeeFullname;
     private String strImageUrl;
     private String strMobile;
     private String enumGender;

    public Employee() {
    }

	
    public Employee(Integer idDepartment, int idManager, String strEmployeeFullname, String strMobile, String enumGender) {
        this.idDepartment = idDepartment;
        this.idManager = idManager;
        this.strEmployeeFullname = strEmployeeFullname;
        this.strMobile = strMobile;
        this.enumGender = enumGender;
    }
   
    public Integer getIdEmployee() {
        return this.idEmployee;
    }
    
    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }
 
    public int getIdManager() {
        return this.idManager;
    }
    
    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }
    public String getStrEmployeeFullname() {
        return this.strEmployeeFullname;
    }
    
    public void setStrEmployeeFullname(String strEmployeeFullname) {
        this.strEmployeeFullname = strEmployeeFullname;
    }
    public String getStrImageUrl() {
        
        return this.strImageUrl;
    }
    
    public void setStrImageUrl(String strImageUrl) {
        this.strImageUrl = strImageUrl;
    }
    public String getStrMobile() {
        return this.strMobile;
    }
    
    public void setStrMobile(String strMobile) {
        this.strMobile = strMobile;
    }
    public String getEnumGender() {
        return this.enumGender;
    }
    
    public void setEnumGender(String enumGender) {
        this.enumGender = enumGender;
    }
}