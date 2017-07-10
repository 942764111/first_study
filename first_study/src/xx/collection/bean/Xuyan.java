package xx.collection.bean;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Xuyan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xuyan", catalog = "study3")
public class Xuyan implements java.io.Serializable {

	// Fields

	private Integer xybh;
	private String xymc;

	// Constructors

	/** default constructor */
	public Xuyan() {
	}

	/** full constructor */
	public Xuyan(String xymc, Set<Teacher> teachers, Set<Zyxx> zyxxes) {
		this.xymc = xymc;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "xybh", unique = true, nullable = false)
	public Integer getXybh() {
		return this.xybh;
	}

	public void setXybh(Integer xybh) {
		this.xybh = xybh;
	}

	@Column(name = "xymc", length = 20)
	public String getXymc() {
		return this.xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
}