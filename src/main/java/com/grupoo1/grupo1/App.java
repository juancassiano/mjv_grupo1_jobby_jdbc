package com.grupoo1.grupo1;


import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import com.grupoo1.grupo1.dao.ProfessorDAO;
import com.grupoo1.grupo1.jdbc.ConnectionFactory;
import com.grupoo1.grupo1.model.Professor;

public class App {
	public static void main( String[] args){

		incluirProfessor();

	}

	private static Professor popularBanco() {
		Professor professor = new Professor();
		List<String> disciplinas = Arrays.asList("Java", "Angular");
		professor.setNome("Juan Cassiano");
		professor.setDataNascimento(LocalDate.of(1993, 10, 11));
		professor.setCargaHoraria(LocalTime.of(8, 0, 0));
		professor.setDataHoraCadastro(LocalDateTime.now());
		professor.setValorHora(50.0);
		professor.setEstrangeiro(false);
		professor.setHorasDisponiveis(8);
		professor.setDisciplinas(disciplinas);
		professor.setBiografia("Desenvolvedor Java");

		return professor;
	}

	private static void incluirProfessor() {
		Connection connection = ConnectionFactory.criarConexao();
		ProfessorDAO professorDAO = new ProfessorDAO(connection);
    	Professor professor = popularBanco();
    	professorDAO.salvar(professor);
    	professorDAO.listar();
	}
}
	
