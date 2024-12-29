package com.pattabhi.library.services;

import com.pattabhi.library.entity.BorrowingEntity;

public interface BorrowingService {
	
	BorrowingEntity borrowBook(Long bookId, Long memberId);
	
	BorrowingEntity returnBook(Long borrowingId);

}
