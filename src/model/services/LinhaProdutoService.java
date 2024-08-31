package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.LinhaProduto;

public class LinhaProdutoService {
	public List<LinhaProduto> findAll() {
		List<LinhaProduto> list = new ArrayList<>();
		list.add(new LinhaProduto(1, 2, "AM-01", "B"));
		list.add(new LinhaProduto(2, 2, "A", "B"));
		list.add(new LinhaProduto(3, 2, "A", "B"));
		list.add(new LinhaProduto(4, 2, "A", "B"));
		return list;
		
		
	}
	
	
}
