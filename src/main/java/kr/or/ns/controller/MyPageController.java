package kr.or.ns.controller;

import java.security.Principal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import kr.or.ns.service.MessageService;
import kr.or.ns.service.MyPageService;
import kr.or.ns.vo.Users;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/mypage/")
public class MyPageController {

	@Autowired
	MyPageService service;

	@Autowired
	MessageService mservice;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// 마이페이지 뿌려줄 데이터 가져오기
	@RequestMapping(value = "mypage.do")
	public String myPage(Model model, Principal principal) {
		String users = principal.getName();

		List<Map<String, Object>> list = null;
		list = service.myPagelist(users); // 북마크 리스트
		Users user = service.userInfo(users); // 사용자 정보
		int bk = service.bookmarkCount(users); // 북마크갯수
		int cm = service.commentCount(users); // 댓글 갯수
		int sb = service.s_boardCount(users); // 스터디게시판 게시글 갯수
		int js = service.join_study(users); // 참여중 스터디 개수
		int rs = service.recruit_study(users); // 모집중 스터디 개수

		List<HashMap<String, Object>> studylist = service.myPageStudyList(users); // 스터디 목록

		model.addAttribute("list", list);
		model.addAttribute("user", user);
		model.addAttribute("bk", bk);
		model.addAttribute("cm", cm);
		model.addAttribute("sb", sb);
		model.addAttribute("js", js);
		model.addAttribute("rs", rs);
		model.addAttribute("studylist", studylist);

		return "user/mypage/mypage";
	}

	// 유저 상세정보 뿌리기
	@RequestMapping(value = "MyPageUserDetail.do")
	public String mypageUserEditView(Model model, Principal principal) {
		Users user = service.getUsers(principal.getName());
		List<HashMap<String, String>> list = service.getSkill(principal.getName());
		model.addAttribute("member", user);
		model.addAttribute("skill", list);
		return "user/mypage/mypage_User_Detail.html";
	}

	// 유저정보 수정페이지로 이동
	@RequestMapping(value = "MyPageUserEdit.do")
	public String mypageUserEdit(Model model, Principal principal) {
		Users user = service.getUsers(principal.getName());
		List<HashMap<String, String>> list = service.getSkill(principal.getName());
		model.addAttribute("member", user);
		model.addAttribute("skill", list);
		return "user/mypage/mypage_User_Edit.html";
	}

	// 유저정보 수정하기
	@RequestMapping(value = "MyPageUserEdit.do", method = RequestMethod.POST)
	public String mypageUserEdit(@RequestParam(value = "file", required = false) MultipartFile ipload, Users user,
			HttpServletRequest request, Model model, Principal principal) {
		
		if(user.getSnstype().equals("normal")) {
			user.setUser_pwd(this.bCryptPasswordEncoder.encode(user.getUser_pwd()));
		}
		service.MyPageUserEdit(user, request);

		return "redirect:/mypage/mypage.do"; // /index.htm

	}

	// 회원탈퇴
	@RequestMapping(value = "userDelete.do", method = RequestMethod.GET)
	public String userDelte(Principal principal, HttpServletRequest request, HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(request, response, auth);
		String users = principal.getName();
		service.userDelete(users);
		return "redirect:/index.do";
	}

	//마이페이지 메인화면
	@RequestMapping("mypage_Myboard.do")
	public String myBoardPage(Model model, Principal principal) {
		String users = principal.getName();

		List<Map<String, Object>> list = null;
		list = service.myPagelist(users); // 북마크 리스트
		Users user = service.userInfo(users); // 사용자 정보
		int bk = service.bookmarkCount(users); // 북마크갯수
		int cm = service.commentCount(users); // 댓글 갯수
		int sb = service.s_boardCount(users); // 스터디게시판 게시글 갯수
		int js = service.join_study(users); // 참여중 스터디 개수
		int rs = service.recruit_study(users); // 모집중 스터디 개수

		List<HashMap<String, Object>> studylist = service.myPageStudyList(users); // 스터디 목록
		model.addAttribute("list", list);
		model.addAttribute("user", user);
		model.addAttribute("bk", bk);
		model.addAttribute("cm", cm);
		model.addAttribute("sb", sb);
		model.addAttribute("js", js);
		model.addAttribute("rs", rs);
		model.addAttribute("studylist", studylist);

		return "user/mypage/mypage_Myboard";
	}

	// 스터디 참여현황 페이지 정보 뿌려주기
	@RequestMapping(value = "SupportStatus.do")
	public String SupportStatus(String s_seq, Model model, Principal principal) {
		List<HashMap<String, Object>> status = service.studyStatus(s_seq);
		String user_id = principal.getName();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("role_name", "방장");
		map.put("s_seq", s_seq);
		HashMap<String, Object> writer = service.getRole(map);
		String a = (String) writer.get("user_id");
		String s_board_staus = service.getStatus(s_seq);
		model.addAttribute("id", user_id);
		model.addAttribute("status", status);
		model.addAttribute("writer", writer);
		model.addAttribute("s_seq", s_seq);
		model.addAttribute("s_board_staus", s_board_staus);
		
		
		return "user/mypage/mypage_Support_Status.html";

	}

}
