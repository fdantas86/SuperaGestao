package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.LinhaProdutoDao;
import model.entities.LinhaProduto;

public class Main2 {

	public static void main(String[] args) {
		
		System.out.println("DAO Um - Find By ID");
		LinhaProdutoDao LinhaProdutoDao = DaoFactory.createLinhaProdutoDao();
		LinhaProduto linhaProduto = LinhaProdutoDao.findById(19);
		System.out.println(linhaProduto);
		
		//System.out.println("DAO Um - Insert");
		//LinhaProduto linhaProduto2 = new LinhaProduto(null, 6, "DC-04", "PERSIANAS");
		//LinhaProdutoDao.insert(linhaProduto2);
		//System.out.println("Foi inserido a nova linha de ID: " + linhaProduto2.getid_linha());

		System.out.println("DAO 3 - Atualizar");
		
		// linhaProduto.setdesc_linha("ESTATUAS");
		// LinhaProdutoDao.update(linhaProduto);
		// System.out.println(linhaProduto);
		
		System.out.println("DAO 4 - Delete");
		
		// int idExc = 19;
		
		// LinhaProdutoDao.deleteById(idExc);
		// System.out.println("Exclusão realizada com sucesso.");
		
		System.out.println("DAO 5 - Find by Setor");
		
		List<LinhaProduto> list = LinhaProdutoDao.findBySetor(4);
		
		for (LinhaProduto obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("DAO 5 - Find All");
		
		List<LinhaProduto> lista = LinhaProdutoDao.findAll();
		
		for (LinhaProduto obj : lista) {
			System.out.println(obj);
		}
		
		
		
		
		
		
		
		
	}

}
