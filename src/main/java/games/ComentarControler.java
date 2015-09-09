package games;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.tools.examples.data.UserLogin;
import org.jboss.tools.examples.model.User;

@Named
@Stateless
public class ComentarControler {

	@Inject
	private Comentars comentars;

	@Inject
	private EntityManager em;

	@Inject
	private UserLogin userLogin;

	private String koment;

	public String getKoment(String coment) {

		return koment;
	}

	public void setKoment(String koment) {
		this.koment = koment;
	}

	public List<Comentars> findAllOrderedByName(String gameName) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Comentars> criteria = cb.createQuery(Comentars.class);
		Root<Comentars> comentars = criteria.from(Comentars.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
		criteria.select(comentars).where(cb.equal(comentars.get("gameComentName"), gameName));
		return em.createQuery(criteria).getResultList();
	}

	public String nastavKoment(String koment, String gameComentName) {

		comentars.setComent(koment);
		comentars.setUserName(userLogin.getName());
		comentars.setGameComentName(gameComentName);
		em.persist(comentars);
		return "g1.jsf";

	}

	public String checkUser() {

		if (userLogin.getUser() != null) {
			return "false";
		} else {
			return "true";
		}
	}

	public String labelText() {

		if (userLogin.getUser() != null) {
			return "Put your coment here";
		} else {
			return "Please log in for coment";
		}
	}

}
