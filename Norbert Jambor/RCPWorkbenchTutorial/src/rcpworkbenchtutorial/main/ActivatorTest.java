package rcpworkbenchtutorial.main;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

   //The activator class controls the plug-in life cycle
public class ActivatorTest extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "RCPWorkbenchTutorial"; //$NON-NLS-1$

	// The shared instance
	private static ActivatorTest plugin;
	
	//The constructor
	public ActivatorTest() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	//Returns the shared instance
	public static ActivatorTest getDefault() {
		return plugin;
	}

}
