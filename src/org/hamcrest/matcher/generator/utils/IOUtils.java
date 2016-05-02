package org.hamcrest.matcher.generator.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOUtils {
    public static String readContents(InputStream inputStream) {
        StringBuffer builder = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        char[] buffer = new char[2048];
        try {
            int read = bufferedReader.read(buffer);
            while (read > 0) {
                builder.append(buffer, 0, read);
                read = bufferedReader.read(buffer);
            }

            bufferedReader.close();
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
