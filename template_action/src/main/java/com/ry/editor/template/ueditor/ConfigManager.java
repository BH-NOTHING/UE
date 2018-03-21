package com.ry.editor.template.ueditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ry.editor.template.ueditor.define.ActionMap;

/**
 * 配置管理器
 *
 * @author hancong03@baidu.com
 */
public final class ConfigManager {
    private static Logger logger = Logger.getLogger(ConfigManager.class);

    private final String rootPath;
    private final String originalPath;
    private final String contextPath;
    private static final String configFileName = "config.json";
    private String parentPath = null;
    private JSONObject jsonConfig = null;
    // 涂鸦上传filename定义
    private final static String SCRAWL_FILE_NAME = "scrawl";
    // 远程图片抓取filename定义
    private final static String REMOTE_FILE_NAME = "remote";

    /*
     * 通过一个给定的路径构建一个配置管理器， 该管理器要求地址路径所在目录下必须存在config.properties文件
     */
    private ConfigManager(String rootPath, String contextPath, String uri) throws FileNotFoundException, IOException {

        rootPath = rootPath.replace("\\", "/");

        this.contextPath = contextPath;

        this.rootPath = rootPath;

        this.originalPath = this.rootPath + uri;

        this.initEnv();

    }

    /**
     * 配置管理器构造工厂
     *
     * @param rootPath    服务器根路径
     * @param contextPath 服务器所在项目路径
     * @param uri         当前访问的uri
     * @return 配置管理器实例或者null
     */
    public static ConfigManager getInstance(String rootPath, String contextPath, String uri) {
        //logger.info("构造1，ConfigManager.getInstance()之前rootPath---------------------" + rootPath + ", contextPath=" + contextPath + ",uri----" + uri);
        try {
            return new ConfigManager(rootPath, contextPath, uri);
        } catch (Exception e) {

            return null;
        }

    }

    // 验证配置文件加载是否正确
    public boolean valid() {
        return this.jsonConfig != null;
    }

    public JSONObject getAllConfig() {
        JSONObject jsonConfig = this.jsonConfig;
        //logger.info("exec3，执行configManager.getAllConfig()返回----" + jsonConfig);
        return jsonConfig;

    }

    public Map<String, Object> getConfig(int type) {
        Map<String, Object> conf = new HashMap<String, Object>();
        String savePath = null;
        try {
            switch (type) {

                case ActionMap.UPLOAD_FILE:
                    conf.put("isBase64", "false");
                    conf.put("maxSize", this.jsonConfig.getLong("fileMaxSize"));
                    conf.put("allowFiles", this.getArray("fileAllowFiles"));
                    conf.put("fieldName", this.jsonConfig.getString("fileFieldName"));
                    savePath = this.jsonConfig.getString("filePathFormat");
                    break;

                case ActionMap.UPLOAD_IMAGE:
                    conf.put("isBase64", "false");
                    conf.put("maxSize", this.jsonConfig.getLong("imageMaxSize"));
                    conf.put("allowFiles", this.getArray("imageAllowFiles"));
                    conf.put("fieldName", this.jsonConfig.getString("imageFieldName"));
                    savePath = this.jsonConfig.getString("imagePathFormat");
                    break;

                case ActionMap.UPLOAD_VIDEO:
                    conf.put("maxSize", this.jsonConfig.getLong("videoMaxSize"));
                    conf.put("allowFiles", this.getArray("videoAllowFiles"));
                    conf.put("fieldName", this.jsonConfig.getString("videoFieldName"));
                    savePath = this.jsonConfig.getString("videoPathFormat");
                    break;

                case ActionMap.UPLOAD_SCRAWL:
                    conf.put("filename", ConfigManager.SCRAWL_FILE_NAME);
                    conf.put("maxSize", this.jsonConfig.getLong("scrawlMaxSize"));
                    conf.put("fieldName", this.jsonConfig.getString("scrawlFieldName"));
                    conf.put("isBase64", "true");
                    savePath = this.jsonConfig.getString("scrawlPathFormat");
                    break;

                case ActionMap.CATCH_IMAGE:
                    conf.put("filename", ConfigManager.REMOTE_FILE_NAME);
                    conf.put("filter", this.getArray("catcherLocalDomain"));
                    conf.put("maxSize", this.jsonConfig.getLong("catcherMaxSize"));
                    conf.put("allowFiles", this.getArray("catcherAllowFiles"));
                    conf.put("fieldName", this.jsonConfig.getString("catcherFieldName") + "[]");
                    savePath = this.jsonConfig.getString("catcherPathFormat");
                    break;

                case ActionMap.LIST_IMAGE:
                    conf.put("allowFiles", this.getArray("imageManagerAllowFiles"));
                    conf.put("dir", this.jsonConfig.getString("imageManagerListPath"));
                    conf.put("count", this.jsonConfig.getInt("imageManagerListSize"));
                    break;

                case ActionMap.LIST_FILE:
                    conf.put("allowFiles", this.getArray("fileManagerAllowFiles"));
                    conf.put("dir", this.jsonConfig.getString("fileManagerListPath"));
                    conf.put("count", this.jsonConfig.getInt("fileManagerListSize"));
                    break;

            }

            conf.put("savePath", savePath);
            conf.put("rootPath", this.rootPath);
        } catch (JSONException e) {
            //logger.info("exec3.2，根据actionCode执行configManager.getConfig( actionCode )-----------------------结果，发生异常！");
            e.printStackTrace();
        }
        //logger.info("exec3.5，根据actionCode执行configManager.getConfig( actionCode )-----------------------结果返回，conf=" + conf);
        return conf;

    }

    /**
     * Get rootPath from request,if not,find it from conf map.
     *
     * @param request
     * @param conf
     * @return
     * @author Ternence
     * @create 2015年1月31日
     */
    public static String getRootPath(HttpServletRequest request, Map<String, Object> conf) {
        Object rootPath = request.getAttribute("rootPath");
        if (rootPath != null) {
            return rootPath + "" + File.separatorChar;
        } else {
            return conf.get("rootPath") + "";
        }
    }

    private void initEnv() throws FileNotFoundException, IOException {
        //logger.info("构造2，new ConfigManager()之后toString----" + toString());
        File file = new File(this.originalPath);

        if (!file.isAbsolute()) {
            file = new File(file.getAbsolutePath());
        }

        this.parentPath = file.getParent();
        String getConfigPath = this.getConfigPath();
        String configContent = this.readFile(getConfigPath);
        //logger.info("构造4，initEnv()中根据上面 Path 去执行readFile()之后configContent:----" + configContent);

        try {
            JSONObject jsonConfig = new JSONObject(configContent);
            this.jsonConfig = jsonConfig;
        } catch (Exception e) {
            this.jsonConfig = null;
        }

    }

    private String getConfigPath() {
        String path = "";
        path = this.getClass().getResource("/").getPath() + ConfigManager.configFileName;
        boolean boo = new File(path).exists();
        if (!boo) {
            //path = this.parentPath + File.separator + ConfigManager.configFileName;
            path = getWebConfPath();
        }
        //logger.info("构造3，getConfigPath()中path,boo= " + boo + " , Path----" + path);
        return path;
    }

    private String getWebConfPath() {
        String path = "";
        try {
            Properties prop = new Properties();
            InputStream in = ConfigManager.class.getResourceAsStream("/conf/editor.properties");
            prop.load(in);
            String resPath = prop.getProperty("editor.server.resPath");
            String editorName = prop.getProperty("editor.server.editorName");
            String editorPath = prop.getProperty("editor.server.editorPath");
//            String webPath = ConfigManager.class.getResource(resPath).getPath();
            //logger.info("resPath:"+resPath+"---editorName:"+editorName+"---editorPath:"+editorPath+"---webPath:"+webPath);
            path = resPath + editorName + editorPath;

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("编辑器配置异常！");
        }
        return path;
    }

    private String readFile(String path) throws IOException {

        StringBuilder builder = new StringBuilder();
        try {

            InputStreamReader reader = new InputStreamReader( ConfigManager.class.getResourceAsStream(path),"UTF-8");

            BufferedReader bfReader = new BufferedReader(reader);

            String tmpContent = null;

            while ((tmpContent = bfReader.readLine()) != null) {
                builder.append(tmpContent);
            }

            bfReader.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return this.filter(builder.toString());

    }

    // 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
    private String filter(String filte) {

        return filte.replaceAll("/\\*[\\s\\S]*?\\*/", "");

    }

    private String[] getArray(String key) {

        JSONArray jsonArray = null;
        String[] result = null;
        try {
            jsonArray = this.jsonConfig.getJSONArray(key);
            result = new String[jsonArray.length()];

            for (int i = 0, len = jsonArray.length(); i < len; i++) {
                result[i] = jsonArray.getString(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public String toString() {
        return "ConfigManager [rootPath=" + rootPath + ", contextPath=" + contextPath + ", originalPath="
                + originalPath + ", parentPath=" + parentPath + ", jsonConfig=" + jsonConfig
                + "]";
    }

}
