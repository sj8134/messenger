package org.sahil.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.sahil.messenger.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable exception) {
		ErrorMessage message = new ErrorMessage("Internal Error", 500, "http://google.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(message).build();
	}

}
