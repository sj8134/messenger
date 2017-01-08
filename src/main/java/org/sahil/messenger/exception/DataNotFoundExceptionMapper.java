package org.sahil.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.sahil.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException exception) {		
		ErrorMessage message = new ErrorMessage("Not Found", 404, "http://google.com");
		return Response.status(Status.NOT_FOUND).entity(message).build();
	}

}
