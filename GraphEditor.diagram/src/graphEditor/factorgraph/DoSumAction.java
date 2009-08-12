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
		final Resource r = domain.getResourceSet().getResources().get(0);

		try {
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
					Double[] oa=(Double[])values.toArray();
					double[] arrayvalues=new double[oa.length];
					for (int i=0;i<oa.length;i++){
						arrayvalues[i]=oa[i].doubleValue();
					}					
					from = new de.lmu.genzentrum.tresch.VariableNode(nodeFrom.getName(),(int)nodeFrom.getId(),arrayvalues);
					System.out.println(nodeFrom.getName()+"-"+(int)nodeFrom.getId()+"-"+Arrays.toString(arrayvalues));
				}
				if (nodeFrom instanceof Factornode) {
					double[][] values=((Factornode) nodeFrom).getValues();
					from = new de.lmu.genzentrum.tresch.FactorNode(nodeFrom.getName(),(int)nodeFrom.getId(),values);
					System.out.println(nodeFrom.getName()+"-"+(int)nodeFrom.getId()+"-"+Arrays.toString(values));
				}
				
				Node nodeTo = m.getTo();
				de.lmu.genzentrum.tresch.Node to=null;
				if (nodeTo instanceof Variablenode) {
					List values=((Variablenode) nodeTo).getValues();
					Double[] oa=(Double[])values.toArray();
					double[] arrayvalues=new double[oa.length];
					for (int i=0;i<oa.length;i++){
						arrayvalues[i]=oa[i].doubleValue();
					}					
					to = new de.lmu.genzentrum.tresch.VariableNode(nodeTo.getName(),(int)nodeTo.getId(),arrayvalues);
					System.out.println(nodeTo.getName()+"-"+(int)nodeTo.getId()+"-"+Arrays.toString(arrayvalues));
				}
				if (nodeTo instanceof Factornode) {
					double[][] values=((Factornode) nodeTo).getValues();
					to = new de.lmu.genzentrum.tresch.FactorNode(nodeTo.getName(),(int)nodeTo.getId(),values);
					System.out.println(nodeTo.getName()+"-"+(int)nodeTo.getId()+"-"+Arrays.toString(values));
				}

				de.lmu.genzentrum.tresch.Message message = new de.lmu.genzentrum.tresch.Message(from, to);
				messages.add(message);
			}

			de.lmu.genzentrum.tresch.Graph factorgraph = new de.lmu.genzentrum.tresch.Graph(messages);
			SumProduct sum = new SumProduct();
		//	sum.doSum(factorgraph);
			factorgraph.print();

		} catch (InterruptedException e) {
		} /*catch (NoValueException e) {
			e.printStackTrace();
		}*/
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
