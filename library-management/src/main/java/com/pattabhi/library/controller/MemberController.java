package com.pattabhi.library.controller;

import com.pattabhi.library.entity.MemberEntity;
import com.pattabhi.library.repos.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

	@Autowired
	private MemberRepository memberRepository;

	@GetMapping("/members")
	public String listMembers(Model model) {
		model.addAttribute("members", memberRepository.findAll());
		return "members";
	}
	
	@GetMapping("/member/new")
	public String showCreateForm(Model model) {
		MemberEntity member = new MemberEntity();
		model.addAttribute("member",member);
		return "create_member";
	}
	
	@PostMapping("/members")
	public String saveMember(MemberEntity member) {
		memberRepository.save(member);
		return "redirect:/members";
	}
	
	@GetMapping("/member/{id}")
	public String showUpdateForm(@PathVariable("id") Long id,Model model) {
		MemberEntity member = memberRepository.findById(id).get();
		model.addAttribute("member",member);
		return "update_member";
	}
	
	@PostMapping("/member/update")
	public String updateMember(MemberEntity member) {
		memberRepository.save(member);
		return "redirect:/members";
	}
}