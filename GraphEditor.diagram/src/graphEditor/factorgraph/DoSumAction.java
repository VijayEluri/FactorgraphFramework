package graphEditor.factorgraph;

import graphEditor.Factornode;
import graphEditor.Graph;
import graphEditor.Message;
import graphEditor.Node;
import graphEditor.Variablenode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import de.lmu.genzentrum.tresch.NoValueException;
import de.lmu.genzentrum.tresch.SumProduct;
import de.lmu.genzentrum.tresch.VariableNode;

public class DoSumAction implements IObjectActionDelegate {

	/**
	 * @generated
	 */
	private IWorkbenchPart targetPart;
	private static graphEditor.diagram.edit.parts.GraphEditPart mySelectedElement;

	public DoSumAction() {
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

	public static void convertEditDomainToFactorgraph() {
		TransactionalEditingDomain domain = null;
		if (mySelectedElement != null)
			domain = mySelectedElement.getEditingDomain();

		graphEditor.Graph graph = null;

		try {
			final Resource r = domain.getResourceSet().getResources().get(0);
			graph = (graphEditor.Graph) domain
					.runExclusive(new RunnableWithResult.Impl() {
						public void run() {
							setResult(((graphEditor.Graph) r.getContents().get(
									0)));

						}
					});
			LinkedList<de.lmu.genzentrum.tresch.Message> messages = new LinkedList<de.lmu.genzentrum.tresch.Message>();

			EList<Message> mesg = graph.getMessages();
			ECollections.sort(mesg);

			for (Message m : mesg) {
				Node nodeFrom = m.getFrom();
				de.lmu.genzentrum.tresch.Node from=null;
				
				if (nodeFrom instanceof Variablenode) {
					List values=((Variablenode) nodeFrom).getValues();
					Double[] da=(Double[])values.toArray();
					double[] arrayvalues=new double[da.length];
					for (int i=0;i<da.length;i++){
						arrayvalues[i]=da[i].doubleValue();
					}	
					Object[] oa=new Object[da.length];
					for (int i=0;i<da.length;i++){
						oa[i]=da[i].doubleValue();
					}
					
					from = new de.lmu.genzentrum.tresch.VariableNode(nodeFrom.getName(),(int)nodeFrom.getId(),oa,false);
				}
				if (nodeFrom instanceof Factornode) {
					double[][] values=((Factornode) nodeFrom).getValues();
					Object[][] oa=new Object[values.length][values[0].length];
					for(int i=0;i<values.length;i++)
						for(int j=0;j<values[0].length;j++)
							oa[i][j]=values[i][j];
					from = new de.lmu.genzentrum.tresch.FactorNode(nodeFrom.getName(),(int)nodeFrom.getId(),oa);
				}
				
				Node nodeTo = m.getTo();
				de.lmu.genzentrum.tresch.Node to=null;
				if (nodeTo instanceof Variablenode) {
					List values=((Variablenode) nodeTo).getValues();
					Double[] da=(Double[])values.toArray();
					double[] arrayvalues=new double[da.length];
					for (int i=0;i<da.length;i++){
						arrayvalues[i]=da[i].doubleValue();
					}					
					Object[] oa=new Object[da.length];
					for (int i=0;i<da.length;i++){
						oa[i]=da[i].doubleValue();
					}
					to = new de.lmu.genzentrum.tresch.VariableNode(nodeTo.getName(),(int)nodeTo.getId(),oa,false);
				}
				if (nodeTo instanceof Factornode) {
					double[][] values=((Factornode) nodeTo).getValues();
					Object[][] oa=new Object[values.length][values[0].length];
					for(int i=0;i<values.length;i++)
						for(int j=0;j<values[0].length;j++)
							oa[i][j]=values[i][j];

					to = new de.lmu.genzentrum.tresch.FactorNode(nodeTo.getName(),(int)nodeTo.getId(),oa);
				}

				de.lmu.genzentrum.tresch.Message message = new de.lmu.genzentrum.tresch.Message(from, to);
				System.out.println("message: "+from.getName()+"->"+to.getName());
				messages.add(message);
			}

			de.lmu.genzentrum.tresch.Graph factorgraph = new de.lmu.genzentrum.tresch.Graph(messages);
			SumProduct sum = new SumProduct(factorgraph);
			System.out.println("do sum product");
			sum.doSum();
			factorgraph.print();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (NoValueException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run(IAction action) {

		// ---------------------------
		Runnable runGetGraph = new Runnable() {
			Graph graph = null;

			public Graph getGraph() {
				return this.graph;
			}

			public void run() {
				DoSumAction.convertEditDomainToFactorgraph();

			}
		};

		Thread t1 = new Thread(runGetGraph);
		t1.start();
	}

}
