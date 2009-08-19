package graphEditor.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class GraphEditorViewProvider extends AbstractViewProvider {

	/**
	 * @generated
	 */
	protected Class getDiagramViewClass(IAdaptable semanticAdapter,
			String diagramKind) {
		EObject semanticElement = getSemanticElement(semanticAdapter);
		if (graphEditor.diagram.edit.parts.GraphEditPart.MODEL_ID
				.equals(diagramKind)
				&& graphEditor.diagram.part.GraphEditorVisualIDRegistry
						.getDiagramVisualID(semanticElement) != -1) {
			return graphEditor.diagram.view.factories.GraphViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getNodeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		if (containerView == null) {
			return null;
		}
		IElementType elementType = getSemanticElementType(semanticAdapter);
		EObject domainElement = getSemanticElement(semanticAdapter);
		int visualID;
		if (semanticHint == null) {
			// Semantic hint is not specified. Can be a result of call from CanonicalEditPolicy.
			// In this situation there should be NO elementType, visualID will be determined
			// by VisualIDRegistry.getNodeVisualID() for domainElement.
			if (elementType != null || domainElement == null) {
				return null;
			}
			visualID = graphEditor.diagram.part.GraphEditorVisualIDRegistry
					.getNodeVisualID(containerView, domainElement);
		} else {
			visualID = graphEditor.diagram.part.GraphEditorVisualIDRegistry
					.getVisualID(semanticHint);
			if (elementType != null) {
				// Semantic hint is specified together with element type.
				// Both parameters should describe exactly the same diagram element.
				// In addition we check that visualID returned by VisualIDRegistry.getNodeVisualID() for
				// domainElement (if specified) is the same as in element type.
				if (!graphEditor.diagram.providers.GraphEditorElementTypes
						.isKnownElementType(elementType)
						|| (!(elementType instanceof IHintedType))) {
					return null; // foreign element type
				}
				String elementTypeHint = ((IHintedType) elementType)
						.getSemanticHint();
				if (!semanticHint.equals(elementTypeHint)) {
					return null; // if semantic hint is specified it should be the same as in element type
				}
				if (domainElement != null
						&& visualID != graphEditor.diagram.part.GraphEditorVisualIDRegistry
								.getNodeVisualID(containerView, domainElement)) {
					return null; // visual id for node EClass should match visual id from element type
				}
			} else {
				// Element type is not specified. Domain element should be present (except pure design elements).
				// This method is called with EObjectAdapter as parameter from:
				//   - ViewService.createNode(View container, EObject eObject, String type, PreferencesHint preferencesHint) 
				//   - generated ViewFactory.decorateView() for parent element
				if (!graphEditor.diagram.edit.parts.GraphEditPart.MODEL_ID
						.equals(graphEditor.diagram.part.GraphEditorVisualIDRegistry
								.getModelID(containerView))) {
					return null; // foreign diagram
				}
				switch (visualID) {
				case graphEditor.diagram.edit.parts.FactornodeEditPart.VISUAL_ID:
				case graphEditor.diagram.edit.parts.VariablenodeEditPart.VISUAL_ID:
					if (domainElement == null
							|| visualID != graphEditor.diagram.part.GraphEditorVisualIDRegistry
									.getNodeVisualID(containerView,
											domainElement)) {
						return null; // visual id in semantic hint should match visual id for domain element
					}
					break;
				case graphEditor.diagram.edit.parts.VariablenodeNameEditPart.VISUAL_ID:
					if (graphEditor.diagram.edit.parts.FactornodeEditPart.VISUAL_ID != graphEditor.diagram.part.GraphEditorVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case graphEditor.diagram.edit.parts.FactornodeNameEditPart.VISUAL_ID:
					if (graphEditor.diagram.edit.parts.VariablenodeEditPart.VISUAL_ID != graphEditor.diagram.part.GraphEditorVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case graphEditor.diagram.edit.parts.WrappingLabel3EditPart.VISUAL_ID:
					if (graphEditor.diagram.edit.parts.MessageEditPart.VISUAL_ID != graphEditor.diagram.part.GraphEditorVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				default:
					return null;
				}
			}
		}
		return getNodeViewClass(containerView, visualID);
	}

	/**
	 * @generated
	 */
	protected Class getNodeViewClass(View containerView, int visualID) {
		if (containerView == null
				|| !graphEditor.diagram.part.GraphEditorVisualIDRegistry
						.canCreateNode(containerView, visualID)) {
			return null;
		}
		switch (visualID) {
		case graphEditor.diagram.edit.parts.FactornodeEditPart.VISUAL_ID:
			return graphEditor.diagram.view.factories.FactornodeViewFactory.class;
		case graphEditor.diagram.edit.parts.VariablenodeNameEditPart.VISUAL_ID:
			return graphEditor.diagram.view.factories.VariablenodeNameViewFactory.class;
		case graphEditor.diagram.edit.parts.VariablenodeEditPart.VISUAL_ID:
			return graphEditor.diagram.view.factories.VariablenodeViewFactory.class;
		case graphEditor.diagram.edit.parts.FactornodeNameEditPart.VISUAL_ID:
			return graphEditor.diagram.view.factories.FactornodeNameViewFactory.class;
		case graphEditor.diagram.edit.parts.WrappingLabel3EditPart.VISUAL_ID:
			return graphEditor.diagram.view.factories.WrappingLabel3ViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getEdgeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		IElementType elementType = getSemanticElementType(semanticAdapter);
		if (!graphEditor.diagram.providers.GraphEditorElementTypes
				.isKnownElementType(elementType)
				|| (!(elementType instanceof IHintedType))) {
			return null; // foreign element type
		}
		String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
		if (elementTypeHint == null) {
			return null; // our hint is visual id and must be specified
		}
		if (semanticHint != null && !semanticHint.equals(elementTypeHint)) {
			return null; // if semantic hint is specified it should be the same as in element type
		}
		int visualID = graphEditor.diagram.part.GraphEditorVisualIDRegistry
				.getVisualID(elementTypeHint);
		EObject domainElement = getSemanticElement(semanticAdapter);
		if (domainElement != null
				&& visualID != graphEditor.diagram.part.GraphEditorVisualIDRegistry
						.getLinkWithClassVisualID(domainElement)) {
			return null; // visual id for link EClass should match visual id from element type
		}
		return getEdgeViewClass(visualID);
	}

	/**
	 * @generated
	 */
	protected Class getEdgeViewClass(int visualID) {
		switch (visualID) {
		case graphEditor.diagram.edit.parts.EdgeEditPart.VISUAL_ID:
			return graphEditor.diagram.view.factories.EdgeViewFactory.class;
		case graphEditor.diagram.edit.parts.MessageEditPart.VISUAL_ID:
			return graphEditor.diagram.view.factories.MessageViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	private IElementType getSemanticElementType(IAdaptable semanticAdapter) {
		if (semanticAdapter == null) {
			return null;
		}
		return (IElementType) semanticAdapter.getAdapter(IElementType.class);
	}
}
