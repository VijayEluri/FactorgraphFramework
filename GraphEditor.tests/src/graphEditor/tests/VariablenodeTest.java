/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package graphEditor.tests;

import graphEditor.GraphEditorFactory;
import graphEditor.Variablenode;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Variablenode</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class VariablenodeTest extends NodeTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(VariablenodeTest.class);
	}

	/**
	 * Constructs a new Variablenode test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariablenodeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Variablenode test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Variablenode getFixture() {
		return (Variablenode)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(GraphEditorFactory.eINSTANCE.createVariablenode());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //VariablenodeTest
