package de.lmu.genzentrum.tresch;

/*
 * Unterklasse von Node
 */
public class FactorNode extends Node{
	private Object[][] function;	
	private int[]nodeArray;
	
	public FactorNode(String name,int id){
		super(name, id);
	}
	
	public FactorNode(String name,int id, Object[][] function){
		super(name, id);
		this.function=function;
	}
	
	public FactorNode(String name,int id, Object[][] function, int[]nodeArray){
		super(name, id);
		this.function=function;
		this.nodeArray=nodeArray;
	}
	
	public Object[][] getFunction() {
		return function;
	}

	public void setFunction(Object[][] function) {
		this.function = function;
	}

	public int[] getNodeArray() {
		return nodeArray;
	}

	public void setNodeArray(int[] nodeArray) {
		this.nodeArray = nodeArray;
	}
	
	
		
}
