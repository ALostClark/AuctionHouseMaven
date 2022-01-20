package local.clark.tools;

import net.jini.core.discovery.LookupLocator;
import net.jini.core.entry.Entry;
import net.jini.core.lookup.ServiceRegistrar;
import net.jini.core.lookup.ServiceTemplate;
import net.jini.core.transaction.server.TransactionManager;
import net.jini.space.JavaSpace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpaceTools {

	private static final String HOSTNAME = "localhost";


	public static JavaSpace getSpace(String hostname) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		JavaSpace js = null;
		try {
			LookupLocator l = new LookupLocator("jini://" + hostname);

			ServiceRegistrar sr = l.getRegistrar();

			Class c = Class.forName("net.jini.space.JavaSpace");
			Class[] classTemplate = {c};

			js = (JavaSpace) sr.lookup(new ServiceTemplate(null, classTemplate, null));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return js;
	}

	public static JavaSpace getSpace() {
		return getSpace(HOSTNAME);
	}


	public static TransactionManager getManager(String hostname) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		TransactionManager tm = null;
		try {
			LookupLocator l = new LookupLocator("jini://" + hostname);

			ServiceRegistrar sr = l.getRegistrar();

			Class c = Class.forName("net.jini.core.transaction.server.TransactionManager");
			Class[] classTemplate = {c};

			tm = (TransactionManager) sr.lookup(new ServiceTemplate(null, classTemplate, null));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tm;
	}


    public static <T extends Entry> List<T> toList(Collection<Entry> collection) {
        List<T> results = new ArrayList<>();
        for (Entry entry : collection) { results.add((T) entry); }
        return results;
    }

	public static TransactionManager getManager() {
		return getManager(HOSTNAME);
	}
}

