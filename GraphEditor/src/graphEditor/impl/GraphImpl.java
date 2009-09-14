/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package graphEditor.impl;

import graphEditor.Edge;
import graphEditor.Factornode;
import graphEditor.Graph;
import graphEditor.GraphEditorPackage;
import graphEditor.GraphElement;
import graphEditor.Message;
import graphEditor.Node;

import graphEditor.Variablenode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Graph</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link graphEditor.impl.GraphImpl#getName <em>Name</em>}</li>
 *   <li>{@link graphEditor.impl.GraphImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link graphEditor.impl.GraphImpl#getEdges <em>Edges</em>}</li>
 *   <li>{@link graphEditor.impl.GraphImpl#getMessages <em>Messages</em>}</li>
 *   <li>{@link graphEditor.impl.GraphImpl#getResult <em>Result</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GraphImpl extends EObjectImpl implements Graph {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
	protected EList<Node> nodes;

	/**
	 * The cached value of the '{@link #getEdges() <em>Edges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<Edge> edges;

	/**
	 * The cached value of the '{@link #getMessages() <em>Messages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessages()
	 * @generated
	 * @ordered
	 */
	protected EList<Message> messages;

	/**
	 * The default value of the '{@link #getResult() <em>Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResult()
	 * @generated
	 * @ordered
	 */
	protected static final double[][] RESULT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResult() <em>Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResult()
	 * @generated
	 * @ordered
	 */
	protected double[][] result = RESULT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GraphImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphEditorPackage.Literals.GRAPH;
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
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphEditorPackage.GRAPH__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Node> getNodes() {
		if (nodes == null) {
			nodes = new EObjectContainmentEList<Node>(Node.class, this, GraphEditorPackage.GRAPH__NODES);
		}
		return nodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Edge> getEdges() {
		if (edges == null) {
			edges = new EObjectContainmentEList<Edge>(Edge.class, this, GraphEditorPackage.GRAPH__EDGES);
		}
		return edges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Message> getMessages() {
		if (messages == null) {
			messages = new EObjectContainmentEList<Message>(Message.class, this, GraphEditorPackage.GRAPH__MESSAGES);
		}
		return messages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double[][] getResult() {
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResult(double[][] newResult) {
		double[][] oldResult = result;
		result = newResult;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphEditorPackage.GRAPH__RESULT, oldResult, result));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public GraphElement getGraphElement(long id) {
		if (nodes == null) {
			nodes = new EObjectContainmentEList<Node>(Node.class, this, GraphEditorPackage.GRAPH__NODES);
		}
		if (edges == null) {
			edges = new EObjectContainmentEList<Edge>(Edge.class, this, GraphEditorPackage.GRAPH__EDGES);
		}
		if (messages == null) {
			messages = new EObjectContainmentEList<Message>(Message.class, this, GraphEditorPackage.GRAPH__MESSAGES);
		}

		HashMap<Long,GraphElement> map=new HashMap<Long,GraphElement>();
		for(Node n:nodes)
			map.put(Long.valueOf(n.getId()), n);
		for(Edge e:edges)
			map.put(Long.valueOf(e.getId()), e);
		for(Message m:messages)
			map.put(Long.valueOf(m.getId()), m);
		
		return map.get(Long.valueOf(id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EList<Variablenode> getConnectingVariablenodes(Factornode fnode) {
		EList<Variablenode> list=new UniqueEList<Variablenode>();
		if (edges == null) {
			edges = new EObjectContainmentEList<Edge>(Edge.class, this, GraphEditorPackage.GRAPH__EDGES);
		}
		for (Edge e: edges){
			if(e.getFrom() == fnode)
				if (e.getTo() instanceof Variablenode)
					list.add((Variablenode)e.getTo());
			if(e.getTo() == fnode)
				if (e.getFrom() instanceof Variablenode)
					list.add((Variablenode)e.getFrom());
		}
		return list;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GraphEditorPackage.GRAPH__NODES:
				return ((InternalEList<?>)getNodes()).basicRemove(otherEnd, msgs);
			case GraphEditorPackage.GRAPH__EDGES:
				return ((InternalEList<?>)getEdges()).basicRemove(otherEnd, msgs);
			case GraphEditorPackage.GRAPH__MESSAGES:
				return ((InternalEList<?>)getMessages()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GraphEditorPackage.GRAPH__NAME:
				return getName();
			case GraphEditorPackage.GRAPH__NODES:
				return getNodes();
			case GraphEditorPackage.GRAPH__EDGES:
				return getEdges();
			case GraphEditorPackage.GRAPH__MESSAGES:
				return getMessages();
			case GraphEditorPackage.GRAPH__RESULT:
				return getResult();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GraphEditorPackage.GRAPH__NAME:
				setName((String)newValue);
				return;
			case GraphEditorPackage.GRAPH__NODES:
				getNodes().clear();
				getNodes().addAll((Collection<? extends Node>)newValue);
				return;
			case GraphEditorPackage.GRAPH__EDGES:
				getEdges().clear();
				getEdges().addAll((Collection<? extends Edge>)newValue);
				return;
			case GraphEditorPackage.GRAPH__MESSAGES:
				getMessages().clear();
				getMessages().addAll((Collection<? extends Message>)newValue);
				return;
			case GraphEditorPackage.GRAPH__RESULT:
				setResult((double[][])newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case GraphEditorPackage.GRAPH__NAME:
				setName(NAME_EDEFAULT);
				return;
			case GraphEditorPackage.GRAPH__NODES:
				getNodes().clear();
				return;
			case GraphEditorPackage.GRAPH__EDGES:
				getEdges().clear();
				return;
			case GraphEditorPackage.GRAPH__MESSAGES:
				getMessages().clear();
				return;
			case GraphEditorPackage.GRAPH__RESULT:
				setResult(RESULT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case GraphEditorPackage.GRAPH__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case GraphEditorPackage.GRAPH__NODES:
				return nodes != null && !nodes.isEmpty();
			case GraphEditorPackage.GRAPH__EDGES:
				return edges != null && !edges.isEmpty();
			case GraphEditorPackage.GRAPH__MESSAGES:
				return messages != null && !messages.isEmpty();
			case GraphEditorPackage.GRAPH__RESULT:
				return RESULT_EDEFAULT == null ? result != null : !RESULT_EDEFAULT.equals(result);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", result: ");
		result.append(result);
		result.append(')');
		return result.toString();
	}

} //GraphImpl
