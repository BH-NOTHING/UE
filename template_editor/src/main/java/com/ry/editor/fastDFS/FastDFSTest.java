package com.ry.editor.fastDFS;

import com.ry.editor.platform.PlatformProperties;
import com.ry.editor.template.ueditor.hunter.ImageHunter;
import com.tt.fastdfs.FastdfsFacade;
import com.tt.fastdfs.client.FileMeta;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nickwu on 2017/5/15.
 */

@Controller
@RequestMapping("/editor/enter")
public class FastDFSTest {
    private static Logger logger = Logger.getLogger(FastDFSTest.class);

    /**
     * FastDFS测试上传和下载
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "testFastdfs")
    public String testFastdfs() throws Exception {
        FastdfsFacade facade = new FastdfsFacade();
        String[] serverInfos = facade.getServerInfo();
        String[] arr$ = serverInfos;
        int len$ = serverInfos.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String serverInfo = arr$[i$];
            System.out.println(serverInfo);
        }

        //上传代码测试
        String result = facade.uploadFile(new File("D:\\QQ\\281070951\\FileRecv\\test.jpg"));
        System.out.println("上传文件路径：" + result);
        result = result.substring(1);
        String groupName = result.substring(0, result.indexOf("/"));
        String remoteFilename = result.substring(result.indexOf("/") + 1);
        System.out.println("groupName:" + groupName + ",remoteFilename=" + remoteFilename);
        TimeUnit.SECONDS.sleep(1L);

        //下载代码测试
        FileMeta fileMeta = facade.getFileMetaInfo(groupName, remoteFilename);
        System.out.println(fileMeta);
        byte[] fileBytes = facade.downloadFile(groupName, remoteFilename);
        String localFileName = remoteFilename.substring(remoteFilename.lastIndexOf("/") + 1);
        IOUtils.write(fileBytes, new FileOutputStream(new File("D:\\"+localFileName)));
        TimeUnit.SECONDS.sleep(2L);

        //上传代码测试
        TimeUnit.SECONDS.sleep(1L);
        String result1 = facade.uploadFile(new File("D:\\QQ\\281070951\\FileRecv\\test.jpg"));
        System.out.println("上传图片文件路径：" + result1);
        result1 = result1.substring(1);
        String groupName1 = result1.substring(0, result1.indexOf("/"));
        String remoteFilename1 = result1.substring(result1.indexOf("/") + 1);
        System.out.println("groupName:" + groupName1 + ",remoteFilename=" + remoteFilename1);
        TimeUnit.SECONDS.sleep(1L);

        //下载
        byte[] fileBytes1 = facade.downloadFile(groupName1, remoteFilename1);
        String localFileName1 = remoteFilename1.substring(remoteFilename1.lastIndexOf("/") + 1);
        IOUtils.write(fileBytes1, new FileOutputStream(new File("D:\\"+localFileName1)));
        TimeUnit.SECONDS.sleep(2L);

        //测试的图片，在测试代码中需要清理FastDFS的脏文件，在正式代码中慎重使用，根据实际场景
//        facade.deleteFile(groupName, remoteFilename);
//        facade.deleteFile(groupName1, remoteFilename1);
        return "测试的图片";
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
        System.out.println(fastDFSOpen);
    }

}
