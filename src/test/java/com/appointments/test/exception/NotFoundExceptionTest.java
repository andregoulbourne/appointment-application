package com.appointments.test.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;

import com.appointments.exceptions.NotFoundException;

class NotFoundExceptionTest {
		
/*	@Test(expected=NotFoundException.class)
	void testCanThrowNotFoundException(){
		throw new NotFoundException();
	}	*/
	
	@Test
	void testCanThrowNotFoundException() {
		assertThrows(NotFoundException.class, () -> {
			throw new NotFoundException();
		});
	}

}
