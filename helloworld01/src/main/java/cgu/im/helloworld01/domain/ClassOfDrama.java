package cgu.im.helloworld01.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "classOfDrama")
public class ClassOfDrama {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 	
	 	@JsonIgnore
	    @ManyToOne
	    @JoinColumn(name = "drama_id")
	    private Drama dramaId;

	 	@JsonIgnore
	    @ManyToOne
	    @JoinColumn(name = "class_id")
	    private Class classId;


	    public ClassOfDrama() {}

		public ClassOfDrama(Drama dramaId, Class classId) {
			super();
			this.dramaId = dramaId;
			this.classId = classId;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Drama getDramaId() {
			return dramaId;
		}

		public void setDramaId(Drama dramaId) {
			this.dramaId = dramaId;
		}

		public Class getClassId() {
			return classId;
		}

		public void setClassId(Class classId) {
			this.classId = classId;
		}


}
