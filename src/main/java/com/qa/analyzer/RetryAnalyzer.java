
/**
 * Class to implement to be able to have a chance to retry a failed test.
 *
 * @author tocman@gmail.com (Jeremie Lenfant-Engelmann)
 *
 */
package com.qa.analyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	int counter = 0;
	int retryLimit = 2;

	/**
	 * Returns true if the test method has to be retried, false otherwise.
	 *
	 * @param result
	 *            The result of the test method that just ran.
	 * @return true if the test method has to be retried, false otherwise.
	 */
	public boolean retry(ITestResult result) {
		if (counter < retryLimit) {
			counter++;
			return true;
		}
		return false;
	}

}
