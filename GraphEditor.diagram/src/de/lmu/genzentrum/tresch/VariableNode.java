package de.lmu.genzentrum.tresch;

import java.util.Arrays;

/*
 * Unterklasse von Node
 */
public class VariableNode extends Node{
	private double[] values;
	private boolean isKnown;
	//private String type;

	public VariableNode(String name, int id){
		super(name, id);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (isKnown ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof VariableNode))
			return false;
		VariableNode other = (VariableNode) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (isKnown != other.isKnown)
			return false;
		if (!Arrays.equals(values, other.values))
			return false;
		return true;
	}

	public VariableNode(String name, int id, double[] values, boolean isKnown){
		super(name, id);	
		this.values=values;
		this.isKnown=isKnown;
	}

	public double[] getValues() {
		return values;
	}

	public void setValues(double[] values) {
		this.values = values;
	}

	public boolean isKnown() {
		return isKnown;
	}

	public void setKnown(boolean isKnown) {
		this.isKnown = isKnown;
	}

	public String toString(){
		StringBuffer sb=new StringBuffer();
		sb.append("{tresch.VariableNode:");
		sb.append(" name:"+this.getName());
		sb.append(" id:"+this.getId());
		sb.append(" known:"+this.isKnown);
		sb.append(" values:");
		for(Object v : values)
			sb.append(" "+v.toString());
		sb.append("}");
		return sb.toString();
	}
	
}
