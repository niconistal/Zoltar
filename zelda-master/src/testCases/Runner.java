package testCases;

import junit.framework.Test;

import org.junit.runner.Computer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class Runner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Computer computer = new Computer();
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(computer,  AllTests.class );
		int fails = result.getFailureCount();
		System.out.println(fails);
		if (fails > 0 ){
			System.out.println(result.getFailures().get(0).getTestHeader());
		}
	}

}
