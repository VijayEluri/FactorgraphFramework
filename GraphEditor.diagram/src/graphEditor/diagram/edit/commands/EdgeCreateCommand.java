package graphEditor.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;

/**
 * @generated
 */
public class EdgeCreateCommand extends CreateElementCommand {

	/**
	 * @generated
	 */
	private final EObject source;

	/**
	 * @generated
	 */
	private final EObject target;

	/**
	 * @generated
	 */
	private graphEditor.Graph container;

	/**
	 * @generated
	 */
	public EdgeCreateCommand(CreateRelationshipRequest request, EObject source,
			EObject target) {
		super(request);
		this.source = source;
		this.target = target;
		if (request.getContainmentFeature() == null) {
			setContainmentFeature(graphEditor.GraphEditorPackage.eINSTANCE
					.getGraph_Connections());
		}

		// Find container element for the new link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null; element = element
				.eContainer()) {
			if (element instanceof graphEditor.Graph) {
				container = (graphEditor.Graph) element;
				super.setElementToEdit(container);
				break;
			}
		}
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (source == null && target == null) {
			return false;
		}
		if (source != null && false == source instanceof graphEditor.Node) {
			return false;
		}
		if (target != null && false == target instanceof graphEditor.Node) {
			return false;
		}
		if (getSource() == null) {
			return true; // link creation is in progress; source is not defined yet
		}
		// target may be null here but it's possible to check constraint
		if (getContainer() == null) {
			return false;
		}
		return graphEditor.diagram.edit.policies.GraphEditorBaseItemSemanticEditPolicy.LinkConstraints
				.canCreateEdge_4001(getContainer(), getSource(), getTarget());
	}

	/**
	 * @generated
	 */
	protected EObject doDefaultElementCreation() {
		graphEditor.Edge newElement = graphEditor.GraphEditorFactory.eINSTANCE
				.createEdge();
		getContainer().getConnections().add(newElement);
		newElement.setParent(getSource());
		newElement.setChild(getTarget());
		return newElement;
	}

	/**
	 * @generated
	 */
	protected EClass getEClassToEdit() {
		return graphEditor.GraphEditorPackage.eINSTANCE.getGraph();
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in create link command"); //$NON-NLS-1$
		}
		return super.doExecuteWithResult(monitor, info);
	}

	/**
	 * @generated
	 */
	protected ConfigureRequest createConfigureRequest() {
		ConfigureRequest request = super.createConfigureRequest();
		request.setParameter(CreateRelationshipRequest.SOURCE, getSource());
		request.setParameter(CreateRelationshipRequest.TARGET, getTarget());
		return request;
	}

	/**
	 * @generated
	 */
	protected void setElementToEdit(EObject element) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @generated
	 */
	protected graphEditor.Node getSource() {
		return (graphEditor.Node) source;
	}

	/**
	 * @generated
	 */
	protected graphEditor.Node getTarget() {
		return (graphEditor.Node) target;
	}

	/**
	 * @generated
	 */
	public graphEditor.Graph getContainer() {
		return container;
	}
}
