package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.LinhaProdutoDao;
import model.entities.LinhaProduto;

public class LinhaProdutoService {
	
	private LinhaProdutoDao dao = DaoFactory.createLinhaProdutoDao();
	
	public List<LinhaProduto> findAll() {
		return dao.findAll();
	}
	
	public void salvarOuAtualizar(LinhaProduto obj) {
		if(obj.getIdLinha()==null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}
	
	
}
