package spectrumReader;

//import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

//import org.junit.Test;

import utils2.MethodNode;

public class ActivatorTest {
	//@Test
	public static void main (String[] args) {
	 ObjectInputStream objIn;
		ArrayList<MethodNode> m12 ;
	//	assertTrue("Hello",4<2);
		try {
			String fileName = "Spectrum.data";
			// Read from disk using FileInputStream
			FileInputStream f_in = new 
				FileInputStream(fileName);

			objIn = new ObjectInputStream (f_in);

			// Read an object
			Object obj = objIn.readObject();
			@SuppressWarnings("unchecked")
			ArrayList<MethodNode> obj2 = (ArrayList<MethodNode>)obj;
			m12 = obj2;
			System.out.println("Ahora vienen los archivos");
			
			for (MethodNode m : m12){	
				System.out.println("Este es uno!!!");
				MethodNode.print(m, "") ;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
