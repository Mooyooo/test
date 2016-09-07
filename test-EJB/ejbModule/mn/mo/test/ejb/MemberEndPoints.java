package mn.mo.test.ejb;

import java.util.Collection;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import mn.mo.test.ejb.exception.ErrorMessage;
import mn.mo.test.model.Member;

@Stateless
@Path("/members")
@Produces(value = { MediaType.APPLICATION_JSON })
@Consumes(value = { MediaType.APPLICATION_JSON })
public class MemberEndPoints implements MemberService {
	@PersistenceContext(unitName = "test-persistence")
	private EntityManager em;

	public Response create(Member member) {
		try {
			// TODO: process the given member
			// here we use Member#getId(), assuming that it provides the
			// identifier
			// to retrieve the created Member resource.
			System.out.println("ehelj bna shu ---------------------------------------------------------------");
			em.persist(member);
			em.flush();
		} catch (ConstraintViolationException  ex) {
			System.out.println("-------------Error SHUUU----------------"+ex.getCause().getMessage());
			throw new ClientErrorException(ex.getMessage(), Status.BAD_REQUEST);
		}
		return Response
				.created(UriBuilder.fromResource(MemberEndPoints.class).path(String.valueOf(member.getId())).build())
				.build();
	}

	public Response findById(@PathParam("id") final Long id) {
		// TODO: retrieve the member
		ErrorMessage errorMessage = new ErrorMessage("not found", 404, "www.google.com");
		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();
		Member member = null;
		member = em.find(Member.class, id);
		if (member == null) {
			throw new WebApplicationException(response);
		}
		return Response.ok(member).build();
	}

	public Collection<Member> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		// TODO: retrieve the members
		Collection<Member> members = em.createQuery("FROM Member", Member.class).getResultList();
		return members;
	}

	public Response update(@PathParam("id") Long id, final Member member) {
		// TODO: process the given member
		if (member == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (em.find(Member.class, id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.merge(member);
		return Response.noContent().build();
	}

	public Response deleteById(@PathParam("id") final Long id) {
		// TODO: process the member matching by the given id
		Member member = null;
		member = em.find(Member.class, id);
		if (member == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.remove(member);
		return Response.noContent().build();
	}

}
