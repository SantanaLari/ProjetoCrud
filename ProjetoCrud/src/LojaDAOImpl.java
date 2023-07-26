
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LojaDAOImpl implements LojaDAO{

	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/projetoLoja";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "123456";
	private Connection con = null;
	
	LojaDAOImpl(){
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void salvar(Produto p) {
		String sql = "INSERT INTO produto (id, nome, preco, tipo) ";
		sql += " VALUES('" + p.getId() + "', ";
		sql += " '" + p.getNome() + "', ";
		sql += " '" + p.getPreco() + "', ";
		sql += " '" + p.getTipo() + "') ";
		System.out.println("Query preparada");
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Produto> listar(String id) {
		String sql = "SELECT * FROM produto";
		List<Produto> produto = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Produto pt = new Produto();
				pt.setId(rs.getString("id"));
				pt.setNome(rs.getString("nome"));
				pt.setPreco(rs.getString("preco"));
				pt.setTipo(rs.getString("tipo"));
				produto.add(pt);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return produto;
	}

	@Override
	public void excluir(Produto p) {
		String sql = "DELETE FROM produto WHERE id = ";
		sql += "'" + p.getId() + "'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(Produto p) {
		String sql = "UPDATE produto SET ";
		sql += " nome = " + p.getNome() + "', ";
		sql += " tipo = " + p.getTipo() + "', ";
		sql += " preco = " + p.getPreco() + "', ";
		sql += " WHERE codigo = ";
		sql += "'" + p.getId() + "'";
		System.out.println("Query atualizada");
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
