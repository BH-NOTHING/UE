var tabsUtil = $W.tabsUtil.create({id:'tabs'});
$(function(){
	var mm = $("#mm").widgets({
		xtype : 'menu'
	});
	var tabs = $("#tabs").widgets({
        xtype:"tabs",
        fit:true,
        border:false,
        closable:true,
        onContextMenu: function(e, title, index) {
            e.preventDefault();
            tabs.select(title);
            mm.show({
                left: e.pageX,
                top: e.pageY
            });
        },
        tools:[{  
	        iconCls:'icon-reload',  
	        handler:function(){
	        	tabsUtil.flushTab();  
	        }  
	    }]
    });
	
	$("#menu").widgets({
        xtype : 'tree',
        data : __menuData,
        onSelect : function(node){
        	if(node.attributes.allPath){
        		tabsUtil.addTab(node.text, ctx + node.attributes.url);
        	}else{
        		tabsUtil.addTab(node.text, ctx + "/bundle/editor/func/sdeDesc" + node.attributes.url);
        	}
        	
        }
	});
});