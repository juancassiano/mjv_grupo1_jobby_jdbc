package com.grupoo1.grupo1.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.grupoo1.grupo1.jdbc.ConnectionFactory;
import com.grupoo1.grupo1.model.Professor;

public class ProfessorDAO {

	   private Connection connection;

	    public ProfessorDAO(Connection connection) {
	        this.connection = connection;
	    }
	    
	    public ProfessorDAO() {
		}

	public List<Professor> listar() {
		List<Professor> professores = new ArrayList<>();
		String sql = "SELECT * FROM PROFESSOR";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();

			try(ResultSet rst = pstm.getResultSet()){
				while(rst.next()) {
							//complete o codigo para poder adicionar em categorias: Ex:						Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
					Professor professor = new Professor();
					professor.setId(rst.getInt("ID"));
	                professor.setNome(rst.getString("NOME"));
	                professor.setDataNascimento(rst.getDate("DATA_NASCIMENTO").toLocalDate());
	                professor.setCargaHoraria(rst.getTime("CARGA_HORARIA").toLocalTime());
	                professor.setValorHora(rst.getDouble("VALOR_HORA"));
	                professor.setEstrangeiro(rst.getBoolean("ESTRANGEIRO"));
	                professor.setHorasDisponiveis(rst.getInt("HORAS_DISPONIVEIS"));
	                professor.setBiografia(rst.getString("BIOGRAFIA"));
	                professor.setDisciplinas(Arrays.asList(rst.getString("DISCIPLINAS").split(",")));
	                professor.setDataHoraCadastro(rst.getTimestamp("DATA_HORA_CADASTRO").toLocalDateTime());

					professores.add(professor);
				}
				return professores;
			}
		
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void salvar(Professor professor) {
		
		String sql = "INSERT INTO PROFESSOR (NOME, DATA_NASCIMENTO, CARGA_HORARIA, VALOR_HORA, ESTRANGEIRO, HORAS_DISPONIVEIS, BIOGRAFIA, DATA_HORA_CADASTRO, DISCIPLINAS) VALUES(?,?,?,?,?,?,?,?,?)";

		try (Connection connection = ConnectionFactory.criarConexao();PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			

			pstm.setString(1, professor.getNome());
			pstm.setDate(2, Date.valueOf(professor.getDataNascimento()));
			pstm.setTime(3, Time.valueOf(professor.getCargaHoraria()));
			pstm.setDouble(4, professor.getValorHora());
			pstm.setBoolean(5, professor.isEstrangeiro());
			pstm.setInt(6, professor.getHorasDisponiveis());
			pstm.setString(7, professor.getBiografia());
			pstm.setTimestamp(8, Timestamp.valueOf(professor.getDataHoraCadastro()));

			List<String> disciplinas = professor.getDisciplinas();
			String disciplinasConcatenadas = String.join(",", disciplinas);
			pstm.setString(9, disciplinasConcatenadas);

			pstm.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	
	
}


