package cgu.im.helloworld01.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Drama {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//原先AUTO，主鍵生成器可能沒有正確初始化，導致新插入的資料與舊資料產生衝突
	//改成IDENTITY之後，使每次插入的主鍵都是唯一的
	private Long id;

	private String dramaName, dramaCountry, dramaIntro;
	private int dramaYear, dramaEpisode;
	
	@OneToMany(mappedBy = "dramaId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ClassOfDrama> classOfDramas;

	public Drama() {}
	
	public Drama(String dramaName, String dramaCountry, String dramaIntro, int dramaEpisode,int dramaYear) {
		super();
		this.dramaName = dramaName;
		this.dramaCountry = dramaCountry;
		this.dramaIntro = dramaIntro;
		this.dramaEpisode = dramaEpisode;
		this.dramaYear = dramaYear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDramaName() {
		return dramaName;
	}

	public void setDramaName(String dramaName) {
		this.dramaName = dramaName;
	}

	public String getDramaCountry() {
		return dramaCountry;
	}

	public void setDramaCountry(String dramaCountry) {
		this.dramaCountry = dramaCountry;
	}

	public String getDramaIntro() {
		return dramaIntro;
	}

	public void setDramaIntro(String dramaIntro) {
		this.dramaIntro = dramaIntro;
	}

	public int getDramaYear() {
		return dramaYear;
	}

	public void setDramaYear(int dramaYear) {
		this.dramaYear = dramaYear;
	}

	public int getDramaEpisode() {
		return dramaEpisode;
	}

	public void setDramaEpisode(int dramaEpisode) {
		this.dramaEpisode = dramaEpisode;
	}

	public Set<ClassOfDrama> getClassOfDramas() {
		return classOfDramas;
	}

	public void setClassOfDramas(Set<ClassOfDrama> classOfDramas) {
		this.classOfDramas = classOfDramas;
	}

}
