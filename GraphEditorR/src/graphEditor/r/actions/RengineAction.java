package graphEditor.r.actions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.jface.dialogs.MessageDialog;

import de.walware.rj.data.RObject;
import de.walware.statet.nico.core.runtime.IToolRunnable;
import de.walware.statet.nico.core.runtime.IToolRunnableControllerAdapter;
import de.walware.statet.nico.core.runtime.SubmitType;
import de.walware.statet.nico.core.runtime.ToolController;
import de.walware.statet.nico.core.runtime.ToolProcess;
import de.walware.statet.nico.ui.NicoUI;
import de.walware.statet.nico.ui.NicoUITools;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class RengineAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	/**
	 * The constructor.
	 */
	public RengineAction() {
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		MessageDialog.openInformation(
			window.getShell(),
			"GraphEditorR Plug-in",
			"Start R Engine");
		//--------------------------------------------------------------------	
		
/*		ToolProcess process = NicoUI.getToolRegistry().getActiveToolSession(page).getProcess();
		ToolController controller = NicoUITools.accessController("R", RTool.R_DATA_FEATURESET_ID, process);
		controller.submit(new IToolRunnable() {
		       public String getLabel() {
		               return "Special Analysis";
		       }
		       public SubmitType getSubmitType() {
		               return SubmitType.TOOLS;
		       }
		       public String getTypeId() {
		               return "my.special.analysis";
		       }
		       public void changed(int event, ToolProcess process) {
		       }
		       public void run(IToolRunnableControllerAdapter adapter, IProgressMonitor monitor)
		                       throws InterruptedException, CoreException {
		               IRDataAdapter r = (IRDataAdapter) adapter;
		               
		               // in console
		               r.submitToConsole("my.r.command()", monitor);
		                       
		               // or invisible
		               r.evalVoid("my.r.command()", monitor);
		                       
		               // with R data return value
		               RObject data = r.evalData("my.r.command()", monitor);
		       }
			@Override
			public void changed(int event) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void run(IToolRunnableControllerAdapter tools,
					IProgressMonitor monitor) throws InterruptedException,
					CoreException {
				// TODO Auto-generated method stub
				
			}
		});*/

		//--------------------------------------------------------------------	
			
	}

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}