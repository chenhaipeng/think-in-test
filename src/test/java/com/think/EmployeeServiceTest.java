package com.think;

import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import static org.junit.Assert.assertEquals;

/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2017/08/14 18:04
 */
public class EmployeeServiceTest {
    @Test
    public void getTotalEmployee() throws Exception {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        PowerMockito.when(employeeDao.getTotal()).thenReturn(10);
        EmployeeService service = new EmployeeService(employeeDao);
        int total = service.getTotalEmployee();
        assertEquals(10, total);
    }

    @Test
    public void testCreateEmployee() {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        Employee employee = new Employee();
        PowerMockito.doNothing().when(employeeDao).addEmployee(employee);
        EmployeeService service = new EmployeeService(employeeDao);
        service.createEmployee(employee);
        // verify the method invocation.
        Mockito.verify(employeeDao).addEmployee(employee);
    }

}