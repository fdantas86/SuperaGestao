package model.dao;

import db.DB;
import model.dao.impl.LinhaProdutoDaoJDBC;

public class DaoFactory {
	public static LinhaProdutoDao createLinhaProdutoDao() {
		return new LinhaProdutoDaoJDBC(DB.getConnection());
	}
}
