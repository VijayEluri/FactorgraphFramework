package de.lmu.genzentrum.tresch;

import java.util.Arrays;

/*
 * Unterklasse von Node
 */
public class FactorNode extends Node{
	private double[][] function;	
	private int[]nodeArray;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof FactorNode))
			return false;
		FactorNode other = (FactorNode) obj;
		if (this.id != other.getId())
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public FactorNode(String name,int id){
		super(name, id);
	}
	
	public FactorNode(String name,int id, double[][] function){
		super(name, id);
		this.function=function;
	}
	
	public FactorNode(String name,int id, double[][] function, int[]nodeArray){
		super(name, id);
		this.function=function;
		this.nodeArray=nodeArray;
	}
	
	public double[][] getFunction() {
		return function;
	}

	public void setFunction(double[][] function) {
		this.function = function;
	}

	public int[] getNodeArray() {
		return nodeArray;
	}

	public void setNodeArray(int[] nodeArray) {
		this.nodeArray = nodeArray;
	}
	
	public String toString(){
		StringBuffer sb=new StringBuffer();
		sb.append("{tresch.FactorNode:");
		sb.append(" name:"+this.getName());
		sb.append(" id:"+this.getId());
		sb.append(" nodeArray:"+Arrays.toString(nodeArray));
		sb.append(" function:(");
		for(int i=0;i<function.length;i++)
			sb.append(Arrays.toString(function[i]));
		sb.append(")}");
		return sb.toString();
	}

		
}
