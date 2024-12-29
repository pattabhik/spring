package com.pattabhi;

import com.pattabhi.addition.AddNumbers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class NumberOperationsApplicationTests {
	@Autowired
	AddNumbers addNumbers;
	@Test
	void addNumbers() {
		assertEquals(15, addNumbers.addTwoNumber());
	}

}
