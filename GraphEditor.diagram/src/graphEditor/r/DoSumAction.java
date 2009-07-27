package graphEditor.r;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;


public class DoSumAction implements IObjectActionDelegate {

	/**
	 * @generated
	 */
	private IWorkbenchPart targetPart;
	private graphEditor.diagram.edit.parts.GraphEditPart mySelectedElement;
	private Shell myShell;


	public DoSumAction() {
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	@Override
	public void run(IAction action) {
		if (mySelectedElement!=null)
				mySelectedElement.getEditingDomain();
		System.out.println("asdfasdf");

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		
		mySelectedElement = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1
					&& structuredSelection.getFirstElement() instanceof graphEditor.diagram.edit.parts.GraphEditPart) {
				mySelectedElement = (graphEditor.diagram.edit.parts.GraphEditPart) structuredSelection
						.getFirstElement();
			}
		}
		action.setEnabled(isEnabled());

	}
	private boolean isEnabled() {
		return mySelectedElement != null;
	}


}
