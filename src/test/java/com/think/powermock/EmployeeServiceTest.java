package com.think.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2017/08/14 18:04
 */
@PrepareForTest({EmployeeUtils.class,EmployeeService.class,EmployeeDao.class})
@RunWith(PowerMockRunner.class)
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

    /**
     * 测试static
     */
    @Test
    public void testGetEmployeeCountWithMock() {
        PowerMockito.mockStatic(EmployeeUtils.class);
        PowerMockito.when(EmployeeUtils.getEmployeeCount()).thenReturn(10);
        final EmployeeService employeeService = new EmployeeService();
        int count = employeeService.getEmployeeCount();
        assertEquals(10, count);
    }

    @Test
    public void testCreateEmployeeWithMock() {
        PowerMockito.mockStatic(EmployeeUtils.class);
        Employee employee = new Employee();
        PowerMockito.doNothing().when(EmployeeUtils.class);
        final EmployeeService employeeService = new EmployeeService();
        employeeService.createEmployeeStatic(employee);
        PowerMockito.verifyStatic();
    }


    @Test
    public void testSaveOrUpdateCountLessZero() {
        try {
            EmployeeDao employeeDao =
                    PowerMockito.mock(EmployeeDao.class);
            PowerMockito.whenNew(EmployeeDao.class).withNoArguments()
                    .thenReturn(employeeDao);
            Employee employee = new Employee();
            PowerMockito.when(employeeDao.getCount(employee)).thenReturn(0L);
            EmployeeService employeeService = new EmployeeService();
            employeeService.saveOrUpdate(employee);
            Mockito.verify(employeeDao).saveEmployee(employee);
            Mockito.verify(employeeDao, Mockito.never()).updateEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test
    public void testSaveOrUpdateCountMoreThanZero() {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        try {
            PowerMockito.whenNew(EmployeeDao.class).withNoArguments()
                    .thenReturn(employeeDao);
            Employee employee = new Employee();
            PowerMockito.when(employeeDao.getCount(employee)).thenReturn(1L);
            EmployeeService employeeService = new EmployeeService();
            employeeService.saveOrUpdate(employee);
            Mockito.verify(employeeDao).updateEmployee(employee);
            Mockito.verify(employeeDao,
                    Mockito.never()).saveEmployee(employee);
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    public void testExist() {
        try {
            EmployeeService employeeService = PowerMockito.spy(new
                    EmployeeService());
            PowerMockito.doNothing().when(employeeService, "checkExist",
                    "wangwenjun");
            boolean result = employeeService.exist("wangwenjun");
            assertTrue(result);
            PowerMockito.verifyPrivate(employeeService).invoke("checkExist","wangwenjun");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}

