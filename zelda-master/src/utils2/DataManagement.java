package utils2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataManagement {
	
	protected String fileName;
	
	public DataManagement(String fileName){
		this.fileName = fileName+".data";
	}
	
	public Object readFile(){
		Object obj = new Object();
		try {
			ObjectInputStream objIn;
			FileInputStream f_in = new FileInputStream(fileName);
			objIn = new ObjectInputStream (f_in);
			obj = objIn.readObject();
			objIn.close();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	public void writeFile(Object writeData){
		try{
			ObjectOutputStream oos;
			FileOutputStream fo = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fo);
			oos.writeObject(writeData);
			oos.flush();
			oos.close();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
