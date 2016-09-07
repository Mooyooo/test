package mn.mo.test.ejb;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import mn.mo.test.ejb.exception.ErrorMessage;

public class Helper {

	public static Response getError(String message,int errorCode, Status st) {
		ErrorMessage errorMessage = new ErrorMessage(message, 500, "www.google.com");
		return Response.status(st).entity(errorMessage).build();
	}
}
