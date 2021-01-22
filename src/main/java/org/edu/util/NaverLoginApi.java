package org.edu.util;

import com.github.scribejava.core.builder.api.BaseApi;
import com.github.scribejava.core.builder.api.DefaultApi20;

public class NaverLoginApi extends DefaultApi20{
	@Override
	public String getAccessTokenEndpoint() {
		// OATH 2.0 인증체크 네이버 API주소가 endpoint(아래)
		//로그인해도 되는 사람인지만 체크(관리자, 일반사용자인지는 체크 안함=우리같은 개발자가 만들어야 하는 것)
		return "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		// 네이버 권한 체크하는 Rest-API주소(아래)
		return "https://nid.naver.com/oauth2.0/authorize";
	}
	
	//싱클톤으로 인스턴스 객체 생성하는 바식: 클래스 실행을 한 번만 하기 위해서.
	private static class InstanceHolder {
		private static final NaverLoginApi INSTANCE = new NaverLoginApi();
	}
	
	public static NaverLoginApi instance() {
		return InstanceHolder.INSTANCE;
	
	}

}
