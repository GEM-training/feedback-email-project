package com.gem.nhom1.feedbackemail.commom.logger;

/**
 * Created by vanhop on 2/3/16.
 */
public class LogData {

    private static LinkQueue<String> logData;

    static{
        logData = new LinkQueue<String>();
    }

    public static void addLog(String log){
        logData.enqueueNotify(log);
    }

    public static String getFirstLog(){
        return logData.dequeueWait(30);
    }

}
