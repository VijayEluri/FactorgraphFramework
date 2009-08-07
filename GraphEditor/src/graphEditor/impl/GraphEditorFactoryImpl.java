/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package graphEditor.impl;

import java.util.Arrays;
import java.util.StringTokenizer;

import graphEditor.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class GraphEditorFactoryImpl extends EFactoryImpl implements
		GraphEditorFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static GraphEditorFactory init() {
		try {
			GraphEditorFactory theGraphEditorFactory = (GraphEditorFactory)EPackage.Registry.INSTANCE.getEFactory("http://de.broicher.factorgraph.graphEditor"); 
			if (theGraphEditorFactory != null) {
				return theGraphEditorFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new GraphEditorFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public GraphEditorFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case GraphEditorPackage.GRAPH: return createGraph();
			case GraphEditorPackage.FACTORNODE: return createFactornode();
			case GraphEditorPackage.VARIABLENODE: return createVariablenode();
			case GraphEditorPackage.MESSAGE: return createMessage();
			case GraphEditorPackage.EDGE: return createEdge();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case GraphEditorPackage.FUNCTION_TYPE:
				return createFunctionTypeFromString(eDataType, initialValue);
			case GraphEditorPackage.VARIABLE_TYPE:
				return createVariableTypeFromString(eDataType, initialValue);
			case GraphEditorPackage.MESSAGE_TYPE:
				return createMessageTypeFromString(eDataType, initialValue);
			case GraphEditorPackage.TABLE:
				return createTableFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case GraphEditorPackage.FUNCTION_TYPE:
				return convertFunctionTypeToString(eDataType, instanceValue);
			case GraphEditorPackage.VARIABLE_TYPE:
				return convertVariableTypeToString(eDataType, instanceValue);
			case GraphEditorPackage.MESSAGE_TYPE:
				return convertMessageTypeToString(eDataType, instanceValue);
			case GraphEditorPackage.TABLE:
				return convertTableToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Graph createGraph() {
		GraphImpl graph = new GraphImpl();
		generateID(graph);
		return graph;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Factornode createFactornode() {
		FactornodeImpl factornode = new FactornodeImpl();
		generateID(factornode);
		return factornode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Variablenode createVariablenode() {
		VariablenodeImpl variablenode = new VariablenodeImpl();
		generateID(variablenode);
		return variablenode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Message createMessage() {
		MessageImpl message = new MessageImpl();
		generateID(message);
		return message;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Edge createEdge() {
		EdgeImpl edge = new EdgeImpl();
		generateID(edge);
		return edge;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionType createFunctionTypeFromString(EDataType eDataType,
			String initialValue) {
		FunctionType result = FunctionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFunctionTypeToString(EDataType eDataType,
			Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public VariableType createVariableTypeFromString(EDataType eDataType,
			String initialValue) {
		VariableType result = VariableType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVariableTypeToString(EDataType eDataType,
			Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public MessageType createMessageTypeFromString(EDataType eDataType,
			String initialValue) {
		MessageType result = MessageType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMessageTypeToString(EDataType eDataType,
			Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public double[][] createTableFromString(EDataType eDataType,
			String initialValue) {
		//return (double[][])super.createFromString(initialValue);
		double[][] value = null;
		
		String[] init=initialValue.split("!");
		if (init.length==2){
			String start=init[0];
			String[] startStrings=start.split("x");
			value=new double[Integer.parseInt(startStrings[0])][Integer.parseInt(startStrings[1])];		
			
			String array=init[1];
			if (array.startsWith("{") && array.endsWith("}")) {
				String substring = array.substring(1,
						array.length() - 1);
				String[] lines=substring.split(";");

				for (int i = 0; i <lines.length; i++) {
					String line = lines[i];
					if (line.startsWith("[") && line.endsWith("]")) {
						String linesubstring = line.substring(1, line.length() - 1);
						String[] lineValues = linesubstring.split(",");
						double[] lineDoubles=new double[lineValues.length];
						for(int l=0;l<lineValues.length;l++)
							lineDoubles[l]=(Double.valueOf(lineValues[l]).doubleValue());
						value[i]=lineDoubles;
						}
					}
				}
			}
		return value;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public String convertTableToString(EDataType eDataType, Object instanceValue) {
		//return super.convertToString(instanceValue);
		double[][] value=(double[][])instanceValue;
		StringBuffer sb = new StringBuffer();
		sb.append(value.length+"x"+value[0].length+"!");
		sb.append("{");
		for (int i = 0; i < value.length; i++) {
			sb.append(Arrays.toString(value[i]));
			if (i != value.length - 1)
				sb.append(";");
		}
		sb.append("}");
		return sb.toString();

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public GraphEditorPackage getGraphEditorPackage() {
		return (GraphEditorPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static GraphEditorPackage getPackage() {
		return GraphEditorPackage.eINSTANCE;
	}

	private static long generate_id_counter = 0;
	private static long generate_variable_id_counter = -1;
	private static long generate_factor_id_counter = -1;
	private static long generate_graph_id_counter = 0;
	private static int generate_message_id_counter = -1;

	/**
	 * We want a way to generate IDs that we know are unique between different
	 * elements in the same model, but currently we don't mind if they aren't
	 * unique between different models created at the exact same time. (For
	 * this, we would need to use UUIDs.) Applies the ID to the given element.
	 * 
	 * This is probably a really unpleasant initial implementation but can
	 * easily be changed in the future :)
	 * 
	 * Currently it sets IDs to something like "Model.12b52.42", where - the
	 * first part is the package name - the second part is the time the package
	 * factory was instantiated (in hex) - the third part is a unique ID to this
	 * factory instance (in hex)
	 * 
	 * @see GeneratedElement
	 * @param obj
	 */
	protected void generateID(EObject obj) {
		if (obj instanceof Graph) {
			Graph ge = (Graph) obj;
			generate_graph_id_counter++;
			ge.setName("Graph" + Long.toString(generate_graph_id_counter));
		}
		if (obj instanceof Factornode) {
			Node ge = (Node) obj;
			generate_factor_id_counter++;
			generate_id_counter++;
			ge.setId(generate_id_counter);
			ge.setName("F" + Long.toString(generate_factor_id_counter));
		}
		if (obj instanceof Variablenode) {
			Node ge = (Node) obj;
			generate_variable_id_counter++;
			generate_id_counter++;
			ge.setId(generate_id_counter);
			ge.setName("X" + Long.toString(generate_variable_id_counter));
		}
		if (obj instanceof Message) {
			Message ge = (Message) obj;
			generate_message_id_counter++;
			ge.setCount(generate_message_id_counter);
		}
	}

} // GraphEditorFactoryImpl
