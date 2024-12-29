package com.pattabhi.library.services;

import java.time.LocalDate;
import java.util.Date;

import com.pattabhi.library.entity.BorrowingEntity;
import com.pattabhi.library.repos.BookRepository;
import com.pattabhi.library.repos.BorrowingRepository;
import com.pattabhi.library.repos.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowingServiceImpl implements BorrowingService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	BorrowingRepository borrowingRepository;

	@Override
	public BorrowingEntity borrowBook(Long bookId, Long memberId) {
		BorrowingEntity borrowing = new BorrowingEntity();
		borrowing.setBook(bookRepository.findById(bookId).get());
		borrowing.setMember(memberRepository.findById(memberId).get());
		borrowing.setBorrowedDate(LocalDate.now());
		return borrowingRepository.save(borrowing);
	}

	@Override
	public BorrowingEntity returnBook(Long borrowingId) {
		BorrowingEntity borrowing = borrowingRepository.findById(borrowingId).get();
		borrowing.setReturnDate(LocalDate.now());
		return borrowingRepository.save(borrowing);
	}

}
