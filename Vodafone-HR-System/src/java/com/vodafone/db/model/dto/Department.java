package com.vodafone.db.model.dto;
 
public class Department  implements java.io.Serializable {


     private int idDepartement;
     private String strDepartementName;

     public Department() {
    }

	
    public Department(int idDepartement, String strDepartementName) {
        this.idDepartement = idDepartement;
        this.strDepartementName = strDepartementName;
    }
    
   
    public int getIdDepartement() {
        return this.idDepartement;
    }
    
    public void setIdDepartement(int idDepartement) {
        this.idDepartement = idDepartement;
    }
    public String getStrDepartementName() {
        return this.strDepartementName;
    }
    
    
    public void setStrDepartementName(String strDepartementName) {
        this.strDepartementName = strDepartementName;
    }
}
