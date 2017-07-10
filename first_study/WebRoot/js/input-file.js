$(function(){
var textButton="<input type='text' name='textfield' id='textfield' class='type-file-text' width='250px' disabled='true'  />  <input type='button' name='button' id='button' value='浏览' class='type-file-button' />"
		    $(textButton).insertBefore("#file");
		    $("#file").change(function(){
		        $("#textfield").val($("#file").val());
		    });
});