/**
 * Interface Elements for jQuery
 * Fisheye menu
 * 
 * http://interface.eyecon.ro
 * 
 * Copyright (c) 2006 Stefan Petre
 * Dual licensed under the MIT (MIT-LICENSE.txt) 
 * and GPL (GPL-LICENSE.txt) licenses.
 *
 */

/**
 * Build a Fisheye menu from a list of links
 *
 * @name Fisheye
 * @description Build a Fisheye menu from a list of links
 * @param Hash hash A hash of parameters
 * @option String items items selection
 * @option String container container element
 * @option Integer itemWidth the minimum width for each item
 * @option Integer maxWidth the maximum width for each item
 * @option String itemsText selection of element that contains the text for each item
 * @option Integer proximity the distance from element that make item to interact
 * @option String valign vertical alignment
 * @option String halign horizontal alignment
 *
 * @type jQuery
 * @cat Plugins/Interface
 * @author Stefan Petre
 */
var imgDistince = 40;//图片与图片之间的间距
jQuery.iFisheye = {

	build : function(options)
	{

		return this.each(
			function()
			{
				var el = this;
				el.fisheyeCfg = {
					items : jQuery(options.items, this),
					container: jQuery(options.container, this),
					pos : jQuery.iUtil.getPosition(this),
					itemWidth: options.itemWidth,
					itemsText: options.itemsText,
					proximity: options.proximity,
					valign: options.valign,
					halign: options.halign,
					maxWidth : options.maxWidth
				};
				jQuery.iFisheye.positionContainer(el, 0);
				jQuery(window).bind(
					'resize',
					function()
					{
						el.fisheyeCfg.pos = jQuery.iUtil.getPosition(el);
						jQuery.iFisheye.positionContainer(el, 0);
						jQuery.iFisheye.positionItems(el);
					}
				);
				jQuery.iFisheye.positionItems(el);
				//el.fisheyeCfg.items
				//	.bind(
				//		'mouseover',
				//		function()
				//		{
				//			jQuery(el.fisheyeCfg.itemsText, this).get(0).style.display = 'block';
				//		}
				//	)
				//	.bind(
				//		'mouseout',
				//		function()
				//		{
				//			jQuery(el.fisheyeCfg.itemsText, this).get(0).style.display = 'none';
				//		}
				//	);
				jQuery(this).bind(
					'mousemove',function(e){//e是事件变量
						jQuery.iFisheye.bindMouseMove(e,el);
						//console.log("222");
					}
				);
			}
		)
	},
	
	positionContainer : function(el, toAdd)
	{
		//console.log(el.fisheyeCfg.halign);
		if (el.fisheyeCfg.halign)
			if (el.fisheyeCfg.halign == 'center')
				el.fisheyeCfg.container.get(0).style.left = (el.offsetWidth - el.fisheyeCfg.itemWidth * el.fisheyeCfg.items.size()-imgDistince*(el.fisheyeCfg.items.size()-1))/2 - toAdd/2 + 'px';
			else if (el.fisheyeCfg.halign == 'left')
				el.fisheyeCfg.container.get(0).style.left =  - toAdd/el.fisheyeCfg.items.size()-imgDistince + 'px';
				//el.fisheyeCfg.container.get(0).style.left = $(document).width()/2;
			else if (el.fisheyeCfg.halign == 'right')
				el.fisheyeCfg.container.get(0).style.left =  (el.offsetWidth - el.fisheyeCfg.itemWidth * el.fisheyeCfg.items.size()) - toAdd/2 + 'px';
		el.fisheyeCfg.container.get(0).style.width = el.fisheyeCfg.itemWidth * el.fisheyeCfg.items.size() + toAdd + 'px';
	},
	
	positionItems : function(el)
	{
		el.fisheyeCfg.items.each(
			function(nr)
			{
				this.style.width = el.fisheyeCfg.itemWidth + 'px';
				//this.style.left = el.fisheyeCfg.itemWidth * nr + 'px';
				if (nr == 0) {
					this.style.left = el.fisheyeCfg.itemWidth * nr+ 'px';
				}
				else {
					this.style.left = el.fisheyeCfg.itemWidth * nr + imgDistince * nr + 'px';
				}
			}
		);
	},
	bindMouseMove:function (event,elo) {//放大效果实现
		//var pointer = jQuery.iUtil.getPointer(event);
		//var toAdd = 0;
		//if (elo.fisheyeCfg.halign && elo.fisheyeCfg.halign == 'center')
		//	var posx = pointer.x - elo.fisheyeCfg.pos.x - (elo.offsetWidth - elo.fisheyeCfg.itemWidth * elo.fisheyeCfg.items.size())/2 - elo.fisheyeCfg.itemWidth/2;
		//else if (elo.fisheyeCfg.halign && elo.fisheyeCfg.halign == 'right')
		//	var posx = pointer.x - elo.fisheyeCfg.pos.x - elo.offsetWidth + elo.fisheyeCfg.itemWidth * elo.fisheyeCfg.items.size();
		//else
		//	var posx = pointer.x - elo.fisheyeCfg.pos.x;
		//var posy = Math.pow(pointer.y - elo.fisheyeCfg.pos.y - elo.offsetHeight/2,2);
		//elo.fisheyeCfg.items.each(
		//	function(nr) {
		//		//debugger;
		//		distance = Math.sqrt(
		//			Math.pow(posx - nr * elo.fisheyeCfg.itemWidth, 2)
		//			+ posy
		//		);
		//		distance -= elo.fisheyeCfg.itemWidth / 2;
        //
		//		distance = distance < 0 ? 0 : distance;
		//		distance = distance > elo.fisheyeCfg.proximity ? elo.fisheyeCfg.proximity : distance;
		//		distance = elo.fisheyeCfg.proximity - distance;
        //
		//		extraWidth = elo.fisheyeCfg.maxWidth * distance / elo.fisheyeCfg.proximity;
        //
		//		this.style.width = elo.fisheyeCfg.itemWidth + extraWidth + 'px';
		//		if (nr == 0) {
		//			this.style.left = elo.fisheyeCfg.itemWidth * nr + toAdd + 'px';
		//		}
		//		else {
		//			this.style.left = elo.fisheyeCfg.itemWidth * nr + toAdd + imgDistince * nr + 'px';
		//		}
		//		//console.log(this.style.left);
		//		toAdd += extraWidth;
		//	}
		//);
		//jQuery.iFisheye.positionContainer(elo, toAdd);
	}
};

jQuery.fn.Fisheye = jQuery.iFisheye.build;