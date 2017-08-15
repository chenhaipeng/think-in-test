package com.think.powermock;

/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2017/08/14 18:02
 */
public class EmployeeService2 {

    private EmployeeDao employeeDao;

    public EmployeeService2(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public EmployeeService2() {
    }
    /**
     * 获取所有员工的数量.
     *
     * @return
     */
    public int getTotalEmployee() {
        EmployeeDao employeeDao = new EmployeeDao();
//        System.out.println("invoke inner");
        return employeeDao.getTotal();
    }



    public void createEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
        System.out.println("invoke service");
    }

}
