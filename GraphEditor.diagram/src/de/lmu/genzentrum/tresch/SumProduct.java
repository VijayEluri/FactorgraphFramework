package de.lmu.genzentrum.tresch;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.LinkedList;


public class SumProduct{
	private LinkedList<Message> mSeq;
	private Hashtable<Node, Hashtable<Node,Hashtable<Object, Double>>> finalHash; 
	private Graph graph;
	private Hashtable <Node ,Hashtable<Object, Double>> marginalHash;
	
	//SumProduct bekommt das Objekt Graph übergeben
	public SumProduct(Graph graph){
		this.graph=graph;
		this.mSeq=graph.getMSeq();
		graph.fillFinalHash();
		finalHash=graph.getFinalHash();
		marginalHash=new Hashtable<Node ,Hashtable<Object, Double>>();
	}
	
	
	/*
	 * DoSum-Algorithmus der für die jeweiligen Messages den Value berechnet
	 * und anschließend dieRandverteilungen berechnet		
	 */
	public Hashtable<Node ,Hashtable<Object, Double>> doSum() throws NoValueException{	
		for(int i=0; i<getMSeq().size(); i++){
			if(getMSeq().get(i).isVariableToFactor()){
				try{
				calcVariableToFactor(i);
				}catch (NoValueException e){
					System.out.println(e.getMessage());
				}
			}
			else{
				try{
					calcFactorToVariable(i);
				}catch (NoValueException e){
					System.out.println(e.getMessage());
				}			
			}
		}
		calcMarginals();
		return marginalHash;
	}

	
	/*
	 *	Methode berechnet die Values für die Messages die von einem Variablen-Knoten
	 * 	zu einem Faktor-Knoten laufen 
	 */
	public void calcVariableToFactor(int index) throws NoValueException{
		Message m=mSeq.get(index);
		VariableNode from=(VariableNode)m.getFrom();		//from und to werden im vorraus aufgerufen, damit diese im Laufe der folgenden Berechnungen 
		FactorNode to=(FactorNode)m.getTo();				//nicht jedes mal neu aufgerufen werden müssen
		Hashtable<Node, Hashtable<Object, Double>> toHash=finalHash.get(from);		//toHash wird für das bestimmte from aufgerufen
		
		if(toHash.size()<=1 && from.isKnown()){	//nur für Kanten die aus Blättern kommen
			Object toArray[] =toHash.keySet().toArray();
			for(int i=0; i<toArray.length; i++){
				FactorNode toNode=(FactorNode)toArray[i];
				if(toNode==to){
					Hashtable<Object, Double> valueHash=toHash.get(toNode);
					valueHash.clear();			//die values mit -1 muessen zunaechst geleert werden
					VariableNode variable=(VariableNode)m.getFrom();
					for(int j=0; j<this.graph.getDefSize(m); j++){
						toHash.get(toNode).put(variable.getValues()[j], 1.0);	//man fügt zum valueHash für jedes Def 1 hinzu;
					}									
				}		
			}	
		}
		
		else if(toHash.size()<=1 && !from.isKnown()){
			throw new NoValueException("Variablen-Knoten sit nicht bekannt");
		}
		
		else {	//für inner Kanten
			Object toArray[] =toHash.keySet().toArray();
			Hashtable<Object, Double> messageValues[]= getMessageValues(m);
			Hashtable <Object, Double> resultHash=new Hashtable<Object, Double>();
			
			for(int i=0; i<toArray.length; i++){			
				FactorNode toNode=(FactorNode)toArray[i];
				if(toNode==to){
					
					Hashtable<Object, Double> valueHash=toHash.get(toNode);
					valueHash.clear();			//die values mit -1 muessen zunaechst geleert werden
				
					double result=1.0;	
					for(int j=0; j<graph.getDefSize(m);j++){
					
						for(int x=0; x<messageValues.length; x++){
							Object def=from.getValues()[j];
							if(!Double.isNaN(messageValues[x].get(def)) ){
								result=result*messageValues[x].get(def);			//Def als Key
							}
							else if(Double.isNaN(messageValues[x].get(def))){
								throw new NoValueException("Kann nicht berechnet werden") ;
							}
						}
						result=roundUp(result);
					
						resultHash.put(from.getValues()[j], result);
						//toHash.get(toNode).put(from.getValues()[j], result);	//neu berechnete Values werden in finalHash hinzugefügt
						result=1.0;
					}
					resultHash=this.normalize(resultHash);
					toHash.put(toNode, new Hashtable<Object, Double>(resultHash));
					resultHash.clear(); 
				}			
			}
		}	
	}
	
	/*
	 * Methode berechnet den Value der Messages die von Faktor-Knoten 
	 * zu Variablen-Knoten geht	
	 */
	public void calcFactorToVariable(int index) throws NoValueException{
		Message m=mSeq.get(index);
		FactorNode from=(FactorNode)m.getFrom();	//from und to werte werden vorher schon mal definiert
		VariableNode to=(VariableNode)m.getTo();
		Object function[][]=from.getFunction();		
		Hashtable<Node, Hashtable<Object, Double>> toHash=finalHash.get(from);		
		
		if(toHash.size()<=1){	//nur für Kanten die aus Blättern kommen
			Object toArray[] =toHash.keySet().toArray();
			for(int i=0; i<toArray.length; i++){
				Node toNode=(Node)toArray[i];
				if(toNode==to){
					Hashtable<Object, Double> valueHash=toHash.get(toNode);
					valueHash.clear();			//die values mit -1 muessen zunaechst geleert werden
					VariableNode variable=(VariableNode)m.getTo();
					for(int j=0; j<this.graph.getDefSize(m); j++){
						toHash.get(toNode).put(variable.getValues()[j], (Double)function[1][j]);	//man fügt zum valueHash für jedes Def die Funktionswerte hinzu;
					}									
				}		
			}	
		}
		else{	//nur für inner Kanten
			int i=0;
			int idArray[]=from.getNodeArray();
			//im nameArray stehen die Namen in der richtigen Reihenfolge drin Bsp(x1, x2, x3) 
			
			for(int j=0; j<idArray.length; j++){ 
				if(from.getNodeArray()[j]==to.getId()){
					i=j;	//i gibt die Spalte an, die genauer betrachtet
				}
			}			
			Hashtable <Object, Double> result=new Hashtable<Object, Double>();	//result ist ein neuer hashtable den man komplet in den Hash einsetzen kann
				
			for(int y=0; y<function[0].length; y++){	//die Tabelle wird zeilen-weise durchlaufen
				double wert=1;
				for(int x=0; x<function.length; x++){	
					if(x<=function.length-2 && x!=i){	//man multipliziert die Values der eingehenden Messages	
						double value=this.getValue(this.getNode(idArray[x]), from, function[x][y]);
							if(!Double.isNaN(value)){
								wert=wert*value;			
							}
							else if(Double.isNaN(value)){
								throw new NoValueException("Kann nicht berechnet werden, da der Value einer eingehenden Message fehlt!");
							}
						
					}
					else if(x==function.length-1){	//der Funktionswert für die bestimmten werte wird dazu multipliziert
						wert=wert*(Double)function[x][y];
					}
				}
				
				double res=0;
				if(!result.containsKey(function[i][y])){	//falls für den Funktionswert bereits ein Eintrag existiet
					res=wert;
					res=roundUp(res);
					result.put(function[i][y], res);
				}
				else{	//falls noch kein funktionswert exitiert
					res=result.get(function[i][y])+wert;
					res=roundUp(res);
					result.remove(function[i][y]);
					result.put(function[i][y], res);
				}
			}
			result=this.normalize(result);
			finalHash.get(from).get(to).clear();	//alte Werte aus dem Value-Hash werden entfernt (-1) 
			finalHash.get(from).put(to,new Hashtable<Object, Double>(result));	//result wird als neuer Value-Hash hinzugefügt
		}
	}
	
	
	/*
	 * Methode berechnet die Werte der einzelnen Variablen-Knoten (Randverteilungen)
	 */
	public void calcMarginals(){
		Hashtable<Object, Double> resultHash=new Hashtable<Object, Double>();
		
		Object finalArray[]=finalHash.keySet().toArray();
		for(int i=0; i<finalArray.length; i++){
			if(finalArray[i].getClass().getName().equals("VariableNode")){	//man sucht im fromHash die einzelnen VariablenKnoten
				Message m=mSeq.get(i);
				VariableNode node=(VariableNode)finalArray[i];
				
				double wert[]=new double[getGraph().getDefSize(m)];	//man braucht soviele Werte wie die Definitionsgröße
				for(int t=0; t<wert.length; t++){
					wert[t]=1;	//man muss das wert-array mit dem neutralen Element der Multiplikation füllen
				}
				
				for(int t=0; t<wert.length; t++){	
					Object def=node.getValues()[t];	//man braucht den definitionswert für den eine berechnung stattfindet
					Object fromArray[]=finalHash.keySet().toArray();
					for(int j=0; j<fromArray.length; j++){	
						Node from=(Node)fromArray[j];
						Object toArray[]=finalHash.get(from).keySet().toArray();
						for(int k=0; k<toArray.length; k++){
							Node to=(Node)toArray[k];
							if(to.equals(node)){
								double value=(double)(finalHash.get(from).get(to).get(def));	
								wert[t]=wert[t]*value;		//wert wird berechnet in dem man alle eingehenden kannten für einen defwert multipliziert				
							}
						}
					}
					wert[t]=roundUp(wert[t]);
					resultHash.put(def, wert[t]);	//Wert wird in einem HilfsHash gespeichert 
				}
				resultHash=this.normalize(resultHash);		//der HilfsHash resultHash wird zunächst normaliziert
				
				//nur für die Ausgabe:
				Object keyArray[]=resultHash.keySet().toArray();
				for(int k=0; k<keyArray.length; k++){
					System.out.println("Knoten "+node.getName()+" Def: "+keyArray[k]+ " hat den Wert "+resultHash.get(keyArray[k]));
				}
				marginalHash.put(node, resultHash);
			}
			
		}
	}
	
	/*
	 * Hilfsfunktionen:
	 */
	public Hashtable <Object, Double> normalize(Hashtable <Object, Double> result){
		double sum=0;
		Hashtable <Object, Double> resultNormalized=new Hashtable<Object, Double>();
		Object resultArray []=result.keySet().toArray();
		for(int i=0; i<resultArray.length; i++){
			sum=sum+result.get(resultArray[i]);
		}
		for(int i=0; i<resultArray.length; i++){
			resultNormalized.put(resultArray[i], roundUp(result.get(resultArray[i])/sum));
		}
		return resultNormalized;
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
		
	public Node[] getNeighbours(Node node){							//gibt alle Nachbarknoten zurück
		Object keyArray[]=finalHash.get(node).keySet().toArray();
		Node nodeArray[]=new Node[keyArray.length];
		for(int i=0; i<keyArray.length; i++){
			nodeArray[i]=(Node)keyArray[i];
		}
		return nodeArray;
	}
	
	
	public Node getNode(int id){	//gibt den Node einer Message zurück, wenn man nur die Id des FromNodes hat 
		Object[] fromKey=finalHash.keySet().toArray();
		for(int i=0; i<fromKey.length; i++){
			Node fromNode=(Node)fromKey[i];
			if(fromNode.getId()==id){
				return fromNode;
			}
		}
		return null;
	}
	
	public double getValue(Node from, Node to, Object def){
		double value=finalHash.get(from).get(to).get(def);
		return value;
	}
	
	public double roundUp(double value){
		BigDecimal bd=new BigDecimal(value); 
		BigDecimal rn=bd.setScale(10, java.math.BigDecimal.ROUND_HALF_UP);
		return rn.doubleValue();
	}
	
	
	/*
	 * Getters & Setters:
	 */
	
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public Hashtable<Node, Hashtable<Node, Hashtable<Object, Double>>> getFinalHash() {
		return finalHash;
	}

	public void setFinalHash(
			Hashtable<Node, Hashtable<Node, Hashtable<Object, Double>>> finalHash) {
		this.finalHash = finalHash;
	}

	public LinkedList<Message> getMSeq() {
		return mSeq;
	}

	public void setMSeq(LinkedList<Message> seq) {
		mSeq = seq;
	}
	
	
}
