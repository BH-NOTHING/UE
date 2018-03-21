/**
 * 取数规则 服务描述：
 * 1.在VOP上面调用，Demo如下
 *		var selectTemplate = $W.databind.databind({
 *			id : 'load',						//随便自定义
 *			isSrv : true, 						// 必须是这个
 *			groupId : "editor.editorSrv"		// 服务分组路径：editor.editorSrv--->editor是bundleId，editorSrv是服务分组名
 *		});
 *		selectTemplate.submit("ruleSrv", {	 	// 服务名
 *			funName : 'findByRuleId', 			// 方法名
 *			ruleId:"J2SU62UZ118"				// parem，可以多个
 * 			XX:XX
 *		}, {
 *			async : false						// 同异步
 *		}, function(res) {						// 返回结果
 *			temp = res.data;
 *		});
 * 
 * @Autor : King
 * @Date : 2017-05-18
 */
var toJs = require('nodejava').toJs;
var spring = require("spring");
var handler = {
	/**
	 * findRulsList
	 */
	findRulsList : function(param) {
		var serviceMgr = require('serviceMgr');
		var list=serviceMgr.callService("/business_rules/func/rule/ruleManager/ruleList/pwp_rule_load", param);
		return list;
	},
	/**
	 * 根据ID查询某一个
	 */
	findById : function(myParam) {
		var result = {};
		// 根据ID获取spring的bean对象
		var ruleRenderManager = spring.getBean("ruleRenderManager");
		var javaObject = ruleRenderManager.renderBycode(myParam.ruleId, myParam.param);
		// 支持java.util.Collection、java.util.Map、java数组、javaBean对象
		result.data = toJs.parse(javaObject);
		result.msg = '查询成功';
		return result;
	},
	/**
	 * 根据Param查询某一个或者多个
	 */
	findByParam : function(param) {
		var result = {};
		return result;
	},
	/**
	 * 加载所有的
	 */
	findAll : function(param) {
		var result = {};
		result.msg = 'load所有成功';
		return result;
	},
	/**
	 * 新增方法
	 * {pname:'',label:'',descr:'',require:'1',supplement:'',text_color:'',default_value:''}
	 */
	add : function(param) {

		var result = {};
		result.msg = '新增成功';
		return result;
	},
	/**
	 * 删除方法
	 */
	del : function(param) {
		var result = {};
		result.msg = '删除成功';
		return result;
	},
	/**
	 * 编辑方法
	 * {id:1,pname:'',label:'',descr:'',require:'1',supplement:'',text_color:'',default_value:''}
	 */
	update : function(param) {
		var result = {};
		result.msg = '修改成功';
		return result;
	}
};

return handler[param.funName](param);
