package logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import utils2.MethodNode;



public class Activator{
	
	private static ObjectInputStream objIn;

	public static void main(String[] args) throws ClassNotFoundException {
		
			ArrayList<MethodNode> m12 ;
			
			try {
				String fileName = "jiji";
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
			}
				
	}
}