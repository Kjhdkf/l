package soapserver;

import javax.xml.ws.Endpoint;

public class Publisher {
	public static void main(String[] args) {
		Endpoint.publish("http://172.31.1.10:3001/ws/users", new UserControllerImpl());
	}
}