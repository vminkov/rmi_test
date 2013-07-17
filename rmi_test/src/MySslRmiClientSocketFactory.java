import java.io.IOException;
import java.net.Socket;

import javax.rmi.ssl.SslRMIClientSocketFactory;


public class MySslRmiClientSocketFactory extends SslRMIClientSocketFactory {
	@Override
	public Socket createSocket(String host, int port) throws IOException {
		// TODO Auto-generated method stub
		Socket s = super.createSocket(host, port);
		return s;
	}
}
