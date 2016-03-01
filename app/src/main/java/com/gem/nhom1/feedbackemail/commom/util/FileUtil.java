package com.gem.nhom1.feedbackemail.commom.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by vanhop on 2/3/16.
 */
public class FileUtil {


    public static void createFolder(String folderName){
        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + folderName);
        if(!folder.exists())
        folder.mkdir();
    }

    public static  File[] getAllFile (String folder_name){
        File[] faFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath()  + "/" + folder_name).listFiles();
        for(File file: faFiles){
            if(file.getName().matches("^(.*?)")){
                Log.d("phuongtd-logger", file.getName());
            }

        }
        return  faFiles;
    }

}
