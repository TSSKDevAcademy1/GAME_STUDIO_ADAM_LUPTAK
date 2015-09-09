package games;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comentars {

	@Id
	@GeneratedValue
	private Long id;

	private String coment;

	private String gameComentName;

	private String userName;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public String getComent() {
		return coment;
	}

	public void setComent(String coment) {
		this.coment = coment;
	}

	public String getGameComentName() {
		return gameComentName;
	}

	public void setGameComentName(String gameComentName) {
		this.gameComentName = gameComentName;
	}

}
