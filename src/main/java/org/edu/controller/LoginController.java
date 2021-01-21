package org.edu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.edu.service.IF_MemberService;
import org.edu.vo.MemberVO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.scribejava.core.model.OAuth2AccessToken;

@Controller
public class LoginController {
	
	@Inject
	private IF_MemberService memberService;
	
	@Inject
	private NaverLoginController naverLoginController;
	
	//로그인 후 세션 처리 매핑 - 네이버 아이디 로그인 로직일 때
	//session(인증토큰정보), state(유효성검증용 UUID정보), code(인증성공/실패 코드 예, 200OKm 401 Error)
	@RequestMapping(value="/login_callback", method= {RequestMethod.GET, RequestMethod.POST})
	public String login_callback(Model model,@RequestParam String code, @RequestParam String state, HttpSession sessison, RedirectAttributes rdat) throws IOException, ParseException {
		
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginController.getAccessToken(sessison, code, state);
		//네이버로 로그인한 사용자 정보(profile)을 읽어온다(아래)
		String apiResult = naverLoginController.getUserProfile(oauthToken);//이름, 이메일 자료
		// 위 String형 apiResult값을 json형태로 파싱합니다.(아래)
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);//apiResult문자열값 -> HashMap<키:값> Json형태로 변환
		JSONObject jsonObj = (JSONObject) obj;//여기서 JSon오브젝트가 됩니다. 파싱한 1차 데이터
		/* apiResult json 구조 */
		/** apiResult json 구조
	    {"resultcode":"00",
	    "message":"success",
	    "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
	    **/

		//TOP레벨에 있는 response 파싱/ 위 1차 데이터를 한 번 더 분리(파싱) 
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		//위 response_obj 파싱(name, email 분리) 아래
		String username = (String) response_obj.get("name");
		String useremail = (String) response_obj.get("email");
		String Status = (String) jsonObj.get("message");
		//여기까지가 네이버 인증 성공 후 개인프로필 뽑아서 변수로 생성한 처리--------
		//우리 로직(스프링 시큐리티의 ROLE_USER를 권한부여를 한느 로직 만듦)을 타게 합니다.(아래)
		if(Status.equals("success")) {
			//강제로 스프링 시큐리티 인증 처리를 하게함 enabled = true
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			//강제로 스프링 시큐리티 인증 처리를 하게 함(아래)
			Authentication authentication = new UsernamePasswordAuthenticationToken(useremail, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);//인증정보 저장처리
			//로그인 세션 변수 생성(아래)
			sessison.setAttribute("session_enabled", true);
			sessison.setAttribute("session_userid", useremail);
			sessison.setAttribute("session_username", username);
			rdat.addFlashAttribute("msg", "네이버 아이디로 로그인");
		}else {
			rdat.addFlashAttribute("param.msg", "fail");//login.jsp전용 메세지
			return "redirect:/login";
		}
		return null;
	}
	
	//로그인 후 세션 처리 매핑 - 스프링 시큐리티 로그인 로직일 때
	@RequestMapping(value="/login_success",method=RequestMethod.GET)
	public String login_success(HttpServletRequest request, RedirectAttributes rdat) throws Exception {
		//이미 인증을 마치고 진입한 메서드. 그래서, 아래 authentication 변수에는 인증정봬가 들어있음
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userid = "";//로그인아이디를 생성해야 하기 때문.
		String levels = "";//권한이 들어갈 변수 선언
		Boolean enabled = false;
		Object principal = authentication.getPrincipal();
		if(principal instanceof UserDetails) {
			//인증이 처리되는 로직
			enabled = ((UserDetails)principal).isEnabled();
		}
		//세션 정보(로그인아이디, 레벨, 회원이름 ...) 저장 시작(아래)
		HttpSession session = request.getSession();//진입 전 단계 전 발생한 세션(로그인 절차로 생성된 세션)가져오는 절차
		if(enabled) { //인증처리가 true라면 아래에서 세션 등록 시작
			//자바8이상에서만 지원되는 람다식 사용해서  DB쿼리에서 GET값 getAuthority()비교구문 처리 levels 변수에 권한값 지정
			Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities();
			if(authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ANONYMOUS")).findAny().isPresent())
			{levels = "ROLE_ANONYMOUS";}
			if(authorities.stream().filter(o -> o.getAuthority().equals("ROLE_USER,")).findAny().isPresent())
			{levels = "ROLE_USER,";}
			if(authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent())
			{levels = "ROLE_ADMIN";}
			//사용자 아이디 값 지정
			userid = ((UserDetails)principal).getUsername();
			//로그인 세션 저장시작: 사용처는 jsp뷰단, java클래스 모두 사용 가능
			session.setAttribute("session_enabled", enabled);
			session.setAttribute("session_userid", userid);
			session.setAttribute("session_levels", levels);
			//상단까지의 세션변수는 스프링 시큐리티에서 기본 제공하는 변수
			//하단부터는 비지니스 로직에 따라서 우리 개발쪽에서 발생시키는 세션변수 시작
			MemberVO memberVO = memberService.readMember(userid);
			session.setAttribute("session_username", memberVO.getUser_name());
		}
		rdat.addFlashAttribute("msg", "로그인");
		return "redirect:/";
	}
	
	//사용자 홈페이지 로그인 접근 매핑
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() throws Exception{
		//BCrypt암호화 match 메서드으로 확인
				String rawPassword = "1234";
				BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder(10);
				String bcryptPassword = bcryptPasswordEncoder.encode("1234");//예, user02 -> 암호화 처리됨
				//System.out.println(bcryptPassword);
				System.out.println("2가지 스트링을 매칭 참,거짓: " + bcryptPasswordEncoder.matches(rawPassword, bcryptPassword));
		return "home/login";
	}
}