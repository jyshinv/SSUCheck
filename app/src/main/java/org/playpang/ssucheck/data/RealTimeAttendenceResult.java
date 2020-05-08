package org.playpang.ssucheck.data;

import java.util.HashMap;
import java.util.Map;

public class RealTimeAttendenceResult {
    //public String AttendenceTime;
    public String junsu;
    public String jillkim;
    public String jiwonkim;

    

    public RealTimeAttendenceResult(){

    }

    public RealTimeAttendenceResult(String junsu,String jillkim, String jiwonkim){
        this.junsu = junsu;
        this.jillkim = jillkim;
        this.jiwonkim = jiwonkim;

    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("junsu",junsu);
        result.put("jillkim", jillkim);
        result.put("jiwonkim", jiwonkim);

        return result;
    }

    //여기서 선언하는 변수 이름은 파이어베이스 키 값과 동일해야 한다!!
    //result.put("큰따옴표 안 내용은 키 값과 달라도 된다",이 자리는 파이어베이스 키 값과 동일하게)
    //대표 키 --> child로 접근
    //키 : 값
    //키는 영어로 지정할 것
    //또 파이어베이스에 있는 키 개수와 변수 개수가 같지 않아도 된다.
    //즉 가져오고싶은 키값만큼만 변수를 선언해주어도 된다.
}
