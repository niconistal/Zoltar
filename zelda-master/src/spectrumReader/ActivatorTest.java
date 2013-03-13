package spectrumReader;

//import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

//import org.junit.Test;

import utils2.ExecutionNode;
import utils2.MethodNode;

public class ActivatorTest {
	//@Test
	public static void main (String[] args) {
	 ObjectInputStream objIn;
		ArrayList<ExecutionNode> m12 ;
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
			ArrayList<ExecutionNode> obj2 = (ArrayList<ExecutionNode>)obj;
			m12 = obj2;
			System.out.println("Ahora vienen los archivos");
			
			for (ExecutionNode m : m12){	
				System.out.println("Este es uno!!!");
				//ExecutionNode.print(m) ;
				System.out.println("Esta ejecucion tiene : "+m.getAllThreadNames().size()+" threads");
				System.out.println("Los threads se llaman: ");
				for (String threadName : m.getAllThreadNames()){
					System.out.println(threadName);
				}
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
