function isEmpty(field) {
var blankSpaceRE = /^[\s]*$/;
if(field.value == "" || field.value === null || field.value.match(blankSpaceRE)){
return true;
} else {
return false;
}
}
function isValidEmail(field) {
var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
if (field.value.match(emailExp)) {
return true;
}
return false;
}
function isComboBoxValueNotSelected(field, index){
if(field.options[field.selectedIndex].value === index){
return true;
} else {
return false;
}
}
function isComboBoxValueSelected(field, index){
if(field.options[field.selectedIndex].value === index){
return true;
} else {
return false;
}
}
function isRadioBtnChecked(radioBtnName) {
var radioLength = radioBtnName.length;
var radioBtnSelected = false;
for(var i = 0; i<radioLength; i++){
if(radioBtnName[i].checked){
radioBtnSelected = true;
break;
}
}
return radioBtnSelected;
}
function isValidPassword(field){
	var passwordExp =  /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])\w{6,}$/;
	if (field.value.match(passwordExp)) {
	return true;
	}
	return false;
	
}

function showHide(action){
	//setCols();
	if(action == 'hide') {
		$("#loyaltyForm\\:rule1").parent().parent().css("display", "none");
		$("#loyaltyForm\\:rulecondition1").parent().parent().css("display", "none");
		$("#loyaltyForm\\:condition1").parent().parent().css("display", "none");
		$("#loyaltyForm\\:clearbtn").css("display", "none");	
		$("#loyaltyForm\\:pattern").parent().parent().css("display", "none");
	} else {
		$("#loyaltyForm\\:rule1").parent().parent().show();
		$("#loyaltyForm\\:rulecondition1").parent().parent().show();
		$("#loyaltyForm\\:condition1").parent().parent().show();
		$("#loyaltyForm\\:pattern").parent().parent().show();
		$("#loyaltyForm\\:clearbtn").show();
		
	}
	
}

function showfieldset(action){
	if(action == 'hide'){
		$("#searchuser\\:fieldset").parent().parent().css("display", "none");	
	}
	else{
		$("#searchuser\\:fieldset").parent().parent().show();	
	}
}

$(document).ready(function() {
	showHide('hide');
	});

function addRule(){
	$("#slv").show();
	$("#loyaltyForm\\:ruletype").val("ruletype");
	$("#loyaltyForm\\:rulecondition").val("rulecondition");
	$("#loyaltyForm\\:condition").val("condition");
	
}

function hideRule(){
	$("#slv").hide();
}