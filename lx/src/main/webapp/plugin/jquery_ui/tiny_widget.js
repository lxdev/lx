(function($) {
	function centralizeButton()
	{
		$(this).parent().find('.ui-dialog-buttonset').css({cssFloat: 'none', textAlign:'center'});
	}
	function closeDialog(dialog, returnValue){
		if(typeof(returnValue) != 'undefined' && returnValue){
			$(dialog).dialog('close');
		} else if(typeof(returnValue) == 'undefined' || returnValue===null || returnValue===undefined){
			$(dialog).dialog('close');
		}
	}
	function confirm(args){
		var opts = {
			title: '请确认您的操作',
			msg: '若果您确定，请单击“确定”，否则单击“取消”!',
			confirmText:'确认',
			cancelText: '取消',
			confirm: function(){},
			cancel: function(){},
			width: 300,
			height: 160
		};
		
		$.extend(opts, args);
		
		var dialog = $('<div/>').addClass('tiny_widget confirm_dialog');
		
		var msg = $('<div/>').addClass('msg').append(opts.msg);
		msg.appendTo(dialog);
		
		var buttons = {};
		buttons[opts.confirmText] = function(){
			var ret = opts.confirm.call(dialog.get(0));
			closeDialog(this, ret);
		}
		buttons[opts.cancelText] = function(){
			var ret = opts.cancel.call(dialog.get(0));
			closeDialog(this, ret);
		}
		
		dialog.appendTo(document.body ? document.body : document.all);
		
		dialog.dialog({
			title: opts.title,
			width: opts.width,
			height: opts.height,
			modal: true,
			shadow: true,
			open: centralizeButton,
			beforeClose: function(){
				$(this).remove();
			},
			buttons: buttons
		});
	}
	
	$.confirm = confirm;
	
	function warn(args){
		var opts = {
			title: '提示',
			msg: '提示信息！',
			okText:'确定',
			ok: function(){},
			close: function(){},
			width: 300,
			height: 160
		};
		
		if(typeof(args)=='string'){
			args = {msg: args};
		}
		
		$.extend(opts, args);
		
		var dialog = $('<div/>').addClass('tiny_widget warn_dialog').addClass('msg');
		
		dialog.append(opts.msg);
		
		//var msg = $('<div/>');
		//msg.addClass('msg');
		//msg.append(opts.msg);
		//msg.appendTo(dialog);
		
		var buttons = {};
		buttons[opts.okText] = function(){
			opts.ok.call(dialog.get(0));
			$(this).dialog('close');
		}
		
		dialog.appendTo(document.body ? document.body : document.all);
		dialog.dialog({
			title: opts.title,
			width: opts.width,
			height: opts.height,
			modal: true,
			shadow: true,
			open: centralizeButton,
			beforeClose: function(){
				try { opts.close(); } finally {}
				$(this).remove();
			},
			buttons: buttons
		});
	}
	
	$.warn = warn;
	
	$.alert = warn;
})(jQuery);