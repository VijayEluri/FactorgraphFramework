/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package graphEditor.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>graphEditor</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class GraphEditorTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new GraphEditorTests("graphEditor Tests");
		suite.addTestSuite(GraphTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphEditorTests(String name) {
		super(name);
	}

} //GraphEditorTests
