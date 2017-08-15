package com.think.powermock;

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
    public EmployeeService() {
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

    public int getEmployeeCount() {
        return EmployeeUtils.getEmployeeCount();
    }

    public void createEmployeeStatic(Employee employee) {
        EmployeeUtils.persistenceEmployee(employee);
    }


    /**
     * 保存或者更新
     * @param employee
     */
    public void saveOrUpdate(Employee employee) {
        final EmployeeDao employeeDao = new EmployeeDao();
        long count = employeeDao.getCount(employee);
        if (count > 0)
            employeeDao.updateEmployee(employee);
        else
            employeeDao.saveEmployee(employee);
    }

    public String findEmailByUserName(String userName) {

        throw new UnsupportedOperationException();
    }


    public boolean exist(String userName) {
        checkExist(userName);
        System.out.println("exist==");
        return true;
    }
    private void checkExist(String userName) {
        System.out.println("invoke the method"+userName);
        throw new UnsupportedOperationException();
    }
}
