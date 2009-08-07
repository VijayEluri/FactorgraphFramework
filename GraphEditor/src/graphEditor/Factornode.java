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
 * A representation of the model object '<em><b>Factornode</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link graphEditor.Factornode#getType <em>Type</em>}</li>
 *   <li>{@link graphEditor.Factornode#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see graphEditor.GraphEditorPackage#getFactornode()
 * @model
 * @generated
 */
public interface Factornode extends Node {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link graphEditor.FunctionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see graphEditor.FunctionType
	 * @see #setType(FunctionType)
	 * @see graphEditor.GraphEditorPackage#getFactornode_Type()
	 * @model required="true"
	 * @generated
	 */
	FunctionType getType();

	/**
	 * Sets the value of the '{@link graphEditor.Factornode#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see graphEditor.FunctionType
	 * @see #getType()
	 * @generated
	 */
	void setType(FunctionType value);

	/**
	 * Returns the value of the '<em><b>Values</b></em>' attribute.
	 * The default value is <code>"null"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' attribute.
	 * @see #setValues(double[][])
	 * @see graphEditor.GraphEditorPackage#getFactornode_Values()
	 * @model default="null" dataType="graphEditor.Table"
	 * @generated
	 */
	double[][] getValues();

	/**
	 * Sets the value of the '{@link graphEditor.Factornode#getValues <em>Values</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Values</em>' attribute.
	 * @see #getValues()
	 * @generated
	 */
	void setValues(double[][] value);

} // Factornode
