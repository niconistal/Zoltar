package testCases;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import junit.framework.Test;

import org.junit.runner.Computer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Runner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = "Failures.data";
		// TODO Auto-generated method stub
		Computer computer = new Computer();
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(computer,  AllTests.class );
		
		int fails = result.getFailureCount();
		System.out.println(fails);
		ArrayList<String> failNames = new ArrayList<String>();
		if (fails > 0 ){
			for (Failure f : result.getFailures()){
				failNames.add(f.getTestHeader());
			}
		}
		//Escribir el resultado del test en un archivo
		
		FileOutputStream fo;
		try {
			fo = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fo);
			oos.writeObject(failNames);
			
			oos.flush();
			
			//System.out.println("termino");
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
