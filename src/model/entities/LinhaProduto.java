package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class LinhaProduto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id_linha;
	private Integer id_setor;
	private String linha;
	private String desc_linha;
	
	public LinhaProduto () {
		
	}

	public LinhaProduto(Integer id_linha, Integer id_setor, String linha, String desc_linha) {
		this.id_linha = id_linha;
		this.id_setor = id_setor;
		this.linha = linha;
		this.desc_linha = desc_linha;
	}

	public Integer getid_linha() {
		return id_linha;
	}

	public void setid_linha(Integer id_linha) {
		this.id_linha = id_linha;
	}

	public Integer getid_setor() {
		return id_setor;
	}

	public void setid_setor(Integer id_setor) {
		this.id_setor = id_setor;
	}

	public String getlinha() {
		return linha;
	}

	public void setlinha(String linha) {
		this.linha = linha;
	}

	public String getdesc_linha() {
		return desc_linha;
	}

	public void setdesc_linha(String desc_linha) {
		this.desc_linha = desc_linha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_linha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinhaProduto other = (LinhaProduto) obj;
		return Objects.equals(id_linha, other.id_linha);
	}

	@Override
	public String toString() {
		return "LinhaProduto [id_linha=" + id_linha + ", id_setor=" + id_setor + ", linha=" + linha + ", desc_linha="
				+ desc_linha +"]";
	}

}