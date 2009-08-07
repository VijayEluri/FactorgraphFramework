package de.lmu.genzentrum.tresch;

public class Message {
	private Node to, from;
	private boolean isVariableToFactor, isMarginalEdge;
	private double[] value;

	/*
	 * Der Klasse Message wird der Knoten des Graphen übergeben aus dem die
	 * Message kommt, sowie der Knoten, dem die Message übergeben wird.
	 * isVariableToFactor gibt die Richtung an Value wird intialisiert
	 */
	public Message(Node from, Node to) {
		this.from = from;
		this.to = to;
		String type=from.getClass().getName();
		
		if (from instanceof VariableNode) {
			isVariableToFactor = true;
			this.value = new double[((VariableNode) from).getValues().length];
		} 
			
		if (from instanceof FactorNode){
			isVariableToFactor = false;
			this.value = new double[((VariableNode) to).getValues().length];
		}
			
		for (int i = 0; i < value.length; i++) {
			value[i] = -1;
		}
	}

	public Node getFrom() {
		return from;
	}

	/*
	 * Getters und Setters folgen:
	 */
	public Node getTo() {
		return to;
	}

	public double[] getValue() {
		return value;
	}

	public boolean isMarginalEdge() {
		return isMarginalEdge;
	}

	public boolean isVariableToFactor() {
		return isVariableToFactor;
	}

	public void setFrom(Node from) {
		this.from = from;
	}

	public void setMarginalEdge(boolean isMarginalEdge) {
		this.isMarginalEdge = isMarginalEdge;
	}

	public void setTo(Node to) {
		this.to = to;
	}

	public void setValue(double[] value) {
		this.value = value;
	}

	public void setVariableToFactor(boolean isVariableToFactor) {
		this.isVariableToFactor = isVariableToFactor;
	}

}
