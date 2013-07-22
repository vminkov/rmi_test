import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;


public class MyTrustManager implements X509TrustManager {
    private static X509Certificate[] stataceCerts;
	public MyTrustManager() {
		 stataceCerts = new X509Certificate[1];
	        try {
	            CertificateFactory cf = CertificateFactory.getInstance("X.509");
	            stataceCerts[0] = (X509Certificate) cf.generateCertificate(new FileInputStream("slfcert.crt"));
	        }catch(CertificateException e) {
	            stataceCerts = new X509Certificate[] { null };
	        }catch(FileNotFoundException e) {
	            stataceCerts = new X509Certificate[] { null };
	        }
	}
	
	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
       return; //throw new UnsupportedOperationException("we don't care if the client trusts us");
	}

	@Override
	public void checkServerTrusted(X509Certificate[] xcs, String arg1)
			throws CertificateException {
		 boolean check = false;
	        for (int i = 0; i < xcs.length && !check; i++) {
	            X509Certificate x509Certificate = xcs[i];
	            System.out.println("certificate: " + x509Certificate.getIssuerX500Principal().getName());
	            for(int j = 0; j < stataceCerts.length && !check; j++) {
	                try {
	                    x509Certificate.verify(stataceCerts[j].getPublicKey());
//	                    stataceCerts[j].verify(x509Certificate.getPublicKey());
	                    check = true;
	                } catch (NoSuchAlgorithmException ex) {
	                    System.out.println("NoSuchAlgorithm: " + ex.getMessage());
	                } catch (InvalidKeyException ex) {
	                    System.out.println("InvalidKey: " + ex.getMessage());
	                } catch (NoSuchProviderException ex) {
	                    System.out.println("NoSuchProvider: " + ex.getMessage());
	                } catch (SignatureException ex) {
	                    System.out.println("SignatureException: " + ex.getMessage());
	                }
	            }
	        }
	        
	        if(!check)
	            throw new CertificateException("Untrusted certificate?");
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return stataceCerts;
	}

}
