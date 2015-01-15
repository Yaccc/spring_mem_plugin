package org.memcached.xiezhaodong.test;

import java.io.Serializable;

/**
 * 
 * <pre><b>����������</b>Ա���࣬����Ҫ���л������򻺴����ʱ�ᱨ���
 *
 * @author ��****(Kevin.xie)<br>
 *
 * <b>�޸���ʷ��</b>(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 *
 * </pre>
 */
public class Employee implements Serializable {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6487397580836471048L;
    
    /**
     * Ա������
     */
    private String EmpName;
    
    /**
     * ������
     */
    private String deptName;
    
    /**
     * ��˾��
     */
    private String companyName;
    
    /**
     * 
     * <b>���캯��</b>
     * 
     */
    public Employee() {

    }
    
    /**
     * Access method for the empName property
     * 
     * @return the empName
     */
    public String getEmpName() {

        return EmpName;
    }
    
    /**
     * Sets the value of empName the property
     * 
     * @param empName the empName to set
     */
    public void setEmpName(String empName) {

        EmpName = empName;
    }
    
    /**
     * Access method for the deptName property
     * 
     * @return the deptName
     */
    public String getDeptName() {

        return deptName;
    }
    
    /**
     * Sets the value of deptName the property
     * 
     * @param deptName the deptName to set
     */
    public void setDeptName(String deptName) {

        this.deptName = deptName;
    }
    
    /**
     * Access method for the companyName property
     * 
     * @return the companyName
     */
    public String getCompanyName() {

        return companyName;
    }
    
    /**
     * Sets the value of companyName the property
     * 
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {

        this.companyName = companyName;
    }
}