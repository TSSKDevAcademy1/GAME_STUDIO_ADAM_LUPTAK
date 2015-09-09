package games;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rating implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private int rating;

	private int number;

	private int sum;

	@Column(unique = true)
	private String game;

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getRating() {
		return rating;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

}
