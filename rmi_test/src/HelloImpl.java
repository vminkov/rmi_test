/*
 * @(#)HelloImpl.java	1.3 01/05/10
 *
 * Copyright 1994-2004 Sun Microsystems, Inc. All Rights Reserved. 
 *
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met: 
 * 
 * -Redistribution of source code must retain the above copyright 
 * notice, this list of conditions and the following disclaimer.
 * 
 * Redistribution in binary form must reproduce the above copyright 
 * notice, this list of conditions and the following disclaimer in 
 * the documentation and/or other materials provided with the 
 * distribution. 
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of 
 * contributors may be used to endorse or promote products derived 
 * from this software without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any 
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND 
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY 
 * EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN") AND ITS LICENSORS SHALL 
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT 
 * OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR 
 * ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, 
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER 
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF 
 * THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS 
 * BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. 
 * 
 * You acknowledge that this software is not designed, licensed or 
 * intended for use in the design, construction, operation or 
 * maintenance of any nuclear facility. 
 */

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject {

	private static final int PORT = 2019;

	public HelloImpl() throws Exception {
		super(PORT, new RMISSLClientSocketFactory(),
				new RMISSLServerSocketFactory());
	}

	public static void main(String args[]) {
		//System.setProperty("javax.net.debug", "all");


//		private SslContextFactory getSslContextFactory(final String jks) {
//
//			final InputStream keystoreFile = getClass().getClassLoader()
//					.getResourceAsStream(jks);
//
//			KeyStore keyStore = null;
//			try {
//				keyStore = KeyStore.getInstance("JKS", "SUN");
//				keyStore.load(keystoreFile, "Phase1server".toCharArray());
//			} catch (KeyStoreException e) {
//				e.printStackTrace();
//			} catch (NoSuchProviderException e) {
//				e.printStackTrace();
//			} catch (NoSuchAlgorithmException e) {
//				e.printStackTrace();
//			} catch (CertificateException e) {
//				e.printStackTrace();
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			final SslContextFactory sslContextFactory = new SslContextFactory();
//			sslContextFactory.setKeyStore(keyStore);
//			sslContextFactory.setKeyManagerPassword("Phase1server");
//			sslContextFactory.setKeyStorePassword("Phase1server");
//			sslContextFactory.setTrustStore(keyStore);
//			sslContextFactory.setTrustStorePassword("Phase1server");
//
//			return sslContextFactory;
//		}

		// Create and install a security manager
//		if (System.getSecurityManager() == null) {
//			System.setSecurityManager(new RMISecurityManager());
//		}
		try {
			// Create SSL-based registry
			Registry registry = LocateRegistry.createRegistry(PORT,
					new RMISSLClientSocketFactory(),
					new RMISSLServerSocketFactory());

			HelloMessage obj = new HelloMessage();

			// Bind this object instance to the name "HelloServer"
			registry.bind("HelloServer", obj);
			registry.bind("HelloNothing", new HelloImpl());
			
			System.out.println("HelloServer bound in registry");
			
		} catch (Exception e) {
			System.out.println("HelloImpl err: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

class HelloMessage implements Hello, Serializable{

	public String sayHello() {
		return "Hello World!";
	}

}
