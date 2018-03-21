package com.ry.editor.template.ueditor;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.editor.platform.PlatformProperties;
import com.tt.fastdfs.FastdfsFacade;

/**
 * Created by Nickwu on 2017/5/15.
 */

@Controller
@RequestMapping("/editor/enter")
public class FastDFS {
    private static Logger logger = Logger.getLogger(FastDFS.class);

    /**
     * FastDFS测试上传
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "uploadFile")
    public String uploadFile() throws Exception {
        FastdfsFacade facade = new FastdfsFacade();
        //上传代码测试
        String str = "D:\\QQ\\281070951\\FileRecv\\test.jpg";
        File file = new File(str);
        String result = facade.uploadFile(file);
        logger.info("上传文件路径---result：" + result);					/*/group1/M00/00/02/CgoKylmMBLOAcKOHAC_4ldFT9Lw277.jpg*/
        result = result.substring(1);										/*group1/M00/00/02/CgoKylmMBLOAcKOHAC_4ldFT9Lw277.jpg*/
        String groupName = "group1";		/*group1*/
        String remoteFilename = "M00/00/02/CgoKylmMBLOAcKOHAC_4ldFT9Lw277.jpg";	/**/
        String filename = result.substring(result.lastIndexOf("/") + 1);	/*CgoKylmMBLOAcKOHAC_4ldFT9Lw277.jpg*/
        logger.info("上传文件路径---groupName:" + groupName + ",remoteFilename=" + remoteFilename+ ",filename=" + filename);
        return "上传图片:"+result;
    }
    /**
     * FastDFS下载
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "downloadFile")
    public String downloadFile() throws Exception {
        FastdfsFacade facade = new FastdfsFacade();
        //下载代码测试
        String groupName = "group1";
        String fileNa ="CgoKylmMBLOAcKOHAC_4ldFT9Lw277.jpg"; 
        String remoteFilename = "M00/00/02/"+fileNa;
        String url = "D:\\download\\"+fileNa;
        byte[] fileBytes = facade.downloadFile(groupName, remoteFilename);
        FileOutputStream fos = new FileOutputStream(new File(url));
        IOUtils.write(fileBytes,fos);
        return "下载图片:"+url;
    }
    /**
     * FastDFS测试上传
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "getServerInfo")
    public String getServerInfo() throws Exception {
        FastdfsFacade facade = new FastdfsFacade();
        
        String[] serverInfos = facade.getServerInfo();
        String[] arr = serverInfos;
        int len = serverInfos.length;
        for(int i = 0; i < len; ++i) {
            String serverInfo = arr[i];
            logger.info(serverInfo);
        }
        return "获取FastdfsFacade服务信息:"+serverInfos.toString();
    }
    @Test
    public void testFastDFSOpen() {
        //fastdfs.open
        String fastDFSOpen = PlatformProperties.getInstance().getProperty("fastdfs.open");
        if(StringUtils.isNotBlank(fastDFSOpen) && "true".equalsIgnoreCase(fastDFSOpen)) {
            //启用了fastDFS，将图片上传到FastDFS服务器
        } else {
            //未启用，使用本地图片IOUtils写入到webapps的image下面

        }
        logger.info(fastDFSOpen);
    }

}
