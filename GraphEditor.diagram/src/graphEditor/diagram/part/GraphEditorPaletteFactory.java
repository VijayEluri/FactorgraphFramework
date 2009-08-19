package graphEditor.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

/**
 * @generated
 */
public class GraphEditorPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createGraphEditor1Group());
	}

	/**
	 * Creates "graphEditor" palette tool group
	 * @generated
	 */
	private PaletteContainer createGraphEditor1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				graphEditor.diagram.part.Messages.GraphEditor1Group_title);
		paletteContainer.add(createVariablenode1CreationTool());
		paletteContainer.add(createFactornode2CreationTool());
		paletteContainer.add(new PaletteSeparator());
		paletteContainer.add(createEdge4CreationTool());
		paletteContainer.add(createMessage5CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createVariablenode1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(graphEditor.diagram.providers.GraphEditorElementTypes.Variablenode_2002);
		NodeToolEntry entry = new NodeToolEntry(
				graphEditor.diagram.part.Messages.Variablenode1CreationTool_title,
				graphEditor.diagram.part.Messages.Variablenode1CreationTool_desc,
				types);
		entry
				.setSmallIcon(graphEditor.diagram.providers.GraphEditorElementTypes
						.getImageDescriptor(graphEditor.diagram.providers.GraphEditorElementTypes.Variablenode_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createFactornode2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(graphEditor.diagram.providers.GraphEditorElementTypes.Factornode_2001);
		NodeToolEntry entry = new NodeToolEntry(
				graphEditor.diagram.part.Messages.Factornode2CreationTool_title,
				graphEditor.diagram.part.Messages.Factornode2CreationTool_desc,
				types);
		entry
				.setSmallIcon(graphEditor.diagram.providers.GraphEditorElementTypes
						.getImageDescriptor(graphEditor.diagram.providers.GraphEditorElementTypes.Factornode_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEdge4CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(graphEditor.diagram.providers.GraphEditorElementTypes.Edge_4001);
		LinkToolEntry entry = new LinkToolEntry(
				graphEditor.diagram.part.Messages.Edge4CreationTool_title,
				graphEditor.diagram.part.Messages.Edge4CreationTool_desc, types);
		entry
				.setSmallIcon(graphEditor.diagram.providers.GraphEditorElementTypes
						.getImageDescriptor(graphEditor.diagram.providers.GraphEditorElementTypes.Edge_4001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createMessage5CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(graphEditor.diagram.providers.GraphEditorElementTypes.Message_4002);
		LinkToolEntry entry = new LinkToolEntry(
				graphEditor.diagram.part.Messages.Message5CreationTool_title,
				graphEditor.diagram.part.Messages.Message5CreationTool_desc,
				types);
		entry
				.setSmallIcon(graphEditor.diagram.providers.GraphEditorElementTypes
						.getImageDescriptor(graphEditor.diagram.providers.GraphEditorElementTypes.Message_4002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
