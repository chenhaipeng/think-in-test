package com.think.powermock;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import java.io.File;

import static junit.framework.TestCase.assertTrue;

/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2017/08/15 12:03
 */
public class FileServiceTest {

    @Test
    public void testWrite() {
        FileService fileService = PowerMockito.mock(FileService.class);
        fileService.write("liudehua");
    }


    @Test
    public void testWriteSpy()
    {
//        FileService fileService = PowerMockito.spy(new FileService());
        FileService fileService = PowerMockito.spy(new FileService());
        fileService.write("liudehua");
        File file = new
                File(System.getProperty("user.dir")+"/wangwenjun.txt");
        assertTrue(file.exists());
    }

}