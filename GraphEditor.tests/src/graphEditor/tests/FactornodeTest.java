/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package graphEditor.tests;

import graphEditor.Factornode;
import graphEditor.GraphEditorFactory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Factornode</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class FactornodeTest extends NodeTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(FactornodeTest.class);
	}

	/**
	 * Constructs a new Factornode test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FactornodeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Factornode test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Factornode getFixture() {
		return (Factornode)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(GraphEditorFactory.eINSTANCE.createFactornode());
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

} //FactornodeTest
