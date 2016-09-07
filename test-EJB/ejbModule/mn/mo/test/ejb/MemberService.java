package mn.mo.test.ejb;

import java.util.Collection;

import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mn.mo.test.model.Member;

@Local
@Path("/members")
@Produces(value={MediaType.APPLICATION_JSON })
@Consumes(value={MediaType.APPLICATION_JSON })
public interface MemberService {

	@POST
	public Response create(Member member);

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id);

	@GET
	public Collection<Member> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult);

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final Member member);

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id);
}
