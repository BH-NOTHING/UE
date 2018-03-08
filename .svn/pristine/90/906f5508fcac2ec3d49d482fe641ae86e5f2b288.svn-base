package com.ry.editor.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Platform的资源文件加载器
 * Created by Nick on 2017/5/22.
 */
public class PlatformProperties {

    private static Logger logger = LoggerFactory.getLogger(PlatformProperties.class);

    private static Properties prop = null;

    private PlatformProperties() {

    }

    public static Properties getInstance() {
        if(prop == null) {
            //读取配置文件
            prop = new Properties();
            InputStream fileInputStream = null;
            try {
                fileInputStream = ESBConstants.class.getResourceAsStream("/conf/platform.properties");
                prop.load(fileInputStream);
            } catch (IOException e) {
                logger.error("读取文件出错", e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if(fileInputStream != null) fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;

    }

}
