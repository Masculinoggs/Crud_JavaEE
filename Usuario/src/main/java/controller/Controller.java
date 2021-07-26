package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
public class Controller extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dao. */
	DAO dao = new DAO();

	/** The usuario. */
	JavaBeans usuario = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();

	}

	/**
	 * Do get.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			usuarios(request, response);
		} else if (action.equals("/insert")) {
			novoUsuario(request, response);
		} else if (action.equals("/select")) {
			listarUsuario(request, response);
		} else if (action.equals("/update")) {
			editarUsuario(request, response);
		} else if (action.equals("/delete")) {
			removerUsuario(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * Usuarios.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
//Listar usuarios
	protected void usuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaBeans> lista = dao.listarUsuarios();
		// Encaminhar a lista ao documento usuario.jsp
		request.setAttribute("usuarios", lista);
		RequestDispatcher rd = request.getRequestDispatcher("usuario.jsp");
		rd.forward(request, response);

	}

	/**
	 * Novo usuario.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	// Novo usuario
	protected void novoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setar as variáveis JavaBeans
		usuario.setNome(request.getParameter("nome"));
		usuario.setFone(request.getParameter("fone"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));
		// invocar o método inserirUsuario passando o objeto usuario
		dao.inserirUsuario(usuario);
		// rediricionar para o documento usuario.jsp
		response.sendRedirect("main");

	}

	/**
	 * Listar usuario.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	// Editar usuarios
	protected void listarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do usuario que será editado
		String iduse = request.getParameter("iduse");
		// Setar a variável JavaBeans
		usuario.setIduse(iduse);
		// Executar o método selecionarUsuario (DAO)
		dao.selecionarUsuario(usuario);
		// Setar os atributos do formulário com o conteúdo JavaBeans
		request.setAttribute("iduse", usuario.getIduse());
		request.setAttribute("nome", usuario.getNome());
		request.setAttribute("fone", usuario.getFone());
		request.setAttribute("email", usuario.getEmail());
		request.setAttribute("senha", usuario.getSenha());
		// Encaminhar ao documento editarusuario.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editarusuario.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar usuario.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	protected void editarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setar as variáveis JavaBeans
		usuario.setIduse(request.getParameter("iduse"));
		usuario.setNome(request.getParameter("nome"));
		usuario.setFone(request.getParameter("fone"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));
		// executar o método alterarUsuario
		dao.alterarUsuario(usuario);
		// Rediricionar para o documento usuario.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}

	/**
	 * Remover usuario.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	// Remover contato
	protected void removerUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do contato a ser excluído (validar.js)
		String iduse = request.getParameter("iduse");
		// Setar a variável iduse JavaBeans
		usuario.setIduse(iduse);
		// Executar o método deletarUsuario (DAO) pasando o objeto contato
		dao.deletarUsuario(usuario);
		// Rediricionar para o documento usuario.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}
}
