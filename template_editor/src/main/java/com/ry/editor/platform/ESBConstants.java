package com.ry.editor.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Nick
 * @version 1.0
 * @Description ESB总线数据的Constants常量类
 * @Date 2017-3-31 上午11:23:06
 */
public class ESBConstants {
    private static Logger logger = LoggerFactory.getLogger(ESBConstants.class);

    /**
     * 此处不能用final，需要修改或者全局获取
     */
    public static String ESBServerIP = "127.0.0.1";
    public static String ESBServerPort = "8088";

	public static String LOCAL_APP_IP = "127.0.0.1";

    static {
		ESBServerIP = PlatformProperties.getInstance().getProperty("esb.server.ip");
		ESBServerPort = PlatformProperties.getInstance().getProperty("esb.server.port");
		LOCAL_APP_IP = PlatformProperties.getInstance().getProperty("app.ip");
	}

    /**
     * 请求accessToken的URL
     */
    public static String ESB_GETTOKEN = "http://"+ESBServerIP+":"+ESBServerPort+"/hip/hipService/getToken";

    /**
     * 业务交换服务URL
     */
    public static String ESB_COMMONPORT = "http://"+ESBServerIP+":"+ESBServerPort+"/hip/hipService/CommonPort";

    /**
     * 订阅服务编码
     */
    public static final String  SERVICECODE_SUBSCRIPTION="A000000";

	/**
	 * alo报告发布服务
	 */
	public  static  final String SERVICECODE_REPORT_PUBLISH="B001503";

	/**
     * 病人信息相关服务编码
     */
	/**
	 * 病人查询
	 */
    public static String SERVICECODE_PATIENT_QUUERY = "A002003";
    
    /**
     * 设备情况服务编码
     */
    public static String SERVICECODE_DEVICE_QUUERY = "A002004";

	/**
	 * 效益分析
	 */
	public static String SERVICECODE_EARNINGS_QUUERY = "A002005";

	/**
	 * 待检查的病人
	 */
	public static String SERVICECODE_PATIENT_CHECK = "A002006";

	/**
	 * 与ESB交互的系统Code
	 */
	public static final String SYSTEMCODE = "WZ_SYSTEM";

	/**
	 * 与ESB交互的系统密码
	 */
	public static final String SYSTEMPASSWORD = "0";

	public static final String SERVICEVERSION_1_0 = "1.0";

}
