package utils2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ExecutionNode implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1756723L;
	protected HashMap<String, ThreadNode> threads;
	

	public ExecutionNode(){
		threads = new HashMap<String, ThreadNode>();
		
	}
	public Boolean isThread(String threadName){
		return threads.containsKey(threadName);
	}
	public ArrayList<String> getAllThreadNames(){
		
		return new ArrayList<String>(threads.keySet());
	}
	
	public void newThread(String threadName){
		threads.put(threadName, new ThreadNode(threadName));
		
	}
	public ThreadNode getThread(String threadName){
		return threads.get(threadName);
	}
	public static void print (ExecutionNode printNode){
		for (String threadName : printNode.getAllThreadNames()){
			System.out.println(threadName);
			ThreadNode.print(printNode.getThread(threadName));
		}
	}
	
}
