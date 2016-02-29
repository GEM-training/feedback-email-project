package com.gem.nhom1.feedbackemail.commom.logger;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.gem.nhom1.feedbackemail.commom.util.DateUtils;
import com.gem.nhom1.feedbackemail.commom.util.FileUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by phuongtd on 29/02/2016.
 */
public class LogThread extends Thread {

    Context context;

    public static  final String LOG_FOLDER = "feedback_log_file";

    private String file_type = ".txt";

    String file_name ;

    public LogThread(Context context){
        this.context = context;
        FileUtil.createFolder(LOG_FOLDER);

        Calendar cal = Calendar.getInstance();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        file_name  = dateFormat.format(cal.getTime()) + file_type;

        // Lay ra danh scah file trong folder
        File[] files = FileUtil.getAllFile(LOG_FOLDER);

        // Lay ra danh sach 5 ngay gan day nhat ( Cac file nay se duoc giu lai )
        List<String> dayAgo = DateUtils.getSomeDateAgo(5);

        for(File file : files){
            boolean delete = true;
            for(int i = 0 ; i < dayAgo.size() ; i ++){
                if(file.getName().equals(dayAgo.get(i)+file_type)){
                    delete = false;
                    break;
                }

            }

            if(delete){
                Log.d("phuongtd-logger" , "Delete file: " + file.getName());
                file.delete();
            }
        }


    }
    @Override
    public void run() {

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + LOG_FOLDER + "/" + file_name);

            while (true) {

                String log = LogData.getFirstLog();
                Log.d("phuongtd-logger", "phuongtd: " + log);



                // if file doesnt exists, then create it


                FileWriter fw = null;
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fw = new FileWriter(file.getAbsoluteFile() , true);

                    BufferedWriter bw = new BufferedWriter(fw);

                    if(log != null){
                        bw.write(log);
                        bw.write("\n");
                    }
                    bw.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


    }
}
