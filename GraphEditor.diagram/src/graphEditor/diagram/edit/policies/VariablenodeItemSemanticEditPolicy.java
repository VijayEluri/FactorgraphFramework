package graphEditor.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class VariablenodeItemSemanticEditPolicy extends
		graphEditor.diagram.edit.policies.GraphEditorBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		CompoundCommand cc = getDestroyEdgesCommand();
		addDestroyShortcutsCommand(cc);
		View view = (View) getHost().getModel();
		if (view.getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
			req.setElementToDestroy(view);
		}
		cc.add(getGEFWrapper(new DestroyElementCommand(req)));
		return cc.unwrap();
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super
				.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (graphEditor.diagram.providers.GraphEditorElementTypes.Edge_4001 == req
				.getElementType()) {
			return getGEFWrapper(new graphEditor.diagram.edit.commands.EdgeCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (graphEditor.diagram.providers.GraphEditorElementTypes.Message_4002 == req
				.getElementType()) {
			return getGEFWrapper(new graphEditor.diagram.edit.commands.MessageCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (graphEditor.diagram.providers.GraphEditorElementTypes.Edge_4001 == req
				.getElementType()) {
			return getGEFWrapper(new graphEditor.diagram.edit.commands.EdgeCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (graphEditor.diagram.providers.GraphEditorElementTypes.Message_4002 == req
				.getElementType()) {
			return getGEFWrapper(new graphEditor.diagram.edit.commands.MessageCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case graphEditor.diagram.edit.parts.EdgeEditPart.VISUAL_ID:
			return getGEFWrapper(new graphEditor.diagram.edit.commands.EdgeReorientCommand(
					req));
		case graphEditor.diagram.edit.parts.MessageEditPart.VISUAL_ID:
			return getGEFWrapper(new graphEditor.diagram.edit.commands.MessageReorientCommand(
					req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}
