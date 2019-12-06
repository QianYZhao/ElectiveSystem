package es.constant;

import java.sql.Time;

public class Tools {

    public static boolean CompareTimeOverlap(Time oldStart,Time OldEnd,Time newStart,Time newEnd){
     // 如果有重叠的部分就返回true，没有基于返回false
        if(oldStart.before(newEnd)&&newStart.before(oldStart))
            return false;
        return true;
    }


}
