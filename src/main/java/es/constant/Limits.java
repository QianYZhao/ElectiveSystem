package es.constant;

import java.sql.Date;
import java.sql.Timestamp;

public class Limits {
    public final static String takesState1 ="taken";
    public final static String takesState2 ="dropped";
    public static String takesState3 ="applying";
    public  static String  takesState4 ="reLearning";
    public  static String  takesState5 ="completed";
    public final static int max_credits = 32;
    public  static int takeStartTime =1575943200;//2019/12/10 10:0:0
    public static  int takeEndTime = 1577584800;//2019/12/29 10:00：00
    public static int dropStartTime=157594320;
    public static int dropEndTime=1577584800;
}
