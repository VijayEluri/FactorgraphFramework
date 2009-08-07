/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package graphEditor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Message Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see graphEditor.GraphEditorPackage#getMessageType()
 * @model
 * @generated
 */
public enum MessageType implements Enumerator {
	/**
	 * The '<em><b>Marginal Edge</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MARGINAL_EDGE_VALUE
	 * @generated
	 * @ordered
	 */
	MARGINAL_EDGE(0, "MarginalEdge", "MarginalEdge"),

	/**
	 * The '<em><b>Variable To Factor</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VARIABLE_TO_FACTOR_VALUE
	 * @generated
	 * @ordered
	 */
	VARIABLE_TO_FACTOR(1, "VariableToFactor", "VariableToFactor");

	/**
	 * The '<em><b>Marginal Edge</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Marginal Edge</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MARGINAL_EDGE
	 * @model name="MarginalEdge"
	 * @generated
	 * @ordered
	 */
	public static final int MARGINAL_EDGE_VALUE = 0;

	/**
	 * The '<em><b>Variable To Factor</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Variable To Factor</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VARIABLE_TO_FACTOR
	 * @model name="VariableToFactor"
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE_TO_FACTOR_VALUE = 1;

	/**
	 * An array of all the '<em><b>Message Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final MessageType[] VALUES_ARRAY =
		new MessageType[] {
			MARGINAL_EDGE,
			VARIABLE_TO_FACTOR,
		};

	/**
	 * A public read-only list of all the '<em><b>Message Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<MessageType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Message Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MessageType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MessageType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Message Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MessageType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MessageType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Message Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MessageType get(int value) {
		switch (value) {
			case MARGINAL_EDGE_VALUE: return MARGINAL_EDGE;
			case VARIABLE_TO_FACTOR_VALUE: return VARIABLE_TO_FACTOR;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private MessageType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //MessageType
