package org.sahil.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sahil.messenger.exception.DataNotFoundException;
import org.sahil.messenger.model.Message;
import org.sahil.messenger.resource.beans.MessageResourceBean;
import org.sahil.messenger.service.MessageService;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class MessageResource {

	MessageService service = new MessageService();
	
	@GET	
	public List<Message> getMessages(@BeanParam MessageResourceBean bean){
		if(bean.getYear() > 0 ) return service.getAllMessagesForYears(bean.getYear());
		else if(bean.getSize() > 0 && bean.getStart() >= 0) return service.getAllMessagesPaginated(bean.getStart(), bean.getSize());
		return service.getAllMessages();
	}
	
	@POST	
	public Response addMessage(Message message, @Context UriInfo uriInfo){
		Message newMessage = service.addMessage(message);
		String id = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.created(uri).entity(newMessage).build();
	}
	
	@PUT
	@Path("/{messageId}")	
	public Message updateMessage(@PathParam("messageId") long id, Message message){
		message.setId(id);
		return service.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")	
	public void deleteMessage(@PathParam("messageId") long id){
		service.removeMessage(id);
	}
	
	@GET	
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo){		
		Message message = service.getMessage(id);
		if (message == null) throw new DataNotFoundException("Message with id " + id + " not found");			
		
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForComments(uriInfo, message), "comments");
		return message;
	}

	private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.resolveTemplate("messageId", message.getId())
				.path(CommentResource.class)
				.build()
				.toString();
		return uri;
	}

	public String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder().path(MessageResource.class)
				 .path(Long.toString(message.getId())).build().toString();
		return uri;
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
}
