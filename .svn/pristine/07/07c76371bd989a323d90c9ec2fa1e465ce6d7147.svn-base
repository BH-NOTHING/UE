$(function() {
	/*$(document).on('click', 'span[id^="selVal_"]', function() {
		var v = this.getAttribute('val');
		var sv = $(this).siblings("#selVal")[0];
		sv.innerHTML =  v ;
	});*/
	
	/*空值检验*/
	$(document).on('blur', 'span[class~="checkNull"]', function() {
		var el = this.getElementsByClassName('sde-value')[0],
		str = el.innerText.replace(/(^\s*)|(\s*$)/g, "");
		if (str != '') {
			var sibL = $(el).siblings(".sde-left")[0];
			sibL.style.color = '#0000FF';
			var sibR = $(el).siblings(".sde-right")[0];
            sibR.style.color = '#0000FF';
            $(sibR).find(".sde-validatebox").remove();
        }
	});


});

