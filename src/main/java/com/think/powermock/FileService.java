package com.think.powermock;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {

    public void write(final String text) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(
                    System.getProperty("user.dir") + "/wangwenjun.txt"));
            bw.write(text);
            bw.flush();
            System.out.println("content write successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null)
                try {
                    bw.close();
                } catch (IOException e) {
                    // be quietly
                }
        }
    }
}