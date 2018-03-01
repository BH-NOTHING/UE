/**
 * 当前文件版本：v0.1
 * 一款简单的鼠标提示js库
 * ============================================
 * 项目地址：https://github.com/ALNY-AC/mTips
 * 作者：见两小儿便日
 * 作者地址：https://github.com/ALNY-AC
 * ============================================
 * 
 * */

var mTips = {
	c: {
		//配置项
		x: 10, //x偏移量,相对于鼠标
		y: 10, //y偏移量,相对于鼠标

		style: {
			'position': 'fixed',
			'padding': '8px 12px',
			'color': '#fff',
			'border-radius': '5px',
			'font-family': "微软雅黑",
			'z-index': '999',
			'display': 'inline',
			'font-size': '14px',
			'background-color': 'rgba(0, 0, 0, 0.5)',
			'color': '#fff'

		}
	},
	//show方法，用于显示提示

	s: function(text, a, b) {
		var style;
		var fun;

		if(typeof(a) == 'string') {
			style = a;
			fun = b;
		} else if(typeof(a) == 'function') {
			style = b;
			fun = a;
		}

		if(style == 'undefined' || style == null) {
			style = 'default';
		}

		var doc = $('<div></div>').addClass('mTips mTips-' + style).html(text).appendTo('body');
		if(doc.css('z-index') !== '999') {
			doc.css(this.c.style);
		}

		$(document).on('mousemove', function(e) {
			$(".mTips").offset({
				top: e.pageY + mTips.c.x,
				left: e.pageX + mTips.c.y
			})
		});

		if(fun != null && typeof(fun) != 'undefined') {
			fun();
		}

	},

	//hide方法，用于隐藏和删除提示
	h: function(fun) {

		$('.mTips').remove();
		if(fun != 'undefined' && fun != null) {
			fun();
		}

	},

	//用于给相关属性添加提示功能
	m: function() {

		$(document).on('mouseenter', '[data-mtpis]', function(e) {
			mTips.s($(this).attr('data-mtpis'), $(this).attr('data-mtpis-style'));
		});

		$(document).on('mouseleave', '[data-mtpis]', function(e) {
			mTips.h();
		});

	}

}
mTips.m(); //通过此函数激活所有的