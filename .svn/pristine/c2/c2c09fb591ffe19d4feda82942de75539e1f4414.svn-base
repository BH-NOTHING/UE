package com.ry.editor.template.ueditor.upload;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;

import com.ry.editor.template.ueditor.ConfigManager;
import com.ry.editor.template.ueditor.PathFormat;
import com.ry.editor.template.ueditor.define.AppInfo;
import com.ry.editor.template.ueditor.define.BaseState;
import com.ry.editor.template.ueditor.define.FileType;
import com.ry.editor.template.ueditor.define.State;
import org.apache.log4j.Logger;

public final class Base64Uploader {
	private static Logger logger = Logger.getLogger(Base64Uploader.class);

	public static State save(HttpServletRequest request, Map<String, Object> conf) {
		//logger.info("execA3.7，Base64Uploader执行文件操作---------- save");
		String filedName = (String) conf.get("fieldName");
		String fileName = request.getParameter(filedName);
		byte[] data = decode(fileName);

		long maxSize = ((Long) conf.get("maxSize")).longValue();

		if (!validSize(data, maxSize)) {
			return new BaseState(false, AppInfo.MAX_SIZE);
		}

		String suffix = FileType.getSuffix("JPG");

		String savePath = PathFormat.parse((String) conf.get("savePath"), (String) conf.get("filename"));
		//logger.info("execA3.9，Base64Uploader执行文件操作---------- savePath="+savePath+",suffix="+suffix);
		savePath = savePath + suffix;
		String rootPath = ConfigManager.getRootPath(request,conf);
		String physicalPath = rootPath + savePath;

		State storageState = StorageManager.saveBinaryFile(data, physicalPath);

		if (storageState.isSuccess()) {
			storageState.putInfo("url", PathFormat.format(savePath));
			storageState.putInfo("type", suffix);
			storageState.putInfo("original", "");
		}

		return storageState;
	}

	private static byte[] decode(String content) {
		return Base64.decodeBase64(content);
	}

	private static boolean validSize(byte[] data, long length) {
		return data.length <= length;
	}
	
}