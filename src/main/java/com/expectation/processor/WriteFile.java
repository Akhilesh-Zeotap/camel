package com.expectation.processor;

import com.expectation.constants.Constants;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class WriteFile {
    public static void writeToFile(StringBuilder content, String dpRegion, String dpProduct, String dpName){
        String path = String.format(Constants.DEFAULT_DIR , dpRegion.toLowerCase(), dpProduct.toLowerCase(), dpName);
        File file = new File(path);
        try {
            boolean mkdirs = file.getParentFile().mkdirs();
            if (mkdirs) {
                final boolean newFile = file.createNewFile();
            }
            Files.write(Paths.get(path), Collections.singleton(content), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
