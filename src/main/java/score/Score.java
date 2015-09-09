package score;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.jboss.tools.examples.model.User;

@Entity
public class Score implements Serializable, Comparator<Score>{

	@Id
	@GeneratedValue
	private Long id;
	// @ManyToMany(cascade = { CascadeType.ALL })

	@OneToOne
	private User user;

	public void addUser(User r) {
		user = r;
	}

	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	private String game;

	public Score() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	@Override
	public int compare(Score o1, Score o2) {
		if (o1.getScore() > o2.getScore())
			return 1;
		else
			return 0;
		
	}

}
