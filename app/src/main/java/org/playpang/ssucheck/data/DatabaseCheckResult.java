package org.playpang.ssucheck.data;

import java.util.HashMap;
import java.util.Map;

public class DatabaseCheckResult {

    public String checkTime;
    public String checkResult;

    public DatabaseCheckResult(){ //생성자

    }

    public DatabaseCheckResult(String checkTime, String checkResult){ //생성자

        this.checkTime = checkTime;
        this.checkResult = checkResult;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("checkTime", checkTime);
        result.put("checkResult",checkResult);
        return result;
    }
}
