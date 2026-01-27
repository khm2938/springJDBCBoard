package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String memberInsertForm(Model model) {
		return "member/insertForm";
	}
	
	@PostMapping("/insert")
	public String insertMember(Member member, Model model) {
		log.info("insert member =" +member.toString());
		try {
			int count = memberService.insertMember(member);
			if(count > 0) {
				return "member/success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member/failed";
	}
	
	
}
