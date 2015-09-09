package score;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.tools.examples.service.ScoreRegistration;

@Named(value = "dtBasicView")
@ViewScoped
public class ScoreTable implements Serializable {

	@Inject
	private ScoreRegistration scoreRegistration;

	private List<Score> score;
	private List<Score> score1;
	private List<Score> scorePuzzle;

	@PostConstruct
	public void init() {

		score = scoreRegistration.findAllOrderedByName("mineSweeper");
		score1 = scoreRegistration.findAllOrdered("df");
		scorePuzzle = scoreRegistration.findAllOrderedByName("puzzle");
	}

	public List<Score> getScores() {

		return score;
	}

	public List<Score> getScores1() {

		return score1;
	}

	public List<Score> getScoresPuzzle() {

		return scorePuzzle;
	}
}
