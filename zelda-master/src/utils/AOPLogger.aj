
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Stack;
import org.aspectj.lang.*;

import utils2.DataManagement;
import utils2.ExecutionNode;
import utils2.MethodNode;

public aspect AOPLogger {

		 String fileName = "Spectrum";
		 static Boolean finish = false;
		 ExecutionNode spectrum = new ExecutionNode(); ;
		 private String theCurrentNameOfTheRunningClass;

		AOPLogger(){
			
		}
		
		//Pointcut definitions go here.
		
		pointcut testIsAboutToBegin()
		: 
			execution( * *.test(..));
		
		pointcut testIsAboutToEnd()
		: if(!finish)
			&& execution( * *.test(..));
		
		pointcut mainExecution()
		: (execution (* *.main(..)));
		
		pointcut traceMethods()
		: if(!finish)
			&&(execution(* *.*(..))
			||execution(*.*.new(..)))
			&& !within(AOPLogger)
		    && !within(utils2.*)
		    && !within(testCases.*)
		    && !execution(* *.draw(..))
		    && ! execution(* *.*test());
		
		
		//Set up the trace
		
		before() : testIsAboutToBegin(){
			finish = false;
			spectrum = new ExecutionNode();
			System.out.println(thisJoinPoint.getTarget().getClass().getName());
			theCurrentNameOfTheRunningClass = thisJoinPoint.getTarget().getClass().getName();
		}
		
		//Trace of each method.
		
		before() : traceMethods(){
			
			
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
		

		//Write the result of the trace.
	
		after()  : testIsAboutToEnd() {
			
			spectrum.setName(theCurrentNameOfTheRunningClass);
			finish = true;
			ArrayList<ExecutionNode> inputs= new ArrayList<ExecutionNode>();
			
			//Read the fileName and load all saved methodNodes
			
			DataManagement fileInput = new DataManagement(fileName);
			Object readInput = fileInput.readFile();
			inputs = (ArrayList<ExecutionNode>) readInput;
			
			inputs.add(spectrum);
			ObjectOutputStream oos;
			
			//save the data into the file.
			
			DataManagement fileOutput = new DataManagement(fileName);
			fileOutput.writeFile(inputs);
			
		}
		
}

