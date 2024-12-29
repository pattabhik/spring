package com.pattabhi.library.controller;

import com.pattabhi.library.repos.BookRepository;
import com.pattabhi.library.repos.BorrowingRepository;
import com.pattabhi.library.repos.MemberRepository;
import com.pattabhi.library.services.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BorrowingController {
	@Autowired
	BookRepository bookRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	BorrowingRepository borrowingRepository;

	@Autowired
	BorrowingService borrowingService;

	@GetMapping("/borrow")
	public String showBorrowForm(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		model.addAttribute("members", memberRepository.findAll());
		return "borrow_book";
	}

	@PostMapping("/borrow")
	public String borrowBook(@RequestParam Long memberId, @RequestParam Long bookId, Model model) {
		borrowingService.borrowBook(bookId, memberId);
		model.addAttribute("message", "BookEntity Borrowed Successfully!");
		return "borrow_success";
	}
	
	@GetMapping("/return")
	public String showReturnForm(Model model) {
		model.addAttribute("borrowers",borrowingRepository.findByReturnDate(null));
		return "return_book";
	}

	@PostMapping("/return")
	public String returnBook(@RequestParam Long borrowingId, Model model) {
		borrowingService.returnBook(borrowingId);
		model.addAttribute("message", "BookEntity Returned Successfully!");
		return "return_success";
	}
}
