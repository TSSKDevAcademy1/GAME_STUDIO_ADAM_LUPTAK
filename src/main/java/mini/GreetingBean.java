package mini;

import javax.ejb.*;
import javax.persistence.*;

import org.jboss.tools.examples.model.User;

import score.Score;

@Stateless
public class GreetingBean {
	@PersistenceContext
	private EntityManager em;

	public void addScore(int score, User user, String game) {
		Score scoreFinal = new Score();
		scoreFinal.addUser(user);
		scoreFinal.setScore(score);
		scoreFinal.setGame(game);
		user.addReview(scoreFinal);
		em.persist(user);
		em.persist(scoreFinal);

	}

	
}