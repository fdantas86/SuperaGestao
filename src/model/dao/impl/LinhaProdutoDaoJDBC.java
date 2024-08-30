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
					+ "(id_setor, linha, desc_linha) "
					+ "VALUES "
					+ "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getid_setor());
			st.setString(2, obj.getlinha());
			st.setString(3, obj.getdesc_linha());
			
			int linhasAlteradas = st.executeUpdate();
			
			if(linhasAlteradas >0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setid_linha(id);
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
					+ "SET id_setor=?, linha=? , desc_linha=? "
					+ "WHERE "
					+ "id_linha = ?");
			
			st.setInt(1, obj.getid_setor());
			st.setString(2, obj.getlinha());
			st.setString(3, obj.getdesc_linha());
			st.setInt(4, obj.getid_linha());
			
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
					+ "id_linha = ?");
			
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
				+ "ON linhaproduto.id_setor = setor.id_setor "
				+ "WHERE linhaproduto.id_linha = ?");
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
	public List<LinhaProduto> findBySetor(Integer id_setor) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
				"SELECT linhaproduto.*, setor.desc_setor "
				+ "FROM linhaproduto INNER JOIN setor "
				+ "ON linhaproduto.id_setor = setor.id_setor "
				+ "WHERE linhaproduto.id_setor = ? "
				+ "ORDER BY linhaproduto.linha ");
			
				st.setInt(1,  id_setor);
				
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
				"SELECT linhaproduto.*, setor.desc_setor "
				+ "FROM linhaproduto INNER JOIN setor "
				+ "ON linhaproduto.id_setor = setor.id_setor "
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
		obj.setid_linha(rs.getInt("id_linha"));
		obj.setid_setor(rs.getInt("id_setor"));
		obj.setlinha(rs.getString("linha"));
		obj.setdesc_linha(rs.getString("desc_linha"));
		return obj;
	}

	
}
