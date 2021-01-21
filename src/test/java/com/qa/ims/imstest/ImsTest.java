package com.qa.ims.imstest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.Ims;
import com.qa.ims.controller.Action;
import com.qa.ims.controller.CustomerController;

@RunWith(MockitoJUnitRunner.class)
public class ImsTest {

	@Mock
	private CustomerController custcontroller;

	@Spy
	private Ims ims;

	@Test
	public void imsSystemTest() {
		String login = "root";
		String option = "STOP";
		// Mockito.doReturn(login, login).when(ims).getInput();
//		Mockito.doReturn(Domain.STOP).when(ims).getDomain();

		// ims.imsSystem();
		// Mockito.verify(ims, Mockito.times(1)).init(login, login);

	}

	@Test
	public void doActionUpdateTest() {
		ims.doAction(custcontroller, Action.UPDATE);
		Mockito.verify(custcontroller, Mockito.times(1)).update();
	}

	@Test
	public void doActionCreateTest() {
		ims.doAction(custcontroller, Action.CREATE);
		Mockito.verify(custcontroller, Mockito.times(1)).create();
	}

	@Test
	public void doActionReadTest() {
		ims.doAction(custcontroller, Action.READ);
		Mockito.verify(custcontroller, Mockito.times(1)).readAll();
	}

	@Test
	public void doActionDeleteTest() {
		ims.doAction(custcontroller, Action.DELETE);
		Mockito.verify(custcontroller, Mockito.times(1)).delete();
	}

	@Test
	public void doActionReturnTest() {
		ims.doAction(custcontroller, Action.RETURN);
		// Mockito.verify(custcontroller, Mockito.times(1)).delete();
	}

	@Test
	public void readFileTest() {
		String fileLocation = "test.txt";
		assertEquals("", ims.readFile(fileLocation));
	}

	@Test
	public void readFileExceptionTest() {
		assertEquals("", ims.readFile("nothing"));
	}

}
