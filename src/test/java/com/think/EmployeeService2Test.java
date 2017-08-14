package com.think;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2017/08/14 18:20
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({EmployeeService2.class})
public class EmployeeService2Test {

    @Test
    public void createEmployee() throws Exception {

    }

    /**
     * 用传统的方式肯定测试失败。
     */
    @Test
    public void testGetTotalEmployee() {
    }

    //should fail
    @Test
    public void getTotalEmployee() throws Exception {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        PowerMockito.when(employeeDao.getTotal()).thenReturn(10);
        EmployeeService2 service = new EmployeeService2(employeeDao);
        int total = service.getTotalEmployee();
        assertEquals(10, total);
    }


    /**
     * 采用 PowerMock 进行测试
     */
    @Test
    public void testGetTotalEmployeeWithMock() {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        try {
            PowerMockito.whenNew(EmployeeDao.class).withNoArguments()
                    .thenReturn(employeeDao);
            PowerMockito.when(employeeDao.getTotal()).thenReturn(10);
            EmployeeService2 service = new EmployeeService2();
            int total = service.getTotalEmployee();
            assertEquals(10, total);
        } catch (Exception e) {
            e.printStackTrace();
            fail("test fail");
        }
    }
}
