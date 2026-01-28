package com.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.member.domain.Member;
import com.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/insertForm")
	public String memberInsertForm() {
		return "member/insertForm";
	}
	
	@PostMapping("/insert")
	public String insertMember(Member member, Model model) {
		log.info("insert member =" +member.toString());
		try {
			int count = memberService.insertMember(member);
			if(count > 0) {
				Member joinedMember = memberService.selectByUserId(member.getUserId());
				
				model.addAttribute("message", "%s님 회원정보가 등록되었습니다.".formatted(joinedMember.getName()));
				model.addAttribute("member", joinedMember);
				return "member/success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member/failed";
	}
	
	@GetMapping("/memberList")
	public String memberList(Model model) {
		log.info("memberList");
		try {
			List<Member> memberList = memberService.memberList();
			model.addAttribute("memberList", memberList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "member/memberList";
	}
	
	@GetMapping("/detail")
	public String memberDetail(@RequestParam("no") int no, Model model) {
		log.info("조회 요청 번호(no): " + no);
		try {
			Member member = memberService.selectByNo(no);
			if(model == null) {
				model.addAttribute("message", no + "번 회원의 상세정보가 없습니다.");
				return "member/failed";
			}
			model.addAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member/detail";
	}
	
	@GetMapping("/delete")
	public String memberDelete(Member member, Model model) {
		log.info("memberDelete member = " + member.toString());
		try {
			int count = memberService.deleteMember(member);
			if (count > 0) {
				return "redirect:/member/memberList"; // 성공시 바로 목록으로 이동
			}
			//model.addAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "%s 님의 회원정보 삭제실패".formatted(member.getName()));
		return "member/failed";
	}
	
	@GetMapping("/updateForm")
	public String memberUpdateForm(Model model, @RequestParam("no") int no) {
		try {
			Member member = memberService.selectByNo(no);
			if (member == null) {
				model.addAttribute("message", "정보가 없습니다.");
				return "member/failed";
			}
			model.addAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member/updateForm";
	}
	
	@PostMapping("/update") 
	public String memberUpdate(Member m, Model model) { 
		log.info("memberUpdate member =" + m.toString());
		try {
			int count = memberService.updateMember(m);
			if (count > 0) {
				Member updatedMember = memberService.selectByNo(m.getNo());
				
				model.addAttribute("message", "%s님의 비밀번호 수정성공".formatted(m.getName()));
				model.addAttribute("description", "회원님의 정보가 안전하게 변경되었습니다.");
				model.addAttribute("member", updatedMember);
				return "member/success";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "수정에 실패하였습니다.");
		return "redirect:/member/myinfo?no=" + m.getNo();
	}
	
	@GetMapping("/myinfo")
	public String myInfo(@RequestParam("no") int no, Model model) {
	    Member member;
		try {
			member = memberService.selectByNo(no);
			model.addAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return "member/myinfo";
	}
	
	@GetMapping("/search")
	public String memberSearch(Member member, Model model) {
		log.info("memberSearch member="+member.toString());
		try {
			List<Member> memberList = memberService.memberSearch(member);
			model.addAttribute("memberList", memberList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "member/memberList";
	}
	
	
	
	
	
	
}
