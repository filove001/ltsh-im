package com.ltsh.im.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class IoUtils {
    private static Logger logger = LoggerFactory.getLogger(IoUtils.class);
    public static byte[] toByteArray(File file) {
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream();
            long count;
            int n;
            byte[] buffer = new byte[4096];
            for(count = 0L; -1 != (n = fileInputStream.read(buffer)); count += (long)n) {
                byteArrayOutputStream.write(buffer, 0, n);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if(fileInputStream != null) {
                    fileInputStream.close();
                }
                if(byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }

        }
        return null;
    }
}
