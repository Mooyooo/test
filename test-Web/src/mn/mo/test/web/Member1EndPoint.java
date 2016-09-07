package mn.mo.test.web;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import mn.mo.test.model.Member;

@RequestScoped
@Path("/members1")
@Produces("application/json")
@Consumes("application/json")
public class Member1EndPoint {

	@POST
	public Response create(final Member member) {
		// TODO: process the given member
		// here we use Member#getId(), assuming that it provides the identifier
		// to retrieve the created Member resource.
		return Response
				.created(UriBuilder.fromResource(Member1EndPoint.class).path(String.valueOf(member.getId())).build())
				.build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		// TODO: retrieve the member
		Member member = null;
		if (member == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(member).build();
	}

	@GET
	public String listAll() {
		// TODO: retrieve the members
		final List<Member> members = null;
		return "Hello done";
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final Member member) {
		// TODO: process the given member
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		// TODO: process the member matching by the given id
		return Response.noContent().build();
	}

}
