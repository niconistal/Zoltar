package Zoltar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import utils2.ExecutionNode;
import utils2.MethodNode;
import utils2.ThreadNode;

public class ZoltarEngine {
	
	
	
	
	public ZoltarEngine(){
		
	}
	
	
	
	
private double getOchiaiCoefficient( int resp, ArrayList< ArrayList<Boolean> > spectrum, ArrayList<Boolean> errors,String respName) {
	/**
	 *     N     |  Touched  |    Run
	 *   -------------------------------
	 *    n00    |    no	 |  passed
	 *    n10	 |    yes    |  passed
	 *    n01	 |    no	 |  failed
	 *    n11	 | 	  yes	 |  failed
	 * 	@author Nicolas Nistal nistal.nicolas(at)gmail.com
	 */
	
	
	Double n11 = 0.0, 
			n10 = 0.0, 
			n01 = 0.0, 
			n00 = 0.0 ; 
		
		for( int j=0 ; j < spectrum.size() ; j++ ) {
			
			if( (spectrum.get(j).size()  > resp ) && (spectrum.get(j).get(resp) ) ){
					n10 +=  errors.get(j) ? 0.0 : 1.0 ;
					n11 +=  errors.get(j) ? 1.0 : 0.0 ;
			} else {
				n00 +=  errors.get(j) ? 0.0 : 1.0 ;
				n01 +=  errors.get(j) ? 1.0 : 0.0 ;
			}
		}
			
		double ochi = n11 / Math.sqrt((n11+n01)*(n11+n10));
		System.out.println("Responsability: "+ respName + "\n"+ n00 +" " + n01 + " " + n11 + " " + n10 + " " + ochi );

		return  ochi ;

		
	}
	
private String stringify( HashMap<String, Double> map  ) {
		
		String temp = "" ;
		
		Iterator it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        it.remove(); 
	        String line = (String) pairs.getKey() + " " + String.format("%.4g%n",pairs.getValue() ) + "\n" ;
	        temp += line ;
	    }
		
		return temp ;
	}
	

private HashMap<String, Double> processSpectrum( ArrayList<ExecutionNode> trace, ArrayList<String> errorsList ) {
		
		int resp  = 0,
			paths = 0 ;
		
		ArrayList< ArrayList<Boolean> > spectrum = new ArrayList< ArrayList<Boolean> >() ; 
		ArrayList<Boolean> errors = new ArrayList<Boolean>() ;
		HashMap<String, Integer> responsabilities = new HashMap<String, Integer> () ;
		HashMap<Integer, String> crossReference = new HashMap<Integer, String> () ;

		HashMap<String, Double> result = new HashMap<String, Double>() ;
		for ( int j=0; j < trace.size() ; j++ ) {
			//Obtengo la traza j
			ExecutionNode path = trace.get(j);
			//Obtengo el thred que quiero
			if (path.isThread("GameLoop")){

				ThreadNode thread = path.getThread("GameLoop");

				//Obtengo el spectrum
				MethodNode threadSpectrum = thread.getSpectrum();
				ArrayList<Boolean> spectrumRow = new ArrayList<Boolean>()  ;

				//Recorrer el spectrum en preorder e ir armando el spectrum
				LinkedList<MethodNode> fila = new LinkedList<MethodNode>();
				fila.add(threadSpectrum);
				while (fila.size() > 0){
					MethodNode currentMethod = fila.pollFirst();
					String id = currentMethod.getName();
					if (! responsabilities.containsKey(id)){
						responsabilities.put(currentMethod.getName(),resp++);
						crossReference.put(resp,currentMethod.getName());

					}
					while(responsabilities.get(id) >= spectrumRow.size())
						spectrumRow.add(false) ;
					spectrumRow.set( responsabilities.get(id), true ) ;
				
					for ( MethodNode m : currentMethod.getChildren()){
						fila.addLast(m);
					}
				}
				spectrum.add(spectrumRow) ;	
				if (errorsList.contains("test("+path.getName()+")")){
					errors.add(true);
					
				}else {
					errors.add(false);
				}
			}
		}
	
		for( int i = 0 ; i < resp ; i++ ) {
			String respName = crossReference.get(i) ;
			double ochi = getOchiaiCoefficient(i, spectrum, errors,respName) ;
			result.put(respName, ochi) ;
		}
		return result;
	}


public String getResult(ArrayList<ExecutionNode> trace,
		ArrayList<String> errorList) {
	HashMap<String,Double> result = processSpectrum(trace,errorList);
    return stringify(result);
	
}

}
