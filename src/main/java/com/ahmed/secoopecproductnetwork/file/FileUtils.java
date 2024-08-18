package com.ahmed.secoopecproductnetwork.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class FileUtils {
    public static byte[] readFileLocation(String fileurl) {
        if(StringUtils.isBlank(fileurl)){return null;
    }
        try {
            Path filepath= new File(fileurl).toPath();
            return Files.readAllBytes(filepath);
        }catch (IOException e){log.warn("no file found in path {}",fileurl);}
        return null;
}}
