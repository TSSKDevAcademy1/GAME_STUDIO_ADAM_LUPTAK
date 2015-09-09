package score;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ScorePokus {

	@Id
	@GeneratedValue
	private Long id;
	
	private int score;
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
