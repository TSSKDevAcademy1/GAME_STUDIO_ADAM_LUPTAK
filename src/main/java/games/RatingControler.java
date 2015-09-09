package games;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.tools.examples.model.User;

@Named
@Stateless
public class RatingControler {

	@Inject
	private Rating rating;

	@Inject
	private EntityManager em;

	public int getRating(String gameName) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Rating> criteria = cb.createQuery(Rating.class);
		Root<Rating> rating = criteria.from(Rating.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(member).where(cb.equal(member.get(Member_.name),
		// email));
		criteria.select(rating).where(cb.equal(rating.get("game"), gameName));

		return em.createQuery(criteria).getSingleResult().getRating();

	}

	public void setRating(int ratingNumber, String gameName) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Rating> criteria = cb.createQuery(Rating.class);
		Root<Rating> rating = criteria.from(Rating.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(member).where(cb.equal(member.get(Member_.name),
		// email));
		criteria.select(rating).where(cb.equal(rating.get("game"), gameName));

		Rating rating1 = em.createQuery(criteria).getSingleResult();

		int ratingSum = (rating1.getSum() + ratingNumber) / (rating1.getNumber() + 1);

		rating1.setNumber(rating1.getNumber() + 1);
		rating1.setRating(ratingSum);
		rating1.setSum(rating1.getSum() + ratingNumber);

		em.persist(rating1);

	}

}
