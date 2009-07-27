package graphEditor.diagram.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * @generated
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = getPreferenceStore();
		graphEditor.diagram.preferences.DiagramPrintingPreferencePage
				.initDefaults(store);
		graphEditor.diagram.preferences.DiagramGeneralPreferencePage
				.initDefaults(store);
		graphEditor.diagram.preferences.DiagramAppearancePreferencePage
				.initDefaults(store);
		graphEditor.diagram.preferences.DiagramConnectionsPreferencePage
				.initDefaults(store);
		graphEditor.diagram.preferences.DiagramRulersAndGridPreferencePage
				.initDefaults(store);
	}

	/**
	 * @generated
	 */
	protected IPreferenceStore getPreferenceStore() {
		return graphEditor.diagram.part.GraphEditorDiagramEditorPlugin
				.getInstance().getPreferenceStore();
	}
}
