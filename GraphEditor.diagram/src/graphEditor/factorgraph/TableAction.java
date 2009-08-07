package graphEditor.factorgraph;

import graphEditor.Factornode;
import graphEditor.Graph;
import graphEditor.Node;
import graphEditor.Variablenode;
import graphEditor.diagram.edit.parts.GraphEditPart;
import graphEditor.diagram.edit.parts.VariablenodeEditPart;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class TableAction implements IObjectActionDelegate {

	/**
	 * @generated
	 */
	private IWorkbenchPart targetPart;
	private ShapeNodeEditPart mySelectedElement;

	public TableAction() {
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		mySelectedElement = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1
					&& structuredSelection.getFirstElement() instanceof graphEditor.diagram.edit.parts.FactornodeEditPart
					) {
				mySelectedElement = (graphEditor.diagram.edit.parts.FactornodeEditPart) structuredSelection
						.getFirstElement();
			}if (structuredSelection.size() == 1
					&& structuredSelection.getFirstElement() instanceof graphEditor.diagram.edit.parts.VariablenodeEditPart
			) {
		mySelectedElement = (graphEditor.diagram.edit.parts.VariablenodeEditPart) structuredSelection
				.getFirstElement();
	}
		}
		action.setEnabled(isEnabled());
	}

	private boolean isEnabled() {
		return mySelectedElement != null;
	}

	@Override
	public void run(IAction action) {

		// ---------------------------
		Runnable getGraph = new Runnable() {
			Graph graph = null;

			public Graph getGraph() {
				return this.graph;
			}

			public void run() {
				TransactionalEditingDomain domain = null;

				if (mySelectedElement != null)
					domain = mySelectedElement.getEditingDomain();
			//	mySelectedElement.
				Graph graph = null;
				final Resource r = domain.getResourceSet().getResources()
						.get(0);

				try {
					graph = (Graph) domain
							.runExclusive(new RunnableWithResult.Impl() {
								public void run() {
									setResult(((Graph) r.getContents().get(0)));
								}
							});

					System.out.println(graph);
					
				LinkedList<de.lmu.genzentrum.tresch.Message> messages=new LinkedList<de.lmu.genzentrum.tresch.Message>();
					
					List<Node> nodes = graph.getNodes();

					for (Node n : nodes) {
						System.out.println(n);
						if (n instanceof Variablenode) {
							
						}

						if (n instanceof Factornode) {
						}
						
					}
					
				//	de.lmu.genzentrum.tresch.Graph nGraph=new de.lmu.genzentrum.tresch.Graph(messages);

				} catch (InterruptedException e) {
					// Handle the interrupted exception in an graceful way ...
				}

			}
		};

		Thread t1 = new Thread(getGraph);
		t1.start();
	}

}
