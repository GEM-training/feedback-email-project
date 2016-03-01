package com.gem.nhom1.feedbackemail.commom.logger;

import com.gem.nhom1.feedbackemail.network.dto.LogDTO;

/**
 * Created by vanhop on 2/3/16.
 */
public class LogData {

    private static LinkQueue<LogDTO> logData;

    static{
        logData = new LinkQueue<LogDTO>();
    }

    public static void addLog(LogDTO log){
        logData.enqueueNotify(log);
    }

    public static LogDTO getFirstLog(){
        return logData.dequeueWait(100);
    }

}
