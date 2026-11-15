package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.Equipamento;

public class EquipamentoDAO {

    // CREATE - adiciona um novo equipamento no banco
    public void inserir(Equipamento eq) throws SQLException {
        String sql = "INSERT INTO equipamentos (patrimonio, tipo, descricao, laboratorio, situacao, memoria_ram, luminosidade) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, eq.getPatrimonio());
            stmt.setString(2, eq.getTipo());
            stmt.setString(3, eq.getDescricao());
            stmt.setString(4, eq.getLaboratorio());
            stmt.setString(5, eq.getSituacao());

            // Tratamento para campos opcionais (nulos)
            if (eq.getMemoriaRam() != null) {
                stmt.setInt(6, eq.getMemoriaRam());
            } else {
                stmt.setNull(6, Types.INTEGER);
            }

            if (eq.getLuminosidade() != null) {
                stmt.setInt(7, eq.getLuminosidade());
            } else {
                stmt.setNull(7, Types.INTEGER);
            }

            stmt.executeUpdate();
        }
    }

    // READ - Listar todos os equipamentos cadastrados
    public List<Equipamento> listarTodos() throws SQLException {
        List<Equipamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipamentos";

        try (Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Equipamento eq = new Equipamento();
                eq.setPatrimonio(rs.getInt("patrimonio"));
                eq.setTipo(rs.getString("tipo"));
                eq.setDescricao(rs.getString("descricao"));
                eq.setLaboratorio(rs.getString("laboratorio"));
                eq.setSituacao(rs.getString("situacao"));

                // getObject(..., Integer.class) preserva o null do banco em vez de transformar em 0
                eq.setMemoriaRam(rs.getObject("memoria_ram", Integer.class));
                eq.setLuminosidade(rs.getObject("luminosidade", Integer.class));

                lista.add(eq);
            }
        }
        return lista;
    }

    // READ (por ID) - Buscar um equipamento específico pelo número de patrimônio
    public Equipamento buscarPorPatrimonio(int patrimonio) throws SQLException {
        String sql = "SELECT * FROM equipamentos WHERE patrimonio = ?";

        try (Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patrimonio);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Equipamento eq = new Equipamento();
                    eq.setPatrimonio(rs.getInt("patrimonio"));
                    eq.setTipo(rs.getString("tipo"));
                    eq.setDescricao(rs.getString("descricao"));
                    eq.setLaboratorio(rs.getString("laboratorio"));
                    eq.setSituacao(rs.getString("situacao"));
                    eq.setMemoriaRam(rs.getObject("memoria_ram", Integer.class));
                    eq.setLuminosidade(rs.getObject("luminosidade", Integer.class));
                    return eq;
                }
            }
        }
        return null; // Retorna null se não encontrar nenhum equipamento com esse patrimônio
    }

    // UPDATE - Atualizar os dados de um equipamento existente
    public boolean atualizar(Equipamento eq) throws SQLException {
        String sql = "UPDATE equipamentos SET tipo = ?, descricao = ?, laboratorio = ?, situacao = ?, memoria_ram = ?, luminosidade = ? WHERE patrimonio = ?";

        try (Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, eq.getTipo());
            stmt.setString(2, eq.getDescricao());
            stmt.setString(3, eq.getLaboratorio());
            stmt.setString(4, eq.getSituacao());

            if (eq.getMemoriaRam() != null) {
                stmt.setInt(5, eq.getMemoriaRam());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }

            if (eq.getLuminosidade() != null) {
                stmt.setInt(6, eq.getLuminosidade());
            } else {
                stmt.setNull(6, Types.INTEGER);
            }

            stmt.setInt(7, eq.getPatrimonio());

            // executeUpdate retorna quantas linhas foram afetadas no banco
            return stmt.executeUpdate() > 0;
        }
    }

    // DELETE - Excluir um equipamento pelo número de patrimônio
    public boolean excluir(int patrimonio) throws SQLException {
        String sql = "DELETE FROM equipamentos WHERE patrimonio = ?";

        try (Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patrimonio);
            return stmt.executeUpdate() > 0;
        }
    }
}