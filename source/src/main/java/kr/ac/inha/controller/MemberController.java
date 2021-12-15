package kr.ac.inha.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.inha.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/member/loginMember.do") //①번. 로그인 페이지로 이동
	public ModelAndView loginMember() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("member/loginMember");
		return mv;
	}
	@RequestMapping("/member/processLoginMember.do")//②번. 로그인을 진행
	public ModelAndView processloginMember(HttpServletRequest request, String id, String pw) {
		ModelAndView mv = new ModelAndView();
		String pw_t = memberService.loginMember(id);

		if (pw.equals(pw_t)) {
			HttpSession session = request.getSession();
			session.setAttribute("sessionId", id);
			mv.setViewName("welcome");
		} else {
			mv.setViewName("member/loginMember");
			mv.addObject("msg", pw);
		}
		return mv;
	}
	@RequestMapping("/member/logoutMember.do")//③ 로그아웃을 진행
	public ModelAndView logoutMember(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		HttpSession session = request.getSession();
		session.invalidate();
		mv.setViewName("");
		return welcome();
	}
	
	@RequestMapping("/welcome.do")
	public ModelAndView welcome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("welcome");
		return mv;
	}

	@RequestMapping("/member/addMember.do")//①번. 회원가입 페이지로 이동
	public ModelAndView addMember() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/addMember");
		return mv;
	}
	
	@RequestMapping("/member/processAddMember.do")//②번. 회원가입을 진행
	public ModelAndView regitMember( String id, String pw, String nickname, String name, String mail, String hello) throws Exception {
		HashMap<String, String> hashmap = new HashMap<>();
		hashmap.put("id", id);
		hashmap.put("pw", pw);
		hashmap.put("nickname", nickname);
		hashmap.put("name", name);
		hashmap.put("mail", mail);
		hashmap.put("hello", hello);
		//log.debug("logger!!" + hashmap.toString());
		try {
			memberService.regitMember(hashmap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return welcome();
	}
	
	@RequestMapping("/member/updateMember.do")//①번. 회원수정 페이지로 이동
	public ModelAndView infoMember(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id=session.getAttribute("sessionId").toString();		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/updateMember");
		HashMap<String, String> info= memberService.infoMember(id);
		mv.addObject("info", info);
		return mv;
	}
	@RequestMapping("/member/processUpdateMember.do")//②번. 회원수정을 진행
	public ModelAndView updateMember(HttpServletRequest request,String id, String pw, String nickname, String name, String mail, String hello) throws Exception {
		System.out.print("nnnnnnnnnn");
		HashMap<String, String> hashmap = new HashMap<>();
		HttpSession session = request.getSession();
		String id_past=session.getAttribute("sessionId").toString();
		hashmap.put("id_past", id_past);
		hashmap.put("id", id);
		hashmap.put("pw", pw);
		hashmap.put("nickname", nickname);
		hashmap.put("name", name);
		hashmap.put("mail", mail);
		hashmap.put("hello", hello);
		try {
			memberService.updateMember(hashmap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!id_past.equals(id)) {
			session.setAttribute("sessionId", id);
		}
		return welcome();
	}
	
	@RequestMapping("/member/deleteMember.do")//③ 회원탈퇴를 진행
	public ModelAndView delete(HttpServletRequest request, String id) throws Exception {
		memberService.delete(id);
		HttpSession session = request.getSession();
		session.invalidate();
		return welcome();
	}
	
	
}
