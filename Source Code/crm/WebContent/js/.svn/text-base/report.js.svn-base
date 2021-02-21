function save() {
	saveCols();
	setCols();
	//test();
}

function saveCols() {
	var cols = "";
	var colTitles = "";
	cols = $("#report\\:cols").val();
	colsTitles = $("#report\\:colsTitles").val();
	$("#report\\:repPickList_target option").each(function() {
		var val = $(this).val();
		var title = $(this).text();
		if(val != "undefined" && val != "" && val != null) {
			if(cols != "" && cols != null){
				if (cols.indexOf(val) == -1) {
					cols += ";" + val;
					colsTitles += "; "+title;
				}
			} else {
				cols = val;
				colsTitles += title;
			}	
		}
	});
	
	$("#report\\:cols").val(cols);	
	$("#report\\:colsTitles").val(colsTitles);
}

function setCols() {
	var cols = $("#report\\:cols").val();
	var colsArr = cols.split(";");
	var colsTitles = $("#report\\:colsTitles").val();
	var colsTitlesArr = colsTitles.split(";");
	var str = "";
	var str1 = "";
	var aliases = "";
	for ( var i = 0; i < colsArr.length; i++) {
		str += "<li class='ui-picklist-item ui-corner-all' data-item-value='"
				+ colsArr[i] + "' style=''>" + colsTitlesArr[i] + "</li>";
		str1 += "<option value='" + colsArr[i] + "'>" + colsTitlesArr[i]
				+ "</option>";
		
		var als = colsArr[i].split(".");
		if(aliases != "" && aliases != null){
			if (aliases.indexOf(als[0]) == -1) {
				aliases += ","+als[0];
			}
		} else {
			aliases = als[0];
		}	

	}
	$(".ui-picklist-target").html(str);
	$("#report\\:repPickList_target").html(str1);
	/*alert("aliase are"+aliases);
	
	var from = "";
	var aliasArr = aliases.split(",");
	var frm = $("#report\\:from").val();
	//alert("from ----"+frm);
	var frmArr = frm.split(",");
	alert("from array "+frmArr);
	for(var i=0; i<aliasArr.length; i++){
		for(var j=i; j<frmArr.length; j++){
			var f = frmArr[j].split(" ");
			if(f[1] == aliasArr[i]){
			//	alert(f[1]);
				if(from != "" && from != null) {
					from += ","+frmArr[j]; 
				} else {
					from = frmArr[j]; 
				}
			}
		}
	}
	alert("from"+from);*/
}

function editReport(){
	//setCols();
	$("#report\\:updateButton").css("display", "inline");
	$("#report\\:delButton").css("display", "inline");
	$("#report\\:titleEdit").css("display", "inline");
	$("#report\\:descEdit").css("display", "inline");
	$("#report\\:saveButton").css("display", "none");
	$("#report\\:createReport").css("display", "none");
	$("#report\\:previewButton").css("display", "none");
	$("#report\\:cancelButton").css("display", "inline");
	
}


function setFromClause() {
	var frm = $("#report\\:from").val();
	var tbl = $("#report\\:table_input").val();
	var alias =  $("#report\\:selalias").val();
	tbl = tbl+" "+alias;
	if (frm != "" && frm != null) {
		if (frm.indexOf(tbl) == -1) {
			frm += ";"+tbl;
		}
	} else {
		frm += tbl;
	}
	$("#report\\:from").val(frm);
}

function addSummary(){
	if($("#slv").css("display") == "none"){
		positionElements(25);
	}
	$("#slv").show();
	$("#report\\:labels").val("Label");
	$("#report\\:values").val("Value");
}

function resetReport(){
	$("#rc").css("height", "555px");
	$("#lc").css("height", "575px");
}



function positionElements(inc){
	var inc = inc;
	var rch = parseInt($("#rc").css("height"));
	rch += inc;
	var lch = parseInt($("#lc").css("height"));
	var crh = parseInt($("#cr").css("height"));
	lch += inc;
	var tt = parseInt($(".ui-picklist-target").css("top"));
//	alert(tt);
	tt+= inc;
	//$(".ui-picklist-target").css("top", tt+"px important");
	$('.ui-picklist-target').attr('style', 'top: '+tt+'px !important');
	$("#rc").css("height", rch+"px");
	$("#lc").css("height", lch+"px");
	$("#cr").css("height", lch+"px");
	
}


function cancelAddSummary(dec){
	var inc = dec;
	var rch = parseInt($("#rc").css("height"));
	rch -= inc;
	var lch = parseInt($("#lc").css("height"));
	var crh = parseInt($("#cr").css("height"));
	lch -= inc;
	var tt = parseInt($(".ui-picklist-target").css("top"));
//	alert(tt);
	tt-= inc;
	//$(".ui-picklist-target").css("top", tt+"px important");
	$('.ui-picklist-target').attr('style', 'top: '+tt+'px !important');
	$("#rc").css("height", rch+"px");
	$("#lc").css("height", lch+"px");
	$("#cr").css("top", lch+"px");
	hideAddSummary();
	
}

function hideAddSummary(){
	$("#slv").hide();
}


function showHideBtn(mode, id){
	if(mode == "add"){
		$("#report\\:addbtn_"+id).css("display", "inline");
		$("#report\\:cancelbtn_"+id).css("display", "inline");
		$("#report\\:editbtn_"+id).css("display", "none");
	} else {
		$("#report\\:addbtn_"+id).css("display", "none");
		$("#report\\:cancelbtn_"+id).css("display", "none");
		$("#report\\:editbtn_"+id).css("display", "inline");
	}
}

function resetVals(label, value, id){
	$("#report\\:lbl_"+id).val(label);
	$("#report\\:val_"+id).val(value);
}


function focusField(id, txt){
	var val = $("#report\\:"+id).val();
	if(val == txt) {
		$("#report\\:"+id).val("");
		$("#report\\:"+id).removeClass("grey_txt");
		$("#report\\:"+id).addClass("black_txt");
	}	
}

function blurField(id, txt){
	var val = $("#report\\:"+id).val();
	if((val == "")) {
		$("#report\\:"+id).val(txt);
		$("#report\\:"+id).addClass("grey_txt");
		$("#report\\:"+id).removeClass("black_txt");
	}	
}





