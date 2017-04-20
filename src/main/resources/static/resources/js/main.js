function callSettingActivity(){
	if(window.whereYou){
		window.whereYou.callSettingsActivity();
	} else {
		alert("App에서 실행해주세요!");
	}
}