package com.redis;


public class RedisKeyUtils {
    private static final String KEY_SUFFIX="elevator";

    /**
     * 监控上报信息KEY
     * @param elevatorCode
     * @return
     */
    public static String monitorKey(String elevatorCode){
        return String.format("%s:%s:%s", KEY_SUFFIX, "monitor",elevatorCode);
    }

    /**
     * 故障上报信息KEY
     * @param elevatorCode
     * @return
     */
    public static String faultKey(String elevatorCode){
        return String.format("%s:%s:%s", KEY_SUFFIX, "fault",elevatorCode);
    }

    /**
     * 事件上报信息KEY
     * @param elevatorCode
     * @return
     */
    public static String eventKey(String elevatorCode){
        return String.format("%s:%s:%s", KEY_SUFFIX, "event",elevatorCode);
    }

    /**
     * 告警信息上报KEY
     * @param elevatorCode
     * @return
     */
    public static String alarmKey(String elevatorCode){
        return String.format("%s:%s:%s", KEY_SUFFIX, "alarm",elevatorCode);
    }

    /**
     * 客户端配置KEY
     * @param elevatorCode
     * @return
     */
    public static String clientConfigInfoKey(String elevatorCode){
        return String.format("%s:%s:%s:%s", KEY_SUFFIX, "config",elevatorCode,"cli_conf");
    }

    /**
     * 客户端重启记录KEY
     * @param elevatorCode
     * @return
     */
    public static String clientRebotKey(String elevatorCode){
        return String.format("%s:%s:%s:%s", KEY_SUFFIX, "config",elevatorCode,"cli_rebot");
    }

    /**
     * 操作客户端指令KEY
     * @param elevatorCode
     * @return
     */
    public static String clientCommandKey(String elevatorCode){
        return String.format("%s:%s:%s:%s", KEY_SUFFIX, "config",elevatorCode,"command");
    }

    /**
     * 设备服务器最新版本号KEY
     * @param elevatorCode
     * @return
     */
    public static String configVersionKey(String elevatorCode){
        return String.format("%s:%s:%s:%s", KEY_SUFFIX, "config",elevatorCode,"version");
    }

    /**
     * @param elevatorCode
     * @return
     */
    public static String clientStatusKey(String elevatorCode,String suffixKey){
        return String.format("%s:%s:%s:%s", KEY_SUFFIX, "online",elevatorCode, suffixKey);
    }
    
    
    
    /**
     * 短信验证码
     * @param telephone
     * @return
     */
    public static String telephoneKey(String telephone ){
        return String.format("%s:%s:%s", KEY_SUFFIX, "verificationCode",telephone );
    }

    /**
     * 故障开关
     * @param elevatorCode
     * @return
     */
    public static String faultSwitchKey(String elevatorCode ){
        return String.format("%s:%s:%s", KEY_SUFFIX, "faultSwitch",elevatorCode );
    }

    /**
     * 所有故障开关
     * @return
     */
    public static String allFaultSwitchKey(){
        return String.format("%s:%s:%s", KEY_SUFFIX, "faultSwitch", "ALL");
    }
}
