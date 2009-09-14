package de.lmu.genzentrum.tresch;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Set;

import org.apache.commons.collections.MapUtils;


public class Graph {
	private LinkedList<Message> mSeq;
	private Hashtable<Node, Hashtable<Node,Hashtable<Double, Double>>> finalHash;
	private Hashtable<Integer, Message> mSeqHash;
	
	
	public Graph(LinkedList<Message> mSeq){
		this.mSeq=mSeq;
		finalHash=new Hashtable<Node, Hashtable<Node,Hashtable<Double, Double>>>();		
		mSeqHash=new Hashtable <Integer, Message>();
		fillMSeqHash();
		fillFinalHash();
	}
	
	public void fillMSeqHash(){
		for(int i=0; i<mSeq.size(); i++){
			mSeqHash.put(i, mSeq.get(i));
		}
	}
	
	public void fillFinalHash(){
	//	System.out.println("fillFinalHasch------------------");
		Hashtable<Node, Hashtable<Node,Hashtable<Double, Double>>> fromHash=new Hashtable<Node, Hashtable<Node,Hashtable<Double, Double>>>();
		Hashtable<Node, Hashtable<Double, Double>> toHash=new Hashtable <Node,Hashtable<Double, Double>>();
		Hashtable<Double, Double> valueHash=new Hashtable<Double, Double>();
		boolean fromIsInHash=false;
		
		for(int i=0; i<mSeq.size(); i++){	//Schleife für fromHash
		//	System.out.println("i: "+i);
			fromIsInHash=false;

			Object keyFrom[]=fromHash.keySet().toArray();
			for(int j=0; j<keyFrom.length; j++){	//hier wird getestet ob der From-Wert bereits im Hash existiert
				Node fromNode=(Node)keyFrom[j];
				if(fromNode.equals(mSeq.get(i).getFrom())){
					fromIsInHash=true;
				}
			}
			if(fromIsInHash==false){
				for(int j=0; j<mSeq.size(); j++){	//Schleife für toHash
					Message mi=mSeq.get(i);
					Message mj=mSeq.get(j);
//					System.out.println("mi: "+mi);
//					System.out.println("mj: "+mj);
		
					Node miFrom=mi.getFrom();
					Node mjFrom=mj.getFrom();
//					System.out.println("i"+i+"j"+j);
//					System.out.println(miFrom.hashCode());
//					System.out.println(mjFrom.hashCode());
					
					if(miFrom.equals(mjFrom)){	//findet alle Kanten die den gleichen FromKnoten haben
//						System.out.println("equals");
						Message message=getMessage(mj.getFrom(), mj.getTo());
//						System.out.println(message);
//						System.out.println("def: "+getDefSize(message));
						for(int x=0; x<getDefSize(message); x++){	//Schleife für ValueHash
							if(message.isVariableToFactor()){
								VariableNode variable=(VariableNode)mi.getFrom();
								double key=variable.getValues()[x];
								double value=mi.getValue()[x];
//								System.out.println("vtf key: "+key+" value: "+value);
								valueHash.put(key, value);
							}	
							else { //Hier ändern für Faktor zu Variable
								VariableNode variable=(VariableNode)mi.getTo();
								double key=variable.getValues()[x];
								double value=mj.getValue()[x];
//								System.out.println("ftv key:"+key+"value"+value);
								valueHash.put(key, value);
							}	
						}
						toHash.put(mSeq.get(j).getTo(), new Hashtable<Double, Double>(valueHash));
						valueHash.clear();		
					}		
				}
//				MapUtils.debugPrint(System.out, "toHash", toHash);
				fromHash.put(mSeq.get(i).getFrom(), new Hashtable<Node, Hashtable<Double, Double>>(toHash));
				toHash.clear();
			}
		}
		finalHash=fromHash;
//		System.out.println("---------------------fillFinalHasch------------------");
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
	public void printout(){
		System.out.println("-------graph---------");
		MapUtils.debugPrint(System.out, "finalHash", finalHash);
		MapUtils.debugPrint(System.out, "mSeqHash", mSeqHash);
		System.out.println("-------graph----ende-----");
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
		for(int i=0; i<mSeqHash.size(); i++){
			if(mSeqHash.get(i).getFrom()==from && mSeqHash.get(i).getTo()==to){
				return mSeqHash.get(i);
			}
		}
		//System.out.println("Es gibt keine Message mit diesen From und To Knoten");
		return null;
	}
	
	public int getMessageIndex(Node from, Node to){					//gibt den Index einer Message zurück, wenn man den From und den To Knoten hat
		for(int i=0; i<mSeqHash.size(); i++){
			if(mSeqHash.get(i).getFrom()==from && mSeqHash.get(i).getTo()==to){
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

	public Hashtable<Node, Hashtable<Node, Hashtable<Double, Double>>> getFinalHash() {
		return finalHash;
	}

	public void setFinalHash(
			Hashtable<Node, Hashtable<Node, Hashtable<Double, Double>>> finalHash) {
		this.finalHash = finalHash;
	}

	public Hashtable<Integer, Message> getMSeqHash() {
		return mSeqHash;
	}

	public void setMSeqHash(Hashtable<Integer, Message> seqHash) {
		mSeqHash = seqHash;
	}
		public String toString(){
			StringBuffer sb=new StringBuffer();
			sb.append("graph: {\n");
			for(Message m:mSeq)
				sb.append(" "+m+",\n");
			
			sb.append("mSeqHash: {");
			Set<Integer> keys=mSeqHash.keySet();
			for(Integer i:keys){
				sb.append("("+i+"=");
				sb.append(mSeqHash.get(i)+"),");
			}
			sb.append("}\n");
			
			sb.append("finalHash: {");
			//Hashtable<Node, Hashtable<Node,Hashtable<Double, Double>>>
			Set<Node> fkeys=finalHash.keySet();
			for(Node n:fkeys){
				sb.append("\n("+n+"=");
				Hashtable<Node,Hashtable<Double, Double>> v=finalHash.get(n);
				Set<Node> vkeys=v.keySet();
				for(Node r:vkeys){
					sb.append(" "+r+"=");
					Hashtable<Double, Double> d=v.get(r);
					sb.append("{");
					Set<Double> dkeys=d.keySet();
					for(Double x:dkeys){
						sb.append("["+x+"="+d.get(x)+"]");
					}
					sb.append("}");
				}
				
			}
			sb.append("}\n");
			
			sb.append("}");
			return sb.toString();
		}	
}
