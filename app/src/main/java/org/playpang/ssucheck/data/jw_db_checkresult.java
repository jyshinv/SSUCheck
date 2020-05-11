package org.playpang.ssucheck.data;

import java.util.HashMap;
import java.util.Map;

public class jw_db_checkresult {
    public String checkTime;
    public String checkResult;
    //public String key;

    public String getcheckTime() {
        return checkTime;
    }

    public void setcheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getcheckResult() {
        return checkResult;
    }

    public void setcheckResult(String checkResult) {
        this.checkResult = checkResult;
    }


    public jw_db_checkresult(){

    }

    public jw_db_checkresult(String checkTime, String checkResult){
        this.checkTime = checkTime;
        this.checkResult= checkResult;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("checkTime", checkTime);
        result.put("checkResult", checkResult);
        return result;
    }
}
