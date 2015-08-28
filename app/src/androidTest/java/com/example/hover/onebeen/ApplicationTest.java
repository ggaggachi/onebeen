package com.example.hover.onebeen;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
	public ApplicationTest() {
		super(Application.class);
	}

	public void testName() throws Exception {
		System.out.println("test");
		int puzzleSize = 3;
		int beenSize = 1;

		int v = (int) ((beenSize * 100) / puzzleSize);

		assertEquals(v, 1);
	}
}