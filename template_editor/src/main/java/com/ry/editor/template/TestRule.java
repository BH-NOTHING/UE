package com.ry.editor.template;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.pwp.rule.parse.render.RuleRenderManager;

@Controller
@RequestMapping("/editor/testRule")
public class TestRule {
	private static Logger logger = Logger.getLogger(TestRule.class);

	private String temp = null;
	@Autowired
	RuleRenderManager ruleRenderManager;

	@ResponseBody
	@RequestMapping(value = "renderBycode")
	public Object renderBycode(HttpServletRequest request) {
		// http://localhost:8090/UE/editor/test/renderBycode.do
		logger.info("--------------------开始动态取数-----------------------------");
		Object obj = null;
		try {
			obj = ruleRenderManager.renderBycode("J2SU62UZ118", "");
		} catch (Exception e) {
			logger.info("--------------------异常-----------------------------"+ e);
		}
		return obj;
	}

}
