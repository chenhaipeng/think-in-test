package com.think;

/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2017/08/14 18:02
 */
public class EmployeeService {

    private EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     * 获取所有员工的数量.
     *
     * @return
     */
    public int getTotalEmployee() {
        return employeeDao.getTotal();
    }



    public void createEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
        System.out.println("invoke service");
    }

}
