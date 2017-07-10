package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Scwj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mindmap", catalog = "study3")
public class MindMap implements java.io.Serializable {

	// Fields

	private String id;
	private String data;
	private String uid;
	private String name;
	// Constructors
	


	/** default constructor */
	public MindMap() {
	}

	
	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "uid")
	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}
	@Column(name = "data")
	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MindMap [id=" + id + ", data=" + data + ", uid=" + uid
				+ ", name=" + name + "]";
	}

	
}