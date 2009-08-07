package graphEditor.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class GraphEditorDiagramUpdater {

	/**
	 * @generated
	 */
	public static List getSemanticChildren(View view) {
		switch (graphEditor.diagram.part.GraphEditorVisualIDRegistry
				.getVisualID(view)) {
		case graphEditor.diagram.edit.parts.GraphEditPart.VISUAL_ID:
			return getGraph_1000SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getGraph_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		graphEditor.Graph modelElement = (graphEditor.Graph) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getNodes().iterator(); it.hasNext();) {
			graphEditor.Node childElement = (graphEditor.Node) it.next();
			int visualID = graphEditor.diagram.part.GraphEditorVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == graphEditor.diagram.edit.parts.VariablenodeEditPart.VISUAL_ID) {
				result
						.add(new graphEditor.diagram.part.GraphEditorNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == graphEditor.diagram.edit.parts.FactornodeEditPart.VISUAL_ID) {
				result
						.add(new graphEditor.diagram.part.GraphEditorNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view) {
		switch (graphEditor.diagram.part.GraphEditorVisualIDRegistry
				.getVisualID(view)) {
		case graphEditor.diagram.edit.parts.GraphEditPart.VISUAL_ID:
			return getGraph_1000ContainedLinks(view);
		case graphEditor.diagram.edit.parts.VariablenodeEditPart.VISUAL_ID:
			return getFactornode_2002ContainedLinks(view);
		case graphEditor.diagram.edit.parts.FactornodeEditPart.VISUAL_ID:
			return getVariablenode_2001ContainedLinks(view);
		case graphEditor.diagram.edit.parts.EdgeEditPart.VISUAL_ID:
			return getEdge_4001ContainedLinks(view);
		case graphEditor.diagram.edit.parts.MessageEditPart.VISUAL_ID:
			return getMessage_4002ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view) {
		switch (graphEditor.diagram.part.GraphEditorVisualIDRegistry
				.getVisualID(view)) {
		case graphEditor.diagram.edit.parts.VariablenodeEditPart.VISUAL_ID:
			return getFactornode_2002IncomingLinks(view);
		case graphEditor.diagram.edit.parts.FactornodeEditPart.VISUAL_ID:
			return getVariablenode_2001IncomingLinks(view);
		case graphEditor.diagram.edit.parts.EdgeEditPart.VISUAL_ID:
			return getEdge_4001IncomingLinks(view);
		case graphEditor.diagram.edit.parts.MessageEditPart.VISUAL_ID:
			return getMessage_4002IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view) {
		switch (graphEditor.diagram.part.GraphEditorVisualIDRegistry
				.getVisualID(view)) {
		case graphEditor.diagram.edit.parts.VariablenodeEditPart.VISUAL_ID:
			return getFactornode_2002OutgoingLinks(view);
		case graphEditor.diagram.edit.parts.FactornodeEditPart.VISUAL_ID:
			return getVariablenode_2001OutgoingLinks(view);
		case graphEditor.diagram.edit.parts.EdgeEditPart.VISUAL_ID:
			return getEdge_4001OutgoingLinks(view);
		case graphEditor.diagram.edit.parts.MessageEditPart.VISUAL_ID:
			return getMessage_4002OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getGraph_1000ContainedLinks(View view) {
		graphEditor.Graph modelElement = (graphEditor.Graph) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_Edge_4001(modelElement));
		result
				.addAll(getContainedTypeModelFacetLinks_Message_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getVariablenode_2001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getFactornode_2002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getEdge_4001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getMessage_4002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getVariablenode_2001IncomingLinks(View view) {
		graphEditor.Variablenode modelElement = (graphEditor.Variablenode) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Edge_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Message_4002(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getFactornode_2002IncomingLinks(View view) {
		graphEditor.Factornode modelElement = (graphEditor.Factornode) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Edge_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Message_4002(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getEdge_4001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getMessage_4002IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getVariablenode_2001OutgoingLinks(View view) {
		graphEditor.Variablenode modelElement = (graphEditor.Variablenode) view
				.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Edge_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Message_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getFactornode_2002OutgoingLinks(View view) {
		graphEditor.Factornode modelElement = (graphEditor.Factornode) view
				.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Edge_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Message_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getEdge_4001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getMessage_4002OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Edge_4001(
			graphEditor.Graph container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getEdges().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof graphEditor.Edge) {
				continue;
			}
			graphEditor.Edge link = (graphEditor.Edge) linkObject;
			if (graphEditor.diagram.edit.parts.EdgeEditPart.VISUAL_ID != graphEditor.diagram.part.GraphEditorVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			graphEditor.Node dst = link.getTo();
			graphEditor.Node src = link.getFrom();
			result
					.add(new graphEditor.diagram.part.GraphEditorLinkDescriptor(
							src,
							dst,
							link,
							graphEditor.diagram.providers.GraphEditorElementTypes.Edge_4001,
							graphEditor.diagram.edit.parts.EdgeEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Message_4002(
			graphEditor.Graph container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getMessages().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof graphEditor.Message) {
				continue;
			}
			graphEditor.Message link = (graphEditor.Message) linkObject;
			if (graphEditor.diagram.edit.parts.MessageEditPart.VISUAL_ID != graphEditor.diagram.part.GraphEditorVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			graphEditor.Node dst = link.getTo();
			graphEditor.Node src = link.getFrom();
			result
					.add(new graphEditor.diagram.part.GraphEditorLinkDescriptor(
							src,
							dst,
							link,
							graphEditor.diagram.providers.GraphEditorElementTypes.Message_4002,
							graphEditor.diagram.edit.parts.MessageEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Edge_4001(
			graphEditor.Node target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != graphEditor.GraphEditorPackage.eINSTANCE
					.getEdge_To()
					|| false == setting.getEObject() instanceof graphEditor.Edge) {
				continue;
			}
			graphEditor.Edge link = (graphEditor.Edge) setting.getEObject();
			if (graphEditor.diagram.edit.parts.EdgeEditPart.VISUAL_ID != graphEditor.diagram.part.GraphEditorVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			graphEditor.Node src = link.getFrom();
			result
					.add(new graphEditor.diagram.part.GraphEditorLinkDescriptor(
							src,
							target,
							link,
							graphEditor.diagram.providers.GraphEditorElementTypes.Edge_4001,
							graphEditor.diagram.edit.parts.EdgeEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Message_4002(
			graphEditor.Node target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != graphEditor.GraphEditorPackage.eINSTANCE
					.getMessage_To()
					|| false == setting.getEObject() instanceof graphEditor.Message) {
				continue;
			}
			graphEditor.Message link = (graphEditor.Message) setting
					.getEObject();
			if (graphEditor.diagram.edit.parts.MessageEditPart.VISUAL_ID != graphEditor.diagram.part.GraphEditorVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			graphEditor.Node src = link.getFrom();
			result
					.add(new graphEditor.diagram.part.GraphEditorLinkDescriptor(
							src,
							target,
							link,
							graphEditor.diagram.providers.GraphEditorElementTypes.Message_4002,
							graphEditor.diagram.edit.parts.MessageEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Edge_4001(
			graphEditor.Node source) {
		graphEditor.Graph container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof graphEditor.Graph) {
				container = (graphEditor.Graph) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getEdges().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof graphEditor.Edge) {
				continue;
			}
			graphEditor.Edge link = (graphEditor.Edge) linkObject;
			if (graphEditor.diagram.edit.parts.EdgeEditPart.VISUAL_ID != graphEditor.diagram.part.GraphEditorVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			graphEditor.Node dst = link.getTo();
			graphEditor.Node src = link.getFrom();
			if (src != source) {
				continue;
			}
			result
					.add(new graphEditor.diagram.part.GraphEditorLinkDescriptor(
							src,
							dst,
							link,
							graphEditor.diagram.providers.GraphEditorElementTypes.Edge_4001,
							graphEditor.diagram.edit.parts.EdgeEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Message_4002(
			graphEditor.Node source) {
		graphEditor.Graph container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof graphEditor.Graph) {
				container = (graphEditor.Graph) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getMessages().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof graphEditor.Message) {
				continue;
			}
			graphEditor.Message link = (graphEditor.Message) linkObject;
			if (graphEditor.diagram.edit.parts.MessageEditPart.VISUAL_ID != graphEditor.diagram.part.GraphEditorVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			graphEditor.Node dst = link.getTo();
			graphEditor.Node src = link.getFrom();
			if (src != source) {
				continue;
			}
			result
					.add(new graphEditor.diagram.part.GraphEditorLinkDescriptor(
							src,
							dst,
							link,
							graphEditor.diagram.providers.GraphEditorElementTypes.Message_4002,
							graphEditor.diagram.edit.parts.MessageEditPart.VISUAL_ID));
		}
		return result;
	}

}
