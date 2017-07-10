package xx.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Functions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "functions", schema = "dbo", catalog = "study2")
public class Functions implements java.io.Serializable {

	// Fields

	private String actionname;
	private Modules modules;
	private String functionname;
	private Integer menu;
	private String menuimg;
	private String comments;

	// Constructors

	/** default constructor */
	public Functions() {
	}

	/** minimal constructor */
	public Functions(String actionname) {
		this.actionname = actionname;
	}

	/** full constructor */
	public Functions(String actionname, Modules modules, String functionname,
			Integer menu, String menuimg, String comments
			) {
		this.actionname = actionname;
		this.modules = modules;
		this.functionname = functionname;
		this.menu = menu;
		this.menuimg = menuimg;
		this.comments = comments;
	}

	// Property accessors
	@Id
	@Column(name = "actionname", unique = true, nullable = false)
	public String getActionname() {
		return this.actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mname")
	public Modules getModules() {
		return this.modules;
	}

	public void setModules(Modules modules) {
		this.modules = modules;
	}

	@Column(name = "functionname")
	public String getFunctionname() {
		return this.functionname;
	}

	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}

	@Column(name = "menu")
	public Integer getMenu() {
		return this.menu;
	}

	public void setMenu(Integer menu) {
		this.menu = menu;
	}

	@Column(name = "menuimg")
	public String getMenuimg() {
		return this.menuimg;
	}

	public void setMenuimg(String menuimg) {
		this.menuimg = menuimg;
	}

	@Column(name = "comments")
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}


}