package xx.collection.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Problemlist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "problemlist", catalog = "study3")
public class Problemlist implements java.io.Serializable {

	// Fields

	private Integer problemid;
	private Integer problemno;
	private String problemzsdh;
	private Integer problemtype;
	private Integer problemnyd;
	private Integer problemtime;
	private Integer problemscore;
	private Integer problempgd;
	private Integer flag;

	// Constructors
 
	/** default constructor */
	public Problemlist() {
	}

	/** full constructor */
	public Problemlist(Integer problemno, String problemzsdh, Integer problemtype,
			Integer problemnyd, Integer problemtime, Integer problemscore, Integer problempgd,Integer flag) {
		this.problemno = problemno;
		this.problemzsdh=problemzsdh;
		this.problemtype = problemtype;
		this.problemnyd=problemnyd;
		this.problemtime=problemtime;
		this.problemscore=problemscore;
		this.problempgd=problempgd;
		this.flag = flag;
		
		
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "problemid", unique = true, nullable = false)
	public Integer getProblemid() {
		return this.problemid;
	}

	public void setProblemid(Integer problemid) {
		this.problemid = problemid;
	}

	@Column(name = "problemno")
	public Integer getProblemno() {
		return this.problemno;
	}

	public void setProblemno(Integer problemno) {
		this.problemno = problemno;
	}

	@Column(name = "problemtype")
	public Integer getProblemtype() {
		return this.problemtype;
	}

	public void setProblemtype(Integer problemtype) {
		this.problemtype = problemtype;
	}

	@Column(name = "flag")
	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Column(name = "problemzsdh")
	public String getProblemzsdh() {
		return problemzsdh;
	}

	public void setProblemzsdh(String problemzsdh) {
		this.problemzsdh = problemzsdh;
	}

	@Column(name = "problemnyd")
	public Integer getProblemnyd() {
		return problemnyd;
	}

	
	public void setProblemnyd(Integer problemnyd) {
		this.problemnyd = problemnyd;
	}

	@Column(name = "problemtime")
	public Integer getProblemtime() {
		return problemtime;
	}

	
	public void setProblemtime(Integer problemtime) {
		this.problemtime = problemtime;
	}

	@Column(name = "problemscore")
	public Integer getProblemscore() {
		return problemscore;
	}


	public void setProblemscore(Integer problemscore) {
		this.problemscore = problemscore;
	}

	@Column(name = "problempgd")
	public Integer getProblempgd() {
		return problempgd;
	}

	
	public void setProblempgd(Integer problempgd) {
		this.problempgd = problempgd;
	}

}