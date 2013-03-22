package spectrumReader;

//import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Zoltar.ZoltarEngine;

//import org.junit.Test;

import utils2.DataManagement;
import utils2.ExecutionNode;
import utils2.MethodNode;

public class ActivatorTest {
	//@Test
	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main (String[] args) {
		ObjectInputStream objIn;
		ArrayList<ExecutionNode> trace  = new ArrayList<ExecutionNode>();
		ArrayList<String> errorList = new ArrayList<String>();
		String fileName = "Spectrum";
		DataManagement inputFile = new DataManagement(fileName);
		trace = (ArrayList<ExecutionNode>)inputFile.readFile();
		fileName = "Failures";
		inputFile = new DataManagement(fileName);
		errorList = (ArrayList<String>)inputFile.readFile();
		/*
		for (ExecutionNode e : trace){
			if (e.getName().equals("testCases.linkTest")){
				ExecutionNode.print(e);
			}			
		}
		for (String faulty : errorList){
			System.out.println("El error se llama: " + faulty) ;
		}
		*/
		ZoltarEngine theEngine = new ZoltarEngine();
		System.out.println(theEngine.getResult(trace, errorList));
		
	}

}
