package com.ry.editor.qrcode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.editor.template.ueditor.ConfigManager;
import com.tt.fastdfs.FastdfsFacade;

@Controller
@RequestMapping("/editor/qrcode")
public class TestQRCode {
	private static Logger logger = Logger.getLogger(TestQRCode.class);
	@ResponseBody
	@RequestMapping(value = "qrcode")
	public String execQRCode(HttpServletRequest request) {
		String imgPath = "D:/XXXX.png";
		imgPath = request.getParameter("imgPath");
		String imgContent = "这是二维码的内容!";
		imgContent = request.getParameter("content");
		String imgType = "png";
		imgType = request.getParameter("imgType");
		int imgSize = 8;
		imgSize = Integer.parseInt(request.getParameter("imgSize"));
		//生成二维码
		TwoDimensionCode handler = new TwoDimensionCode();
		handler.encoderQRCode(imgContent, imgPath, imgType, imgSize);
		//解析二维码
		String decoderContent = handler.decoderQRCode(imgPath);
		//System.out.println("解析结果如下：" + decoderContent);

		//fastDFS上传二维码
		String str = imgPath;
		String FASTDFS = "", fastdfsPath = "", tracker_prefix = "";
		try {
			FASTDFS = request.getParameter("WEB_URL_FASTDFS");
			if (!isEmpty(FASTDFS) && FASTDFS.equals("1")) {
				tracker_prefix = getProp("/fdfs_client.conf");
				tracker_prefix = "http://" + tracker_prefix.split(":")[0] + ":8080";
				FastdfsFacade facade = new FastdfsFacade();
				byte[] getBytes = File2byte(imgPath);
				fastdfsPath = facade.uploadFile(getBytes, "fastdfs", "png");
				System.out.println("结束fastDFS上传图片-----tracker_prefix:" + tracker_prefix + "-----fastdfsPath:" + fastdfsPath);
			}
		} catch (Exception e) {
			System.out.println("异常fastDFS上传图片---------tracker_prefix:" + tracker_prefix + "-----Exception:" + e);
		}
		str = isEmpty(fastdfsPath) ? str : fastdfsPath;
		return str;
	}

	public static byte[] File2byte(String filePath) {
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	private static String getProp(String path) {// "/fdfs_client.conf"
		String param = "";
		try {
			Properties prop = new Properties();
			InputStream in = ConfigManager.class.getResourceAsStream(path);
			prop.load(in);
			param = prop.getProperty("tracker_server");
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getProp异常！");
		}
		return param;
	}

	private static boolean isEmpty(String str) {
		return str == null || str == "" || str.isEmpty();
	}

}
