package kr.or.ns.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.weaver.tools.cache.AsynchronousFileCacheBacking.ClearCommand;
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
import kr.or.ns.vo.Message;
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

	@RequestMapping("mypage.do")
	public String myPagePage() {
		return "user/mypage/mypage";
	}

	// 유저 상세정보 뿌리기
	@RequestMapping(value = "MyPageUserDetail.do")
	public String mypageUserEditView(Model model, Principal principal) {
		System.out.println("상세정보");
		Users user = service.getUsers(principal.getName());
		List<HashMap<String, String>> list = service.getSkill(principal.getName());
		model.addAttribute("member", user);
		model.addAttribute("skill", list);
		return "user/mypage/mypage_User_Detail.html";
	}

	// 유저정보 수정페이지로 이동
	@RequestMapping(value = "MyPageUserEdit.do")
	public String mypageUserEdit(Model model, Principal principal) {
		System.out.println("여길 타는건가");
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
		user.setUser_pwd(this.bCryptPasswordEncoder.encode(user.getUser_pwd()));

		System.out.println("컨트롤러1");
		System.out.println(user);
		System.out.println("userrrrr:" + user);

		service.MyPageUserEdit(user, request);

		System.out.println("컨트롤러2");
		return "user/mypage/mypage.html";

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

	@RequestMapping("mypage_Myboard.do")
	public String myBoardPage() {
		System.out.println("내가 쓴 게시판으로 이동이동(연규가씀)");
		return "user/mypage/mypage_Myboard";
	}

	
}
