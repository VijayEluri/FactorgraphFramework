/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package graphEditor;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variablenode</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link graphEditor.Variablenode#getType <em>Type</em>}</li>
 *   <li>{@link graphEditor.Variablenode#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see graphEditor.GraphEditorPackage#getVariablenode()
 * @model
 * @generated
 */
public interface Variablenode extends Node {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link graphEditor.VariableType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see graphEditor.VariableType
	 * @see #setType(VariableType)
	 * @see graphEditor.GraphEditorPackage#getVariablenode_Type()
	 * @model required="true"
	 * @generated
	 */
	VariableType getType();

	/**
	 * Sets the value of the '{@link graphEditor.Variablenode#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see graphEditor.VariableType
	 * @see #getType()
	 * @generated
	 */
	void setType(VariableType value);

	/**
	 * Returns the value of the '<em><b>Values</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' attribute list.
	 * @see graphEditor.GraphEditorPackage#getVariablenode_Values()
	 * @model default="0"
	 * @generated
	 */
	EList<Double> getValues();

} // Variablenode