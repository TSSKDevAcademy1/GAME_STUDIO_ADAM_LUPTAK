package org.jboss.tools.examples.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.tools.examples.model.User;

import score.Score;
import score.ScorePokus;

@Stateless
public class ScoreRegistration {

	@Inject
	private EntityManager em;

	public void addScore(ScorePokus score) {

		em.persist(score);

	}

	public void addScore1(Score score, String game, User user) {
        score.addUser(user);
		score.setGame(game);
		em.persist(score);

	}
	
	public List<Score> findAllOrderedByName(String game) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Score> criteria = cb.createQuery(Score.class);
		Root<Score> score = criteria.from(Score.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new where(cb.equal(user.get("password"), password)
		// feature in JPA 2.0
		// criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
		criteria.select(score).where(cb.equal(score.get("game"), game)).orderBy(cb.desc(score.get("score")));
		return em.createQuery(criteria).setMaxResults(5).getResultList();
	}
	
	
	public List<Score> findAllOrdered(String game) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Score> criteria = cb.createQuery(Score.class);
		Root<Score> score = criteria.from(Score.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new where(cb.equal(user.get("password"), password)
		// feature in JPA 2.0
		// criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
		criteria.select(score).orderBy(cb.desc(score.get("score")));
		return em.createQuery(criteria).getResultList();
	}

}
