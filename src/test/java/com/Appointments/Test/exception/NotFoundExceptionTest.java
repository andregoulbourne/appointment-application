package com.Appointments.Test.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;

import com.Appointments.exceptions.NotFoundException;

public class NotFoundExceptionTest {
		
/*	@Test(expected=NotFoundException.class)
	public void testCanThrowNotFoundException(){
		throw new NotFoundException();
	}	*/
	
	@Test
	public void testCanThrowNotFoundException() {
		assertThrows(NotFoundException.class, () -> {
			throw new NotFoundException();
		});
	}

}
