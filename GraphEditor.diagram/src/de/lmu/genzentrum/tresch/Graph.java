package de.lmu.genzentrum.tresch;

import java.util.Hashtable;
import java.util.LinkedList;


public class Graph {
	private LinkedList<Message> mSeq;
	private Hashtable<Node, Hashtable<Node,Hashtable<Object, Double>>> finalHash;
	
	public Graph(LinkedList<Message> mSeq){
		this.mSeq=mSeq;
		finalHash=new Hashtable<Node, Hashtable<Node,Hashtable<Object, Double>>>();		
	}
	
	public void fillFinalHash(){
		Hashtable<Node, Hashtable<Node,Hashtable<Object, Double>>> fromHash=new Hashtable<Node, Hashtable<Node,Hashtable<Object, Double>>>();
		Hashtable<Object, Double> valueHash=new Hashtable<Object, Double>();
		Hashtable<Node, Hashtable<Object, Double>> toHash=new Hashtable <Node,Hashtable<Object, Double>>();
		boolean fromIsInHash=false;
		
		for(int i=0; i<mSeq.size(); i++){	//Schleife für fromHash
			fromIsInHash=false;
			Object keyFrom[]=fromHash.keySet().toArray();
			for(int j=0; j<keyFrom.length; j++){	//hier wird getestet ob der From-Wert bereits im Hash existiert
				Node fromNode=(Node)keyFrom[j];
				if(fromNode.getName().equals(mSeq.get(i).getFrom().getName())){
					fromIsInHash=true;
				}
			}
			
			if(!fromIsInHash){
				for(int j=0; j<mSeq.size(); j++){	//Schleife für toHash
					Message mi=mSeq.get(i);
					Message mj=mSeq.get(j);

					if(mi.getFrom().equals(mj.getFrom())){	//findet alle Kanten die den gleichen FromKnoten haben
						Message message=getMessage(mj.getFrom(), mj.getTo());
						
						for(int x=0; x<getDefSize(message); x++){	//Schleife für ValueHash
							
							if(message.isVariableToFactor()){
							
								VariableNode variable=(VariableNode)mi.getFrom();
								Object key=variable.getValues()[x];
								double value=mi.getValue()[x];
								valueHash.put(key, value);
							}	
							else { //Hier ändern für Faktor zu Variable
								
								VariableNode variable=(VariableNode)mi.getTo();
								Object key=variable.getValues()[x];
								double value=mj.getValue()[x];
								valueHash.put(key, value);
							}	
							
						}
						
						toHash.put(mSeq.get(j).getTo(), new Hashtable<Object, Double>(valueHash));
						valueHash.clear();		
					}		
					
				}
				fromHash.put(mSeq.get(i).getFrom(), new Hashtable<Node, Hashtable<Object, Double>>(toHash));
				toHash.clear();
			}
				
		}
		finalHash=fromHash;
	}
	
	
	/*
	 * print-Methode zum Testen
	 */
	public void print(){
		Object keyFrom[]=this.finalHash.keySet().toArray();
		for(int i=0; i<keyFrom.length; i++){
				Node knoten= (Node)keyFrom[i];
				Object keyTo[]=finalHash.get(knoten).keySet().toArray();	
				for(int x=0; x<keyTo.length; x++){
					Node toKnoten=(Node)keyTo[x];
					Object keyValue[]=finalHash.get(knoten).get(toKnoten).keySet().toArray();
					for(int j=0; j<keyValue.length; j++){
						double value=finalHash.get(knoten).get(toKnoten).get(keyValue[j]);
						System.out.println("From: "+knoten.getName()+" To: "+toKnoten.getName()+" für Def. "+keyValue[j]+" Value: "+value);
					}
					
				}	
			}
	}
		
	
	/*
	 * ein paar kleine Hilfsmethoden folgen
	 */
	public int getDefSize(Message m){	//gibt die Größe der Definitionsmenge einer Message zurück
		int size=0;
		if(m.isVariableToFactor()){
			VariableNode variable=(VariableNode)m.getFrom();
			size=variable.getValues().length;
		}
		else{
			VariableNode variable=(VariableNode)m.getTo();
			size=variable.getValues().length;
		}
		return size;
	}
	
	public Message getMessage(Node from, Node to){	//gibt die Message zurück, wenn man den From-Knoten und den To-Knoten hat
		for(int i=0; i<mSeq.size(); i++){
			if(mSeq.get(i).getFrom()==from && mSeq.get(i).getTo()==to){
				return mSeq.get(i);
			}
		}
		//System.out.println("Es gibt keine Message mit diesen From und To Knoten");
		return null;
	}
	
	public int getMessageIndex(Node from, Node to){					//gibt den Index einer Message zurück, wenn man den From und den To Knoten hat
		for(int i=0; i<mSeq.size(); i++){
			if(mSeq.get(i).getFrom()==from && mSeq.get(i).getTo()==to){
				return i;
			}
		}
		return 0;
	}
	
	
	/*
	 * Getter und Setters:
	 */
	
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

		
}
