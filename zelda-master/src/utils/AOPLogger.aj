
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Stack;
import org.aspectj.lang.*;

import utils2.ExecutionNode;
import utils2.MethodNode;

public aspect AOPLogger {

		 String fileName = "Spectrum.data";
		 private static ObjectInputStream objIn;
		 static Boolean finish = false;
		 ExecutionNode spectrum = new ExecutionNode();

		AOPLogger(){
			
			
		}
		pointcut traceMethods()
		: if(!finish)
			&&(execution(* *.*(..))
			||execution(*.*.new(..)))
			&& !within(AOPLogger)
		    && !within(utils2.*)
		    && !within(testCases.*)
		    && !execution(* *.draw(..));
		before() : traceMethods(){
			
			//System.out.println("traceando");
			Signature sig = thisJoinPointStaticPart.getSignature();
			MethodNode currentMethod = new MethodNode(sig.getName(), sig.getDeclaringType().getName(), new ArrayList<MethodNode>());
			String threadName = Thread.currentThread().getName();
			if (!spectrum.isThread(threadName)){
				spectrum.newThread(threadName);
			}
			spectrum.getThread(threadName).peekFromStack().getChildren().add(currentMethod);
			spectrum.getThread(threadName).addToStack(currentMethod);
	
		}
		after() : traceMethods(){
			
			String threadName = Thread.currentThread().getName();
			if (spectrum.getThread(threadName).stackIsEmpty()) return ;
			spectrum.getThread(threadName).popFromStack();
		}
		
		pointcut mainExecution()
		: (execution (* *.main(..)));
	
		after()  : mainExecution() {
			finish = true;
			System.out.println("Hello");
			ArrayList<ExecutionNode> inputs= new ArrayList<ExecutionNode>();
			
			//Read the fileName and load all saved methodNodes
			try {
				// Read from disk using FileInputStream
				FileInputStream f_in = new 
					FileInputStream(fileName);

				objIn = new ObjectInputStream (f_in);

				// Read an object
				Object obj;
				try {
					obj = objIn.readObject();
					@SuppressWarnings("unchecked")
					ArrayList<ExecutionNode> obj2 = (ArrayList<ExecutionNode>)obj;
					inputs = obj2;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			inputs.add(spectrum);
			//MethodNode.print(spectrum, "") ;
			ObjectOutputStream oos;
			
			//save the data into the file.
			try {
				//System.out.println("empezando a escribir");
				FileOutputStream fo = new FileOutputStream(fileName);
				oos = new ObjectOutputStream(fo);
				oos.writeObject(inputs);
				
				//oos.flush();
				
				//System.out.println("termino");
				//oos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
}

