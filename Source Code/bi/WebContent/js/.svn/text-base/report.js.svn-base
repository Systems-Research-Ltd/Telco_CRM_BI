

function addSummary(){
	$("#addSummaryCon").show();
}
 
function hideAddSummary(){
	$("#addSummaryCon").hide();
}

function cancelAddSummary(){
	$("#addSummaryCon").hide();
}


function showHideBtn(mode, id){
	if(mode == "add"){
		$("#addbtn_"+id).css("display", "inline");
		$("#cancelbtn_"+id).css("display", "inline");
		$("#editbtn_"+id).css("display", "none");
		$("#delbtn_"+id).css("display", "inline");
	} else {
		$("#addbtn_"+id).css("display", "none");
		$("#cancelbtn_"+id).css("display", "none");
		$("#editbtn_"+id).css("display", "inline");
		$("#delbtn_"+id).css("display", "none");
	}
}

function resetVals(label, value, id){
	$("#report\\:lbl_"+id).val(label);
	$("#report\\:val_"+id).val(value);
}



function changeGraphCol(){
	var gt = $("#report\\:graphType_input").val();
	if(gt == "bar" || gt == "stacked") {
		$("#gt2").show();
		$("#gt3").hide();
	} else {
		$("#gt3").show();
		$("#gt2").hide();
	}
	
}

function changeValue(id){
	$("#"+id+"Con").show();
	$("#"+id+"LabelCon").hide();
}

function saveValue(id){
	var val = $("#"+id).val();
	$("#"+id+"Label").html(val);
	$("#"+id+"Con").hide();
	$("#"+id+"LabelCon").show();
}

function cancel(id){
	$("#"+id+"Con").hide();
	$("#"+id+"LabelCon").show();
}

function positionLogo(){
	var x = $("#toolbar").offset().left + $("#toolbar").width();
	var y = $("#titleLabelCon div").offset().top;
	y = y + 10;
	x = x - 198;
	$("#logoCon").css("top", y+"px");
	$("#logoCon").css("left", x+"px");
}

function showHideGraph(){
	var val = $("#selectedGraph").val();
	//alert(val);
	if(val == 0){
		$("#reportDataGrid").css("width", "100%");
		$("#graphCon").hide();
	} else {
		$("#reportDataGrid").css("width", "49%");
		$("#graphCon").show();
	}	
}

function initDND() {
	$('.ui-treenode-leaf').draggable({
	 opacity: 0.6,
	 zIndex: ++PrimeFaces.zindex,
	 scope: 'treetotable',
	 helper: function() {  
	     var th = $(this);  	   
	     return th.clone().appendTo('body');  
	     }  
	  });
	
	$('.droppoint').droppable({
	 activeClass: 'ui-state-active',  
	 hoverClass: 'ui-state-highlight',  
	 tolerance: 'pointer',
	 scope: 'treetotable',  
	 drop: function(event, ui) {
	     var property = ui.draggable.find('.ui-treenode-label').text();
	     var table = ui.draggable.find('.ui-treenode-label').closest('.ui-treenode-parent').find('.ui-treenode-label')[0].innerHTML;
	     treetotable([  
	          {name: 'property', value:  property},
	          {name: 'table', value: table}
	         ]);
	     }  
	  });  
	
	$('.ui-datatable th').draggable({  
	    helper: 'clone',  
	    zIndex: ++PrimeFaces.zindex,
	    scope: 'tabletotree',
	    helper: function() {  
	        var th = $(this);  
	        return th.clone().appendTo('body').css('width', th.width());  
	    }  
	 });  
	
	 $('.ui-tree').droppable({  
	    helper: 'clone',   
	    activeClass: 'ui-state-active',  
	    hoverClass: 'ui-state-highlight',  
	    tolerance: 'touch',
	    scope: 'tabletotree',  
	    drop: function(event, ui) {                                 
	        tableToTree([  
	            {name: 'colIndex', value:  ui.draggable.index()}  
	        ]);  
	    }  
	 }); 
	
	 $('.graph-con').droppable({  
	    helper: 'clone',   
	    activeClass: 'ui-state-active',  
	    hoverClass: 'ui-state-highlight',  
	    tolerance: 'touch',
	    scope: 'tabletotree',  
	    drop: function(event, ui) {                                 
	        tableToGraph([  
	            {name: 'colIndex', value:  ui.draggable.index()}  
	        ]);  
	    }  
	 });                
}	

function editReport(){
	var tempRId = $("#tempReportId").val();
	var columnsSize = $("#columnsSize").val();
	if(columnsSize == 0 && tempRId != 0){
		//$("#loader").show();
		$("#editReportLink").trigger('click');
	}
}


function delSource(){
	var retVal = saveReport();
	if(retVal){
		sourceConfirmation.show();
	}
	return true;
}

function addSource(){
	var retVal = saveReport();
	if(retVal){
		dlg2.show();
	}
	return retVal;
}











