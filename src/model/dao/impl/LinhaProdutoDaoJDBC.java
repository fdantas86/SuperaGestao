package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.LinhaProdutoDao;
import model.entities.LinhaProduto;


public class LinhaProdutoDaoJDBC implements LinhaProdutoDao {
	
	private Connection conn;
	
	public LinhaProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(LinhaProduto obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO linhaproduto "
					+ "(idSetor, linha, descLinha) "
					+ "VALUES "
					+ "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getIdSetor());
			st.setString(2, obj.getLinha());
			st.setString(3, obj.getDescLinha());
			
			int linhasAlteradas = st.executeUpdate();
			
			if(linhasAlteradas >0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setIdLinha(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro Inesperado: Nenhuma Linha afetada. Tente novamente mais tarde.");
			}
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	
	}

	@Override
	public void update(LinhaProduto obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"UPDATE linhaproduto "
					+ "SET idSetor=?, linha=? , descLinha=? "
					+ "WHERE "
					+ "idLinha = ?");
			
			st.setInt(1, obj.getIdSetor());
			st.setString(2, obj.getLinha());
			st.setString(3, obj.getDescLinha());
			st.setInt(4, obj.getIdLinha());
			
			st.executeUpdate();
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer idLinha) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"DELETE FROM linhaproduto "
					+ "WHERE "
					+ "idLinha = ?");
			
			st.setInt(1, idLinha);
			
			st.executeUpdate();
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public LinhaProduto findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
				"SELECT linhaproduto.*, setor.desc_setor "
				+ "FROM linhaproduto INNER JOIN setor "
				+ "ON linhaproduto.idSetor = setor.idSetor "
				+ "WHERE linhaproduto.idLinha = ?");
				st.setInt(1,  id);
				rs = st.executeQuery();
				if (rs.next()) {
	
					LinhaProduto obj = instantiateLinhaProduto(rs);
					return obj;
				}
				return null;
					
		}catch (SQLException e) {
			throw new DbException (e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}
	
	@Override
	public List<LinhaProduto> findBySetor(Integer idSetor) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
				"SELECT linhaproduto.*, setor.descSetor "
				+ "FROM linhaproduto INNER JOIN setor "
				+ "ON linhaproduto.idSetor = setor.idSetor "
				+ "WHERE linhaproduto.idSetor = ? "
				+ "ORDER BY linhaproduto.linha ");
			
				st.setInt(1,  idSetor);
				
				rs = st.executeQuery();
				
				List<LinhaProduto> list = new ArrayList<>();
				
				while (rs.next()) {
	
					LinhaProduto obj = instantiateLinhaProduto(rs);
					list.add(obj);
				}
				return list;
					
		}catch (SQLException e) {
			throw new DbException (e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}
	
	@Override
	public List<LinhaProduto> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
				"SELECT linhaproduto.*, setor.descSetor "
				+ "FROM linhaproduto INNER JOIN setor "
				+ "ON linhaproduto.idSetor = setor.idSetor "
				+ "ORDER BY linhaproduto.linha ");

				rs = st.executeQuery();
				
				List<LinhaProduto> list = new ArrayList<>();
				
				while (rs.next()) {
	
					LinhaProduto obj = instantiateLinhaProduto(rs);
					list.add(obj);
				}
				return list;
					
		}catch (SQLException e) {
			throw new DbException (e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	private LinhaProduto instantiateLinhaProduto(ResultSet rs) throws SQLException {
		LinhaProduto obj = new LinhaProduto();
		obj.setIdLinha(rs.getInt("idLinha"));
		obj.setIdSetor(rs.getInt("idSetor"));
		obj.setLinha(rs.getString("linha"));
		obj.setDescLinha(rs.getString("descLinha"));
		return obj;
	}

	
}
