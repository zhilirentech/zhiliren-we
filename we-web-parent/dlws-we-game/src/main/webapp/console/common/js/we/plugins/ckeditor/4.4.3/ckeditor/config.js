/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.toolbar = 'Basic';
	config.toolbar = 'Full';
	config.toolbar_Full = [
//		['Source','-','Save','NewPage','Preview','-','Templates'],
//		['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
		['Format','Font','FontSize'],
		['TextColor','BGColor'],
		['Undo','Redo','-','RemoveFormat'],
//		['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
//		'/',
		['Bold','Italic','Underline','-','Subscript','Superscript'],
//		['NumberedList','BulletedList','-','Outdent','Indent'],
		['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
		['Table','Smiley','SpecialChar'],
//		['Link','Unlink','Anchor'],
		'/'
	];
	};
