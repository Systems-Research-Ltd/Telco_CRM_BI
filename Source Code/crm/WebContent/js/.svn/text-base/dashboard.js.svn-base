$(document).ready(function() {
	/*$("#carousel_ul li button").click(function(event) {
		$("#campaignDetails").hide();
		//event.stopPropagation();
		
	});*/
	$("#mainContainer").click(function(event) {
		//alert(event.target.nodeName);
		if(event.target.nodeName === "DIV" || event.target.nodeName === "div" || event.target.nodeName ==="li"){
			$("#cDetail").trigger('click');
		} 
		if(event.target.nodeName === "BUTTON" || event.target.nodeName == "button"){
			$("#campaignDetails").hide();
		}
		
		
	});
	// options( 1 - ON , 0 - OFF)
	var auto_slide = 0;
	var hover_pause = 1;
	var key_slide = 1;
	var auto_slide_seconds = 6000;
	var lcampCounter = parseInt($("#lanunchedCampaignsCounter").val());
    if(lcampCounter > 3){
    	auto_slide = 1;
    	$('#carousel_ul li:first').before($('#carousel_ul li:last'));
    	auto_slide_seconds = 6000;
    }
	// speed of auto slide(
	
	/*
	 * IMPORTANT: i know the variable is called ...seconds but it's in
	 * milliseconds ( multiplied with 1000) '
	 */

	/*
	 * move he last list item before the first item. The purpose of this is if
	 * the user clicks to slide left he will be able to see the last item.
	 */
    
//	

	// check if auto sliding is enabled
	if (auto_slide == 1) {
	//	alert("slied");
		/*
		 * set the interval (loop) to call function slide with option 'right'
		 * and set the interval time to the variable we declared previously
		 */
		var timer = setInterval('slide("right")', auto_slide_seconds);

		/*
		 * and change the value of our hidden field that hold info about the
		 * interval, setting it to the number of milliseconds we declared
		 * previously
		 */
		$('#hidden_auto_slide_seconds').val(auto_slide_seconds);
	}

	// check if hover pause is enabled
	if (hover_pause == 1) {
		// when hovered over the list
		$('#carousel_ul').hover(function() {
			// stop the interval
			clearInterval(timer);
		}, function() {
			// and when mouseout start it again
		//	alert("slide right");
			if (auto_slide == 1) {
				timer = setInterval('slide("right")', auto_slide_seconds);
			}	
		});

	}

	// check if key sliding is enabled
	if (key_slide == 1) {

		// binding keypress function
		$(document).bind('keypress', function(e) {
			// keyCode for left arrow is 37 and for right it's 39 '
			if (e.keyCode == 37) {
				// initialize the slide to left function
				//alert("key code");
				slide('left');
			} else if (e.keyCode == 39) {
				// initialize the slide to right function
				//alert("key right");
				slide('right');
			}
		});

	}
	
	
});

// FUNCTIONS BELLOW

// slide function
function slide(where) {
	//alert("slide");
	// get the item width
	var item_width = $('#carousel_ul li').outerWidth() + 20;

	/*
	 * using a if statement and the where variable check we will check where the
	 * user wants to slide (left or right)
	 */
	if (where == 'left') {
		// ...calculating the new left indent of the unordered list (ul) for
		// left sliding
		var left_indent = parseInt($('#carousel_ul').css('left')) + item_width;
	} else {
		// ...calculating the new left indent of the unordered list (ul) for
		// right sliding
		var left_indent = parseInt($('#carousel_ul').css('left')) - item_width;

	}

	// make the sliding effect using jQuery's animate function... '
	$('#carousel_ul:not(:animated)').animate({
		'left' : left_indent
	}, 1500, function() {
		//alert("left indent");

		/*
		 * when the animation finishes use the if statement again, and make an
		 * ilussion of infinity by changing place of last or first item
		 */
		if (where == 'left') {
			// ...and if it slided to left we put the last item before the first
			// item
			$('#carousel_ul li:first').before($('#carousel_ul li:last'));
		} else {
			// ...and if it slided to right we put the first item after the last
			// item
			$('#carousel_ul li:last').after($('#carousel_ul li:first'));
		}

		// ...and then just get back the default left indent
		$('#carousel_ul').css({
			'left' : '20px'
		});
	});
}

function showCampaign(index, campaignId) {

	$("#carousel_ul li").each(function() {
		$(this).css("background", "#F0F0F0");
	});
	//$("#" + index).parent().css("background", "#FFFFFF");
	$("#campaignId").val(campaignId);
}