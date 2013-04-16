$(document).ready(function(){
	// Current page url
	var str=location.href.toLowerCase();

	$(".masthead li a").each(function() {
		// If the page url matches with the url of the <a> element
		if (str.indexOf(this.href.toLowerCase()) > -1) {
			$("li.active").removeClass("active");
			$(this).parent().addClass("active");
		}
	});
})