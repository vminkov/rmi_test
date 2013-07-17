import java.io.IOException;
import java.net.ServerSocket;

import javax.rmi.ssl.SslRMIServerSocketFactory;


public class MySslRmiServerSocketFactory extends SslRMIServerSocketFactory {
	@Override
	public ServerSocket createServerSocket(int port) throws IOException {
		// TODO Auto-generated method stub
		return super.createServerSocket(port);
	}
}
