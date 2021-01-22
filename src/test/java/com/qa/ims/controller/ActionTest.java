package com.qa.ims.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ActionTest {
	@Test
	public void customerTest() {
		Action action = Action.CREATE;
		assertTrue(action.getDescription().toLowerCase().contains("save"));
	}

}
