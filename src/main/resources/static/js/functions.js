
function setLangUa() {
 if (location.pathname.indexOf("?") > -1){
     window.location.replace(location.pathname+'&lang=ua');
 }
 else{
    window.location.replace(location.pathname+'?lang=ua');
 }

}

		$(".js-select2").select2({
			closeOnSelect : false,
			placeholder : "Placeholder",
			// allowHtml: true,
			allowClear: true,
			tags: true
		});
function setLangEn() {
 if (location.pathname.indexOf("?") > -1){
     window.location.replace(location.pathname+'&lang=en');
 }
 else{
    window.location.replace(location.pathname+'?lang=en');
 }

}