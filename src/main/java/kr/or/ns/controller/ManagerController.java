package kr.or.ns.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ns.export.WriteListToExcelFile;
import kr.or.ns.service.ManagerServiceImpl;
import kr.or.ns.vo.Users;

@Controller
@RequestMapping("/manager/")
public class ManagerController {

	@Autowired
	private ManagerServiceImpl service;

	/*
	 * @Autowired private ManagerService service;
	 */

	@RequestMapping("index.do")
	public String indexPage() {
		System.out.println("어드민대문이동");

		return "manager/index";
	}

//	회원정보 아이디 클릭시 상세보기
	@RequestMapping("board/member_Detail.do")
	public String memberDetailPage(Model model, String user_id) {
		System.out.println("우철 : " + user_id);

		Users member = service.getUsers(user_id);
		List<HashMap<String, String>> skill = service.getSkill(user_id);
		System.out.println("일스킬이 찍히나요?");
		model.addAttribute("member", member);
		System.out.println("우철입니다 " + member);
		model.addAttribute("skill", skill);
		System.out.println("이스킬이 찍히나용? : " + skill);
		return "manager/board/member_Detail";

	}
//	회원정보 상세에서 삭제버튼클릭시 삭제하기
	@RequestMapping("board/memberDel.do")
	public String memberDel(String user_id) {
		System.out.println("삭제 컨트롤러 찍히나요?");
		return service.memberDel(user_id);
	}
	
	

	// 회원관리 목록
	@RequestMapping("board/member_Management.do")
	public String memberManagementPage(Model model) {
		System.out.println("어드민 회원관리 테이블페이지이동");

		// DAO받아오기 + 매퍼를 통한 인터페이스 연결

		List<Users> memberList = null;

		memberList = service.getMemberList(); // 회원목록
		model.addAttribute("memberList", memberList); // view까지 전달
		System.out.println("멤버리스트" + memberList);

		return "manager/board/member_Management";
	}

	
	// 회원관리 목록 엑셀뽑기-----------------------------------------------------------
	public String excelMemberView(Model model) throws Exception {
		System.out.println("회원 목록을 excel로 뽑아요");

		List<Users> memberList = null;
		// memberList = service.getMemberList(); //회원목록가져와서 memberList에 넣음

		model.addAttribute("memberList", memberList); // view까지 전달
		/* WriteListToExcelFile.writeMemberListToFile("cordova.xls", memberList); */
		// cordova는 대체 무엇인고

		
		WriteListToExcelFile.writeMemberListToFile("memberList.xls", memberList);
		return "manager/board/member_Management";
	}

	
	//----------------------------------------------------------------------------
	@RequestMapping("board/report_Management.do")
	public String reportManagementPage() {
		System.out.println("어드민 회원관리 테이블페이지이동");

		return "manager/board/report_Management";
	}
}
