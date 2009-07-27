package graphEditor.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

/**
 * @generated
 */
public class EdgeReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public EdgeReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof graphEditor.Edge) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof graphEditor.Node && newEnd instanceof graphEditor.Node)) {
			return false;
		}
		graphEditor.Node target = getLink().getChild();
		if (!(getLink().eContainer() instanceof graphEditor.Graph)) {
			return false;
		}
		graphEditor.Graph container = (graphEditor.Graph) getLink()
				.eContainer();
		return graphEditor.diagram.edit.policies.GraphEditorBaseItemSemanticEditPolicy.LinkConstraints
				.canExistEdge_4001(container, getNewSource(), target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof graphEditor.Node && newEnd instanceof graphEditor.Node)) {
			return false;
		}
		graphEditor.Node source = getLink().getParent();
		if (!(getLink().eContainer() instanceof graphEditor.Graph)) {
			return false;
		}
		graphEditor.Graph container = (graphEditor.Graph) getLink()
				.eContainer();
		return graphEditor.diagram.edit.policies.GraphEditorBaseItemSemanticEditPolicy.LinkConstraints
				.canExistEdge_4001(container, source, getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().setParent(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setChild(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected graphEditor.Edge getLink() {
		return (graphEditor.Edge) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected graphEditor.Node getOldSource() {
		return (graphEditor.Node) oldEnd;
	}

	/**
	 * @generated
	 */
	protected graphEditor.Node getNewSource() {
		return (graphEditor.Node) newEnd;
	}

	/**
	 * @generated
	 */
	protected graphEditor.Node getOldTarget() {
		return (graphEditor.Node) oldEnd;
	}

	/**
	 * @generated
	 */
	protected graphEditor.Node getNewTarget() {
		return (graphEditor.Node) newEnd;
	}
}
