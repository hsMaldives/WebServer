package kr.ac.hansung.maldives.web.utility;

import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.Facebook;

public class FacebookUtil {
	
	private static final String[] fields = { "id", "about", "age_range", "birthday", "context", "cover", "currency", "devices", "education", "email", "favorite_athletes", "favorite_teams", "first_name", "gender", "hometown", "inspirational_people", "installed", "install_type", "is_verified", "languages", "last_name", "link", "locale", "location", "meeting_for", "middle_name", "name", "name_format", "political", "quotes", "payment_pricepoints", "relationship_status", "religion", "security_settings", "significant_other", "sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", "video_upload_limits", "viewer_can_send_gift", "website", "work" };
	
	/**
	 * Facebook API v2.8 버전 변동으로 인해 일부 사용 불가능된 필드를 제외하고 가져오도록 하기 위해 만든 메서드
	 * @param facebook
	 * @return
	 */
	public static UserProfile fetchUserProfile(Facebook facebook){
		UserProfile socialMediaProfile = null;
		
		org.springframework.social.facebook.api.User user = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, fields);
		socialMediaProfile = new UserProfile(user.getId(), user.getName(), user.getFirstName(), user.getLastName(), user.getEmail(), null);
		
		return socialMediaProfile;
	}
}
