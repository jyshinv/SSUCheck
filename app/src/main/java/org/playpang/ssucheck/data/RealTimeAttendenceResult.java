package org.playpang.ssucheck.data;

import java.util.HashMap;
import java.util.Map;

public class RealTimeAttendenceResult {
    //변수
    public String jiwonkim;
    public String jiilkim;
    public String jiyoonshin;
    public String spongebob;
    public String ddunge;
    public String new1;
    public String new2;
    public String new3;

    public RealTimeAttendenceResult(){ //생성자

    }

    public RealTimeAttendenceResult(String jiilkim, String jiwonkim,String jiyoonshin, String spongebob, String ddunge, String new1, String new2, String new3){ //생성자
        this.jiilkim = jiilkim;
        this.jiwonkim = jiwonkim;
        this.jiyoonshin = jiyoonshin;
        this.spongebob = spongebob;
        this.ddunge = ddunge;
        this.new1 = new1;
        this.new2 = new2;
        this.new3 = new3;

    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("jiilkim", jiilkim);
        result.put("jiwonkim", jiwonkim);
        result.put("jiyoonshin",jiyoonshin);
        result.put("spongebob",spongebob);
        result.put("ddunge",ddunge);
        result.put("new1",new1);
        result.put("new2",new2);
        result.put("new3",new3);

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
