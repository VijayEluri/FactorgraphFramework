package de.lmu.genzentrum.tresch;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.LinkedList;


public class MaxProduct {
	private LinkedList<Message> mSeq;
	private Hashtable<Node, Hashtable<Node,Hashtable<Object, Double>>> finalHash; 
	private Graph graph;
	private VariableNode root;
	private Hashtable<Integer, Hashtable<Object, Hashtable< Integer,Object>>> maxHash;
	private Hashtable<Node, Object> maxVariables;
	
	public MaxProduct(Graph graph, VariableNode root){
		this.graph=graph;
		this.mSeq=graph.getMSeq();
		this.root=root;
		graph.fillFinalHash();
		finalHash=graph.getFinalHash();
		maxHash=new Hashtable<Integer, Hashtable<Object, Hashtable< Integer,Object>>>();
		maxVariables=new Hashtable<Node, Object>();
	}
	
	
	public void doMax() throws NoValueException{
		for(int k=0; k<getMSeq().size(); k++){
			if(getMSeq().get(k).isVariableToFactor()){
				calcVariableToFactor(k);				
			}
			else{	
				calcFactorToVariable(k);
			}
		}
		
		/*
		 * Man muss zunächst an dem gewählten Wurzelknoten, von allen eigenhenden Kannten eines Definitionswerts die Summe 
		 * bilden und davon das Maximum wählen; man bestimmt den Definitionswert für den der Wurzelknoten das Maximum annimmt: 
		 */
		
		Node neighbours[]=this.getNeighbours(root);				//alle Nachbarknoten der Wurzel werden bestimmt
		
		double[] value=new double[root.getValues().length];		//Array, indem in jedem Feld die Summe für einen Def.-Wert steht 
		double maxresult=Double.NEGATIVE_INFINITY;				//für das maximale Ergebnis wird maxresult zunächst auf minus unendlich gesetzt
		double minresult=Double.POSITIVE_INFINITY;				//für das minimale Ergebnis wird minresult zunächst auf plus unendlich gesetzt
		Object def=null;					
		
		for(int j=0; j<root.getValues().length; j++){	
			for(int i=0; i<neighbours.length; i++){				//Es wird die Summe für jeden Nachbarn eines Def.-Wert gebildet 
				value[j]=value[j]+finalHash.get(neighbours[i]).get(root).get(root.getValues()[j]);	
			}	
			maxresult=Math.max(value[j], maxresult);			//max und min von den einzelnen Summen werden gebildet
			minresult=Math.min(value[j], minresult);
			if(maxresult==value[j]){
				def=root.getValues()[j];						//der Def.-Wert zum Maximum wird bestimmt
			}
		}	
		/*
		 * Ausgabe von Maximalen und Minmalen Wert der Wurzel
		 */
		System.out.println("Max: "+maxresult+"  "+def);		
		System.out.println("Min: "+minresult+"  "+def);
		
		maxVariables.put(root, def);							//das Maximum für die Wurzel wird in den Hash eingetragen
		
		calcMarginals(root, null);								//aufruf von calcMarginal
		
		/*
		 * Folgende Schleife ist nur für die Ausgabe 
		 */
		Object maxArray[]=maxVariables.keySet().toArray();
		for(int i=0; i<maxArray.length; i++){
			Node node=(Node) maxArray[i];
			System.out.println("Knoten "+node.getName()+" hat den Def-Wert "+maxVariables.get(node));
		}
	}
	
	
	public void calcMarginals(VariableNode root, FactorNode parent){		
		Node neighbours[]=this.getNeighbours(root);
		Object def=null;
		
		for(int i=0; i<neighbours.length; i++){						//für alle ausgehnden Kanten die nicht zum Elternteil führen
			if(neighbours[i]!=parent){			
				int index=graph.getMessageIndex(neighbours[i] ,root);		//Index der Message wird bestimmt
				if(finalHash.get(neighbours[i]).size()>1){			//wenn Message nicht zum Blattknoten führt
					
					def=maxVariables.get(root);						//def für den Variablenknoten wird bestimmt, geht nur wenn dieser bereits in Hash ist!
					
					Object defArray[]=maxHash.get(index).get(def).keySet().toArray();		
					for(int j=0; j<defArray.length; j++){			//für jeden Knoten der die Wurzel beeinflusst (und daher für diesen max-def-wert) wird der Wert in den Hash maxVariables eingetragen 
						Node variable=getNode((Integer)defArray[j]);
						maxVariables.put(variable, maxHash.get(index).get(def).get(defArray[j]));  	
					}
				}
			}
		}
			
		for(int i=0; i<neighbours.length; i++){						
			if(neighbours[i]!=parent){								//für alle Nachbarknoten, die nicht Eltern sind
				Message m=graph.getMessage(neighbours[i], root);			
				if(m!=null){										//falls die Message existiert
					Node nodeArray[]=getFromNodes(m);				//man bekommt alle Nachbarknoten, von den man nicht kommt
					parent=(FactorNode)neighbours[i];				//Elternteil wird neu gesetzt
					for(int j=0; j<nodeArray.length; j++){			
						root=(VariableNode)nodeArray[j];			//für jeden Nachbarknoten(also nur VariablenKnoten) wird dieser als Wurzel gesetzt 
						//System.out.println(root.getName()+"   "+parent.getName());
						calcMarginals(root, parent);				//und rekursiv für jeden Wert die belegung der Wurzel berechent	
					}
				}
			}
			else break;
		}		
	}
	
	public void calcVariableToFactor(int index) throws NoValueException{
		Message m=mSeq.get(index);
		VariableNode from=(VariableNode)m.getFrom();				//from und to werden im vorraus aufgerufen, damit diese im Laufe der folgenden Berechnungen 
		FactorNode to=(FactorNode)m.getTo();						//nicht jedes mal neu aufgerufen werden müssen
		Hashtable<Node, Hashtable<Object, Double>> toHash=finalHash.get(from);		//toHash wird für das bestimmte from aufgerufen
		
		if(toHash.size()<=1 && from.isKnown()){						//nur für Kanten die aus Blättern kommen
			Object toArray[] =toHash.keySet().toArray();
			for(int i=0; i<toArray.length; i++){
				FactorNode toNode=(FactorNode)toArray[i];
				if(toNode==to){
					Hashtable<Object, Double> valueHash=toHash.get(toNode);
					valueHash.clear();								//die values mit -1 muessen zunaechst geleert werden
					VariableNode variable=(VariableNode)m.getFrom();
					for(int j=0; j<this.graph.getDefSize(m); j++){
						toHash.get(toNode).put(variable.getValues()[j], 0.0);		//man fügt zum valueHash für jedes Def 1 hinzu;
					}
				}		
			}
			
		}
		
		else if(toHash.size()<=1 && !from.isKnown()){
			throw new NoValueException("Variablen-Knoten ist nicht bekannt");
		}
		
		else {														//für inner Kanten
			Object toArray[] =toHash.keySet().toArray();
			Hashtable<Object, Double> messageValues[]= getMessageValues(m);
			Hashtable <Object, Double> resultHash=new Hashtable<Object, Double>();
			
			for(int i=0; i<toArray.length; i++){			
				FactorNode toNode=(FactorNode)toArray[i];
				if(toNode==to){
					
					Hashtable<Object, Double> valueHash=toHash.get(toNode);
					valueHash.clear();								//die values mit -1 muessen zunaechst geleert werden
				
					double result=0.0;	
					for(int j=0; j<graph.getDefSize(m);j++){
					
						for(int x=0; x<messageValues.length; x++){
							Object def=from.getValues()[j];	
							if(!Double.isNaN(messageValues[x].get(def)) ){
								result=result+messageValues[x].get(def);			//Def als Key
							}
							else if(Double.isNaN(messageValues[x].get(def))){
								throw new NoValueException("Kann nicht berechnet werden") ;
							}
						}
					
						resultHash.put(from.getValues()[j], result);
						result=0.0;
					}

					toHash.put(toNode, new Hashtable<Object, Double>(resultHash));
					resultHash.clear(); 
				}			
			}
		}	
	}
	
	public void calcFactorToVariable(int index) throws NoValueException{
		Message m=mSeq.get(index);
		FactorNode from=(FactorNode)m.getFrom();					//from und to werte werden vorher schon mal definiert
		VariableNode to=(VariableNode)m.getTo();
		Object function[][]=from.getFunction();		
		Hashtable<Node, Hashtable<Object, Double>> toHash=finalHash.get(from);		
		Hashtable <Object , Hashtable<Integer, Object>>defHash=new Hashtable<Object , Hashtable<Integer, Object>>();
		Hashtable<Integer, Object> varHash=new Hashtable<Integer, Object>();
					
		if(toHash.size()<=1){										//nur für Kanten die aus Blättern kommen
			Object toArray[] =toHash.keySet().toArray();
			for(int i=0; i<toArray.length; i++){
				Node toNode=(Node)toArray[i];
				if(toNode==to){
					Hashtable<Object, Double> valueHash=toHash.get(toNode);
					valueHash.clear();								//die values mit -1 muessen zunaechst geleert werden
					VariableNode variable=(VariableNode)m.getTo();
					for(int j=0; j<this.graph.getDefSize(m); j++){
						toHash.get(toNode).put(variable.getValues()[j], Math.log((Double)function[1][j]));	//man fügt zum valueHash für jedes Def die Funktionswerte hinzu;
					}
				}		
			}	
		}
		else{														//nur für inner Kanten
			int i=0;
			int idArray[]=from.getNodeArray();
																	//im idArray stehen die ids in der richtigen Reihenfolge drin Bsp(x1-id, x2-id, x3-id) 
			for(int j=0; j<idArray.length; j++){ 
				if(from.getNodeArray()[j]==to.getId()){
					i=j;											//i gibt die Spalte an, die genauer betrachtet wird
				}
			}	
			
			Hashtable <Object, Double> result=new Hashtable<Object, Double>();	//result ist ein neuer hashtable den man komplet in den Hash einsetzen kann
				
			for(int y=0; y<function[0].length; y++){				//die Tabelle wird zeilen-weise durchlaufen
				double wert=0;
				
				for(int x=0; x<function.length; x++){	
					if(x<=function.length-2 && x!=i){				//man addiert die Values der eingehenden Messages	
						double value=this.getValue(this.getNode(idArray[x]), from, function[x][y]);
						if(!Double.isNaN(value)){
							wert=wert+value;	
							varHash.put(from.getNodeArray()[x], function[x][y]);	//function wird zum varHash hinzugefüht
						}
						else if(Double.isNaN(value)){
							throw new NoValueException("Kann nicht berechnet werden");
						}
					}
					else if(x==function.length-1){					//der Funktionswert für die bestimmten werte wird dazu multipliziert
						wert=wert+Math.log((Double)function[x][y]);
					}
				}
				
				double res=0;
				if(!result.containsKey(function[i][y])){			//falls für den Funktionswert kein Eintrag existiert
					res=roundUp(wert);
					result.put(function[i][y], res);
					defHash.put(function[i][y], new Hashtable<Integer, Object>(varHash));		//varHash wird DefHash übergeben
					varHash.clear();
				}
				else{												//falls bereits ein Eintrag für den Funktionswert existiert 
					res=Math.max(result.get(function[i][y]), wert);	//Maximum wird gebildet
					res=roundUp(res);
					wert=roundUp(wert);
					if(res!=wert){									//Wert ist Maximum
						varHash=new Hashtable<Integer, Object>(defHash.get(function[i][y]));	//varHash wird neu gesetzt 
						defHash.remove(function[i][y]);											//alter defHash für den Funktionswert wird entfernt
						defHash.put(function[i][y], new Hashtable<Integer, Object>(varHash));	//defHash bekommt für den Funktionswert den neuen varHash
						varHash.clear();														//varHash wird geleert, damit er neu gesetzt werden kann
					}
					else{												
						defHash.remove(function[i][y]);											//varHash muss nicht verändert werden
						defHash.put(function[i][y], new Hashtable<Integer, Object>(varHash));	//defHasht bekommt varHash für den Funtkionswert
						varHash.clear();
					}
					result.remove(function[i][y]);					//ResultHash bekommt das Maximum (res) als Value übergeben
					result.put(function[i][y], res);				//um anschließend in FinalHash eingetragen zu werden
				}	
			}
			this.maxHash.put(index, new Hashtable<Object , Hashtable<Integer, Object>>(defHash));
			defHash.clear();
			finalHash.get(from).get(to).clear();								//alte Werte aus dem Value-Hash werden entfernt (-1) 
			finalHash.get(from).put(to,new Hashtable<Object, Double>(result));	//result wird als neuer Value-Hash hinzugefügt
		}
	}
	
	
	public Hashtable<Object, Double>[] getMessageValues (Message m){	//gibt Array zurück, der die einzelnen DefHash enthält
		Node from=m.getFrom();
		Node to=m.getTo();
		Node neighbours[]=getNeighbours(from);
		int neighLength=neighbours.length;
		Hashtable<Object, Double>[] resultArray=new Hashtable[neighLength-1];
		
		int k=0;
		for(int i=0; i<neighLength; i++){
			if(neighbours[i]!=to){
				resultArray[k]=finalHash.get(neighbours[i]).get(from);;
				k++;
			}
		}	
		return resultArray;		
	}
			
	public Node[] getFromNodes(Message m){ 							//man bekommt die Nodes die über eine Message in den fromNode von m führen und nicht toNode von m sind
		Node from=m.getFrom();
		Node to=m.getTo();
		Node neighbours[]=getNeighbours(from);
		int neighLength=neighbours.length;
		Node[] resultArray=new Node[neighLength-1];
		int k=0;
		for(int i=0; i<neighLength; i++){
			if(neighbours[i]!=to){
				resultArray[k]=neighbours[i];
				k++;
			}
		}
		return resultArray;		
	}
	
	public double getValue(Node from, Node to, Object def){			//gibt den Value einer bestimmten Message(from und zo gegeben) zu einem bestimmten Def.Wert zurück
		double value=finalHash.get(from).get(to).get(def);
		return value;
	}
	
	public Node[] getNeighbours(Node to){							//gibt alle Nachbarknoten zurück
		Object keyArray[]=finalHash.get(to).keySet().toArray();
		Node nodeArray[]=new Node[keyArray.length];
		for(int i=0; i<keyArray.length; i++){
			nodeArray[i]=(Node)keyArray[i];
		}
		return nodeArray;
	}
	
	public Node getNode(int id){ 									//gibt Knoten zurück, wenn man nur die id weiß
		for(int i=0; i<mSeq.size(); i++){
			Node from=mSeq.get(i).getFrom(); 
			Node to=mSeq.get(i).getTo(); 
			if(from.getId()==id ){
				return from;
			}
			else if( to.getId()==id){
				return to;
				
			}
		}
		return null;
	}
	
	public double roundUp(double value){
		BigDecimal bd=new BigDecimal(value); 
		BigDecimal rn=bd.setScale(10, java.math.BigDecimal.ROUND_HALF_UP);
		return rn.doubleValue();
	}
	
	

	public LinkedList<Message> getMSeq() {
		return mSeq;
	}

	public void setMSeq(LinkedList<Message> seq) {
		mSeq = seq;
	}

	public Hashtable<Node, Hashtable<Node, Hashtable<Object, Double>>> getFinalHash() {
		return finalHash;
	}

	public void setFinalHash(
			Hashtable<Node, Hashtable<Node, Hashtable<Object, Double>>> finalHash) {
		this.finalHash = finalHash;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
	
	
}
