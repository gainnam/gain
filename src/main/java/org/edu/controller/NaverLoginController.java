package org.edu.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.edu.util.NaverLoginApi;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

@Controller
public class NaverLoginController {
 /*
  * 네이버 API서버로 보내는 인증 요청문을 구성하는 파라미터(아래)
  * client_id:  네이버 App등록 후 발급받은 클라이언트 아이디
  * response_type: 네이버 APP보내 준 응답에 대한 데이터 타입
  * redirect_url: 네이버 로그인 인증결과를 전달 받는 콜백 URL
  * state: 네이버 App이 생성한 토큰의 상태(네트웍에서 전송되는 자료단위-인증정보)의 상태
  */
	private final static String CLIENT_ID = "mLPPJrhSRd4Hi8DbS4ML";
	private final static String CLIENT_SECRET = "aIOv73PAav";
	private final static String RDIRECT_URI = "http://127.0.0.1:8080/login_callback";
	private final static String SESSION_STATE = "ouath_state";
	/*프로필 조회 API URL - 사용자 이름*/
	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
	
	/*네이버 아이디로 로그인 인증 url 생성 Method*/
	public String GetAuthorizationUrl(HttpSession session) {
		/*세션 유효성 검증을 위해 난수 생성*/
		String state = generateRandomString();
		//생성한 난수 값을 session 변수에 저장
		setSession(session, state);
		
		/*
		 * Scribe외부모듈에서 제공하는 인증 uRL생성 기능을 이용하여 네아로 인증 uRL생성(아래)
		 */
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.callback(RDIRECT_URI)
				.state(state)
				.build(NaverLoginApi.instance());
		
		return oauthService.getAuthorizationUrl();
	}
	//네아로 Callback 처리 및 Access Token 구하기 메서드
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException {
		String sessionState = getSession(session);
		
		if(StringUtils.pathEquals(sessionState, state)) {
			
			OAuth20Service oauthService = new ServiceBuilder()
					.apiKey(CLIENT_ID)
					.apiSecret(CLIENT_SECRET)
					.callback(RDIRECT_URI)
					.state(state)
					.build(NaverLoginApi.instance());
			//Scribe 외
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			return accessToken;//인증받은 토큰정보를 반환함.
		}
		return null;//인증받지 못하면, 널값을 반환.
	}
	private String getSession(HttpSession session) {
		//http에서 session 값 가져오기
		return (String) session.getAttribute(SESSION_STATE);
	}
	
	private void setSession(HttpSession session, String state) {
		// http session 클래스에 데이터 저장
		session.setAttribute(SESSION_STATE, state);
		
	}

	private String generateRandomString() {
		//세션 유효성 검증을 위한 난수 생성기
		return UUID.randomUUID().toString();
	}

	
	//Acceses Token을 이용하여 네이버 사용자 프로필 API 호출 = 여기서 네이버 이름, 이메일을 반환
	public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException {
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.callback(RDIRECT_URI)
				.build(NaverLoginApi.instance());
		OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
		oauthService.signRequest(oauthToken, request);
		Response response = request.send();//Response클래스는 Scribe외부모듈에서 추가ㅣ
		return response.getBody();
	}
	
}
