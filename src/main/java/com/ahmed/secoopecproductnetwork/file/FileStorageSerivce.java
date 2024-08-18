package com.ahmed.secoopecproductnetwork.file;

import com.ahmed.secoopecproductnetwork.secoopecproduct.SecoopecProduct;
import jakarta.mail.Multipart;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageSerivce {

    @Value("${application.file.uploads.photos-output-path}")
    private String fileUploadPath;
    public String saveFile(@NonNull MultipartFile sourcefile,

                           @NonNull Integer userid) {
        final String fileUploadSubPath = "users"+ File.separator+ userid;
        final String finalUploadPath=fileUploadPath + File.separator+fileUploadSubPath;
        File targetFolder=new File(finalUploadPath);
        if(!targetFolder.exists()){boolean foldercreated=targetFolder.mkdirs();
            if(!foldercreated){log.warn("failed to create floder");
                return null;

            }}
        final String fileextension=getFileExtention(sourcefile.getOriginalFilename());
        // ./upload/users/1/2335484984.jpg
        String targetfilepath=finalUploadPath + File.separator+System.currentTimeMillis()+"."+fileextension;
        Path targetpath= Paths.get(targetfilepath);

        try {
            Files.write(targetpath,sourcefile.getBytes());
            log.info("File saved to"+targetfilepath);
        } catch (IOException e) {
            log.error("File was not saved");

        }
        return null;

    }





    private String getFileExtention(String filename) {
        if(filename==null || filename.isEmpty()){
            return "";
        }
        int LastDotIndex=filename.lastIndexOf(".");
        if(LastDotIndex==-1){return "";}
        return filename.substring(LastDotIndex+1).toLowerCase();
    }
    }
