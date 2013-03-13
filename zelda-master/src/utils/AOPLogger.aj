
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Stack;
import org.aspectj.lang.*;
import utils2.MethodNode;

public aspect AOPLogger {

		 String fileName = "Spectrum.data";
		 private static ObjectInputStream objIn;
		 
		 MethodNode spectrum = new MethodNode("APP","",new ArrayList<MethodNode>()) ;
		 MethodNode currentMethod ; 
		static Boolean finish = false;
		 Stack<MethodNode> stacktrace = new Stack<MethodNode>();

		AOPLogger(){
			stacktrace.add(spectrum) ;
			
		}
		pointcut traceMethods()
		: if(!finish)
			&&(execution(* *.*(..))
			||execution(*.*.new(..)))
			&& !within(AOPLogger)
		    && !within(utils2.*)
		    && !within(testCases.*)
		    && !within(zelda.engine.Sound)
		    && !within(zelda.engine.SoundFx)
		    && !within(zelda.engine.Music)
		    && !execution(* *.draw(..));
		before() : traceMethods(){
			
			//System.out.println("traceando");
			Signature sig = thisJoinPointStaticPart.getSignature();
			MethodNode currentMethod = new MethodNode(sig.getName(), sig.getDeclaringType().getName(), new ArrayList<MethodNode>());
			stacktrace.peek().getChildren().add(currentMethod) ;
			stacktrace.add(currentMethod);
	
		}
		after() : traceMethods(){
			
			if (stacktrace.isEmpty()) return ;
			stacktrace.pop();
		}
		
		pointcut mainExecution()
		: (execution (* *.main(..)));
	
		after()  : mainExecution() {
			finish = true;
			System.out.println("Hello");
			ArrayList<MethodNode> inputs= new ArrayList<MethodNode>();
			
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
					ArrayList<MethodNode> obj2 = (ArrayList<MethodNode>)obj;
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
				System.out.println("empezando a escribir");
				FileOutputStream fo = new FileOutputStream(fileName);
				oos = new ObjectOutputStream(fo);
				oos.writeObject(inputs);
				
				//oos.flush();
				
				System.out.println("termino");
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
