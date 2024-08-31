package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class LinhaProduto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idLinha;
	private Integer idSetor;
	private String linha;
	private String descLinha;
	
	public LinhaProduto() {
		
	}

	public LinhaProduto(Integer idLinha, Integer idSetor, String linha, String descLinha) {
		this.idLinha = idLinha;
		this.idSetor = idSetor;
		this.linha = linha;
		this.descLinha = descLinha;
	}

	public Integer getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(Integer idLinha) {
		this.idLinha = idLinha;
	}

	public Integer getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(Integer idSetor) {
		this.idSetor = idSetor;
	}

	public String getLinha() {
		return linha;
	}

	public void setLinha(String linha) {
		this.linha = linha;
	}

	public String getDescLinha() {
		return descLinha;
	}

	public void setDescLinha(String descLinha) {
		this.descLinha = descLinha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idLinha);
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
		return Objects.equals(idLinha, other.idLinha);
	}

	@Override
	public String toString() {
		return "LinhaProduto [idLinha=" + idLinha + ", idSetor=" + idSetor + ", linha=" + linha + ", descLinha="
				+ descLinha + "]";
	}
	
	
	

}