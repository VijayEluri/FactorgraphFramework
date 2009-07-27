package graphEditor.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class GraphEditorVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "GraphEditor.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (graphEditor.diagram.edit.parts.GraphEditPart.MODEL_ID
					.equals(view.getType())) {
				return graphEditor.diagram.edit.parts.GraphEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return graphEditor.diagram.part.GraphEditorVisualIDRegistry
				.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				graphEditor.diagram.part.GraphEditorDiagramEditorPlugin
						.getInstance().logError(
								"Unable to parse view type as a visualID number: "
										+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return String.valueOf(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (graphEditor.GraphEditorPackage.eINSTANCE.getGraph().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((graphEditor.Graph) domainElement)) {
			return graphEditor.diagram.edit.parts.GraphEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = graphEditor.diagram.part.GraphEditorVisualIDRegistry
				.getModelID(containerView);
		if (!graphEditor.diagram.edit.parts.GraphEditPart.MODEL_ID
				.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (graphEditor.diagram.edit.parts.GraphEditPart.MODEL_ID
				.equals(containerModelID)) {
			containerVisualID = graphEditor.diagram.part.GraphEditorVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = graphEditor.diagram.edit.parts.GraphEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case graphEditor.diagram.edit.parts.GraphEditPart.VISUAL_ID:
			if (graphEditor.GraphEditorPackage.eINSTANCE.getVariablenode()
					.isSuperTypeOf(domainElement.eClass())) {
				return graphEditor.diagram.edit.parts.FactornodeEditPart.VISUAL_ID;
			}
			if (graphEditor.GraphEditorPackage.eINSTANCE.getFactornode()
					.isSuperTypeOf(domainElement.eClass())) {
				return graphEditor.diagram.edit.parts.VariablenodeEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = graphEditor.diagram.part.GraphEditorVisualIDRegistry
				.getModelID(containerView);
		if (!graphEditor.diagram.edit.parts.GraphEditPart.MODEL_ID
				.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (graphEditor.diagram.edit.parts.GraphEditPart.MODEL_ID
				.equals(containerModelID)) {
			containerVisualID = graphEditor.diagram.part.GraphEditorVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = graphEditor.diagram.edit.parts.GraphEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case graphEditor.diagram.edit.parts.FactornodeEditPart.VISUAL_ID:
			if (graphEditor.diagram.edit.parts.VariablenodeNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case graphEditor.diagram.edit.parts.VariablenodeEditPart.VISUAL_ID:
			if (graphEditor.diagram.edit.parts.WrappingLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case graphEditor.diagram.edit.parts.GraphEditPart.VISUAL_ID:
			if (graphEditor.diagram.edit.parts.FactornodeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (graphEditor.diagram.edit.parts.VariablenodeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case graphEditor.diagram.edit.parts.MessageEditPart.VISUAL_ID:
			if (graphEditor.diagram.edit.parts.WrappingLabel3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (graphEditor.GraphEditorPackage.eINSTANCE.getEdge().isSuperTypeOf(
				domainElement.eClass())) {
			return graphEditor.diagram.edit.parts.EdgeEditPart.VISUAL_ID;
		}
		if (graphEditor.GraphEditorPackage.eINSTANCE.getMessage()
				.isSuperTypeOf(domainElement.eClass())) {
			return graphEditor.diagram.edit.parts.MessageEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(graphEditor.Graph element) {
		return true;
	}

}
