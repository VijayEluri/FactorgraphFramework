package graphEditor.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class GraphEditorParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser variablenodeName_5001Parser;

	/**
	 * @generated
	 */
	private IParser getVariablenodeName_5001Parser() {
		if (variablenodeName_5001Parser == null) {
			variablenodeName_5001Parser = createVariablenodeName_5001Parser();
		}
		return variablenodeName_5001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createVariablenodeName_5001Parser() {
		EAttribute[] features = new EAttribute[] { graphEditor.GraphEditorPackage.eINSTANCE
				.getNode_Name(), };
		graphEditor.diagram.parsers.MessageFormatParser parser = new graphEditor.diagram.parsers.MessageFormatParser(
				features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser factornodeName_5002Parser;

	/**
	 * @generated
	 */
	private IParser getFactornodeName_5002Parser() {
		if (factornodeName_5002Parser == null) {
			factornodeName_5002Parser = createFactornodeName_5002Parser();
		}
		return factornodeName_5002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createFactornodeName_5002Parser() {
		EAttribute[] features = new EAttribute[] { graphEditor.GraphEditorPackage.eINSTANCE
				.getNode_Name(), };
		graphEditor.diagram.parsers.MessageFormatParser parser = new graphEditor.diagram.parsers.MessageFormatParser(
				features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser messageCount_6001Parser;

	/**
	 * @generated
	 */
	private IParser getMessageCount_6001Parser() {
		if (messageCount_6001Parser == null) {
			messageCount_6001Parser = createMessageCount_6001Parser();
		}
		return messageCount_6001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createMessageCount_6001Parser() {
		EAttribute[] features = new EAttribute[] { graphEditor.GraphEditorPackage.eINSTANCE
				.getMessage_Count(), };
		graphEditor.diagram.parsers.MessageFormatParser parser = new graphEditor.diagram.parsers.MessageFormatParser(
				features);
		return parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case graphEditor.diagram.edit.parts.VariablenodeNameEditPart.VISUAL_ID:
			return getVariablenodeName_5001Parser();
		case graphEditor.diagram.edit.parts.WrappingLabel2EditPart.VISUAL_ID:
			return getFactornodeName_5002Parser();
		case graphEditor.diagram.edit.parts.WrappingLabel3EditPart.VISUAL_ID:
			return getMessageCount_6001Parser();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(graphEditor.diagram.part.GraphEditorVisualIDRegistry
					.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(graphEditor.diagram.part.GraphEditorVisualIDRegistry
					.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (graphEditor.diagram.providers.GraphEditorElementTypes
					.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
