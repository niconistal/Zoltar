package testCases;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.runner.Result;

import junit.framework.TestCase;
import junit.framework.TestResult;



public class TestComposite {
	private LinkedList<Class<? extends TestCase>> classList ;

	public TestComposite() {
	   classList = new LinkedList<Class<? extends TestCase>>();
	}
	
	public void addTest( Class<? extends TestCase> testClass) {
		classList.add(testClass) ;
	}
	public void executeAllTest(){
		while ( classList.size() > 0){
			try {
				
				
				TestResult r = executeTest();
				if (r.errorCount() > 0){
					System.out.println("error");
				}else{
					System.out.println("nor");
				}
				
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private TestResult executeTest() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Class<? extends TestCase> test = classList.pollFirst();
			return test.getConstructor().newInstance().run() ;
					
		
	}
	

}
