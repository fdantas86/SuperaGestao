package model.dao;

import java.util.List;

import model.entities.LinhaProduto;

public interface LinhaProdutoDao {

	void insert (LinhaProduto obj);
	void update (LinhaProduto obj);
	void deleteById (Integer idLinha);
	LinhaProduto findById(Integer idLinha);
	List<LinhaProduto> findBySetor(Integer id_setor);
	List<LinhaProduto> findAll(); 
}
