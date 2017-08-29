/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.controller.beans;

import com.vodafone.db.model.dbutils.HR_DB_Helper;
import com.vodafone.db.model.dto.Department;
import java.util.Set;

/**
 * contain Function Get All Departments from database
 * @author ahmed_amer
 */
public class DepartmentBean {

    public Set<Department> getAllDepartment() {
        HR_DB_Helper dB_Helper = new HR_DB_Helper();
        return dB_Helper.All_Departments();
    }
}
