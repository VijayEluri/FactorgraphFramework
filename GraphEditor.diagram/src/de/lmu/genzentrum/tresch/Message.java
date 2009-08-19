package de.lmu.genzentrum.tresch;


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
			
		if(from.getClass().getName().equals("VariableNode")){
			isVariableToFactor=true;
			this.value=new double[((VariableNode)from).getValues().length];
		}
		else if(from.getClass().getName().equals("FactorNode")){
			isVariableToFactor=false;
			this.value=new double[((VariableNode)to).getValues().length];
		}
		if (value!=null){
			for(int i=0; i<value.length; i++){
				value[i]=Double.NaN;
			}
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
	
	public boolean isMarginalEdge() {
		return isMarginalEdge;
	}

	public void setMarginalEdge(boolean isMarginalEdge) {
		this.isMarginalEdge = isMarginalEdge;
	}

	public boolean isVariableToFactor() {
		return isVariableToFactor;
	}

	public void setVariableToFactor(boolean isVariableToFactor) {
		this.isVariableToFactor = isVariableToFactor;
	}

	public double[] getValue() {
		return value;
	}

	public void setValue(double[] value) {
		this.value = value;
	}
	
	
}
