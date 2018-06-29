package soapserver;

import javax.xml.ws.Endpoint;

public class Publisher {
	public static void main(String[] args) {
		Endpoint.publish("54.175.59.200:3001/ws/users", new UserControllerImpl());
	}
}