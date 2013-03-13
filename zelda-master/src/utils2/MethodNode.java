package utils2 ;


import java.io.Serializable;
import java.util.ArrayList;



public class  MethodNode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 589128L;
	protected String methodClass ;
    protected String methodName ;
    protected ArrayList<MethodNode> children ;
    
    
    public MethodNode(String name, String className, ArrayList<MethodNode> child ) {
    	methodClass = className;
    	methodName = name;
    	children = child;
    }
	
    private void setChildren(ArrayList<MethodNode> child) {
		this.children = child ;
		
	}
	
	public ArrayList<MethodNode> getChildren() {
		return this.children ;
	}
	
	public String getMethodClass() {
		return methodClass;
	}
	
	public void setMethodClass(String methodClass) {
		this.methodClass = methodClass;
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public static void print(MethodNode root, String level) {
		System.out.println(level + root.getMethodClass() + "."+ root.getMethodName() );
		for( MethodNode child : root.getChildren()) {
			MethodNode.print(child, level +"__") ;
		}
	}
    
}