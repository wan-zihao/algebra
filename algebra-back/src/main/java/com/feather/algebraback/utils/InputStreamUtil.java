package com.feather.algebraback.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author 惊鸿之羽
 */
public class InputStreamUtil {
    /**
     * 将字节输入流转为文本
     */
    public static String getInputStreamString(InputStream inputStream) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String s = null;
        StringBuilder builder = new StringBuilder();
        while ((s = bufferedReader.readLine()) != null){
            builder.append(s);
        }
        bufferedReader.close();
        return builder.toString();
    }
}
