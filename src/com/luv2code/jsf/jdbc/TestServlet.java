package com.luv2code.jsf.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

	@Resource(name = "jdbc/sistemadirid")
	private DataSource dataSource;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "select * from euro_resultado;";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			while (myRs.next()) {
				String idResult = myRs.getString("idResultado");
				String apostas = myRs.getString("idApostas_FK");
				String resultado = myRs.getString("Resultado");
				out.println(idResult);
				out.println(apostas);
				out.println(resultado);
				System.out.println(idResult);
				System.out.println(apostas);
				System.out.println(resultado);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			out.println(exc.getMessage());
		}
	}

}
