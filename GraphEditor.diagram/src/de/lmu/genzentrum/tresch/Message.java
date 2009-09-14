package de.lmu.genzentrum.tresch;

import java.util.Arrays;


public class Message {
	private Node to, from;
	private boolean isVariableToFactor, isMarginalEdge;
	private double[] value;
	/*
	 * Der Klasse Message wird der Knoten des Graphen übergeben aus dem die Message kommt,
	 * sowie der Knoten, dem die Message übergeben wird.
	 * isVariableToFactor gibt die Richtung an
	 * Value wird intialisiert
	 */
	public Message(Node from, Node to){
		this.from=from;
		this.to=to;  	
			
		if(from instanceof VariableNode){
			isVariableToFactor=true;
			this.value=new double[((VariableNode)from).getValues().length];
		}
		else if(from instanceof FactorNode){
			isVariableToFactor=false;
			this.value=new double[((VariableNode)to).getValues().length];
		}
		
		for(int i=0; i<value.length; i++){
			value[i]=Double.NaN;
		}
	}

	/*
	 * Getters und Setters folgen:
	 */
	public Node getTo() {
		return to;
	}

	public void setTo(Node to) {
		this.to = to;
	}

	public Node getFrom() {
		return from;
	}

	public void setFrom(Node from) {
		this.from = from;
	}
	
	public boolean isVariableToFactor() {
		return isVariableToFactor;
	}

	public double[] getValue() {
		return value;
	}

	public void setValue(double[] value) {
		this.value = value;
	}
	public String toString(){
		StringBuffer sb=new StringBuffer();
		sb.append("msg: "+from+" -> "+to+" vtf:"+isVariableToFactor+" isMarginalEdge:"+isMarginalEdge);
		sb.append(" value:"+Arrays.toString(value));
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + (isMarginalEdge ? 1231 : 1237);
		result = prime * result + (isVariableToFactor ? 1231 : 1237);
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		result = prime * result + Arrays.hashCode(value);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Message))
			return false;
		Message other = (Message) obj;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (isMarginalEdge != other.isMarginalEdge)
			return false;
		if (isVariableToFactor != other.isVariableToFactor)
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		if (!Arrays.equals(value, other.value))
			return false;
		return true;
	}
	
}
