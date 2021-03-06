package utils2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class ThreadNode implements Serializable {

	
	private static final long serialVersionUID = 123542L;
	MethodNode spectrum ;
	MethodNode currentMethod ; 
	Stack<MethodNode> stacktrace;
	
	public ThreadNode(String treadName){
		spectrum = new MethodNode(treadName,"",new ArrayList<MethodNode>());
		stacktrace = new Stack<MethodNode>();
		stacktrace.add(spectrum) ;
	}
	public MethodNode getSpectrum(){
		return spectrum;
	}
	public MethodNode getCurrentMethod(){
		return currentMethod;
	}
	public void setCurrentMethod(MethodNode currentNode){
		currentMethod = currentNode;
	}
	public MethodNode peekFromStack(){
		return stacktrace.peek();
	}
	public void addToStack(MethodNode node){
		stacktrace.add(node);
	}
	public MethodNode popFromStack(){
		return stacktrace.pop();
	}
	public Boolean stackIsEmpty(){
		return stacktrace.isEmpty();
	}
	public static void print ( ThreadNode threadPrint){
		MethodNode.print(threadPrint.getSpectrum(),"");
	}
	
}
