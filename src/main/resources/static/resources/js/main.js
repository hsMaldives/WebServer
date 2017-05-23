function callSettingActivity() {
	if (window.whereYou) {
		window.whereYou.callSettingsActivity();
	} else {
		alert("App에서 실행해주세요!");
	}
}

$(function() {
	$('#footer-menu .dropup').on('show.bs.dropdown', function() {
		$(this).find('.dropdown-menu').first().stop(true, true).slideDown();
	});

	// Add slideUp animation to Bootstrap dropdown when collapsing.
	$('#footer-menu .dropup').on('hide.bs.dropdown', function() {
		$(this).find('.dropdown-menu').first().stop(true, true).slideUp();
	});
})