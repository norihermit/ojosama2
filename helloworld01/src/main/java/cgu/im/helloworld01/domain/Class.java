package cgu.im.helloworld01.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Class {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long classid;
	
	private String className;

	@OneToMany(mappedBy = "classId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ClassOfDrama> classOfDramas;

	public Class() {}

	public Class(String className) {
		super();
		this.className = className;
	}
	

	public Long getClassid() {
		return classid;
	}

	public void setClassid(Long classid) {
		this.classid = classid;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Set<ClassOfDrama> getClassOfDramas() {
		return classOfDramas;
	}

	public void setClassOfDramas(Set<ClassOfDrama> classOfDramas) {
		this.classOfDramas = classOfDramas;
	}
}
