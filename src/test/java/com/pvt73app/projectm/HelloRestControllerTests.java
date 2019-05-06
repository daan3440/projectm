package com.pvt73app.projectm;

import static org.junit.Assert.*;

import org.junit.Test;

import pvt73app.projectm.HelloRestController;

public class HelloRestControllerTests {
	HelloRestController hrc = new HelloRestController();
	
	@Test
	public void hejBombTestSimple() {
		assertEquals("Hej Bomben 2", hrc.hejBomb());
	}
	
	@Test
	public void greetingTest() {
		System.out.println(hrc.greeting("Moon"));
		assertEquals("Hello, Moon.", hrc.greeting("Moon"));
	}
	
	@Test
	public void jsonTestTest() {
		assertEquals("test", hrc.hejBomb("test").getName());
	}

}
