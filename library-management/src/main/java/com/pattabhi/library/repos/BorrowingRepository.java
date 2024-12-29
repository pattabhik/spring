package com.pattabhi.library.repos;

import com.pattabhi.library.entity.BorrowingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BorrowingRepository extends JpaRepository<BorrowingEntity, Long> {

	List<BorrowingEntity> findByReturnDate(Date date);

}
