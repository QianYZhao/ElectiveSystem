package es;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ErrorCode extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public static final int IO_EXCEPTION = 1;
    public static final int CLASSROOM_CONFLICT = 2;

    private static final Map<Integer,String>ErrorCodeMap = new HashMap<>();

    static {
        ErrorCodeMap.put(IO_EXCEPTION,"IO exception");
        ErrorCodeMap.put(CLASSROOM_CONFLICT,"classroom is conflict");
    }

    public static String getErrorText(int errorCode){
        return ErrorCodeMap.getOrDefault(errorCode,"invalid");
    }

    private int errorCode;

    public ErrorCode(int errorCode){
        super(String.format("error code '%d' \"%s\"",errorCode,getErrorText(errorCode)));
        this.errorCode = errorCode;
    }

    public int getErrorCode(){
        return errorCode;
    }
}

