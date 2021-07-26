package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {

	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";

	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbusuarios?useTimezone=true&serverTimezone=UTC";

	/** The user. */
	private String user = "root";

	/** The password. */
	private String password = "dba123";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// CRUD CREATE

	/**
	 * Inserir usuario.
	 *
	 * @param usuario the usuario
	 */
	public void inserirUsuario(JavaBeans usuario) {
		String create = "insert into users (nome,fone,email,senha) values (?,?,?,?)";
		try {
			// abrir conexao
			Connection con = conectar();
			// Preparar a query para execucao no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parâmetros (?) pelo conteudo das variaveis JavaBeans
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getFone());
			pst.setString(3, usuario.getEmail());
			pst.setString(4, usuario.getSenha());
			// Executar a query
			pst.executeUpdate();
			// Encerrar a conexao com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Listar usuarios.
	 *
	 * @return the array list
	 */
	// ** CRUD READ **/
	public ArrayList<JavaBeans> listarUsuarios() {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> usuarios = new ArrayList<>();
		String read = "select * from users order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

			// o laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				// váriaveis de apaio que recebem os dados do banco
				String iduse = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				String senha = rs.getString(5);

				// populando o ArrayList
				usuarios.add(new JavaBeans(iduse, nome, fone, email, senha));
			}
			con.close();
			return usuarios;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * CRUD UPDATE *.
	 *
	 * @param usuario the usuario
	 */
	// selecionar o usuario
	public void selecionarUsuario(JavaBeans usuario) {
		String read2 = "select * from users where iduse = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, usuario.getIduse());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// setar as variáveis JavaBeans
				usuario.setIduse(rs.getString(1));
				usuario.setNome(rs.getString(2));
				usuario.setFone(rs.getString(3));
				usuario.setEmail(rs.getString(4));
				usuario.setSenha(rs.getString(5));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Alterar usuario.
	 *
	 * @param usuario the usuario
	 */
	// Editar usuario
	public void alterarUsuario(JavaBeans usuario) {
		String create = "update users set nome=?, fone=?, email=?, senha=? where iduse=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getFone());
			pst.setString(3, usuario.getEmail());
			pst.setString(4, usuario.getSenha());
			pst.setString(5, usuario.getIduse());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * CRUD DELETE *.
	 *
	 * @param usuario the usuario
	 */
	public void deletarUsuario(JavaBeans usuario) {
		String delete = "delete from users where iduse=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, usuario.getIduse());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
