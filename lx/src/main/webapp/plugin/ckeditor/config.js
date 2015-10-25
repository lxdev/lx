/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	config.language = 'zh-cn';
	//config.uiColor = '#AADC6E';
	config.toolbar='Full';
	
	config.toolbar_Full = [

	                       ['NewPage','Preview','Bold','Italic','Underline','Strike'],

	                       ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],

	                       ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],

	                       ['Styles','Format','Font','FontSize','TextColor','BGColor']

	                    ];
};
