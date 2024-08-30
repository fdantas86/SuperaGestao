package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Setor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id_setor;
	private String desc_setor;
	
	public Setor() {
		
	}
	
	public Setor(Integer id_setor, String desc_setor) {
		this.id_setor = id_setor;
		this.desc_setor = desc_setor;
	}

	public Integer getid_setor() {
		return id_setor;
	}
	public void setid_setor(Integer id_setor) {
		this.id_setor = id_setor;
	}
	public String getdesc_setor() {
		return desc_setor;
	}
	public void setdesc_setor(String desc_setor) {
		this.desc_setor = desc_setor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_setor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Setor other = (Setor) obj;
		return Objects.equals(id_setor, other.id_setor);
	}

	@Override
	public String toString() {
		return "Setor [id_setor=" + id_setor + ", desc_setor=" + desc_setor + "]";
	}
	
}
