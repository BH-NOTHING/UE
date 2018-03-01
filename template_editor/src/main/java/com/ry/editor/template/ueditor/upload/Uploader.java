package com.ry.editor.template.ueditor.upload;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ry.editor.template.ueditor.define.State;

public class Uploader {
	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;

	public Uploader(HttpServletRequest request, Map<String, Object> conf) {
		this.request = request;
		this.conf = conf;
	}

	public final State doExec() {
		State state = null;
		boolean isBase="true".equals(this.conf.get("isBase64"));
		if (isBase) {
			state = Base64Uploader.save(this.request, this.conf);
		} else {
			state = BinaryUploader.save(this.request, this.conf);
		}

		return state;
	}
}
