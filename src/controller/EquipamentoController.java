package controller;

import dao.EquipamentoDAO;
import java.sql.SQLException;
import java.util.List;
import model.Equipamento;

public class EquipamentoController {

    // O Gerente (Controller) precisa ter o contato do Estoquista (DAO) para mandar ordens para ele
    private EquipamentoDAO dao = new EquipamentoDAO();

    // 1. CADASTRAR (Validar e mandar salvar)
    public String cadastrar(int patrimonio, String tipo, String descricao, String laboratorio,
                            String situacao, Integer memoriaRam, Integer luminosidade) {
        try {
            // Regra 1: Não aceita patrimônio zero ou negativo
            if (patrimonio <= 0) {
                return "Erro: O número de patrimônio deve ser maior que zero.";
            }

            // Regra 2: Não aceita campos obrigatórios em branco
            if (tipo == null || tipo.trim().isEmpty() ||
                descricao == null || descricao.trim().isEmpty() ||
                laboratorio == null || laboratorio.trim().isEmpty() ||
                situacao == null || situacao.trim().isEmpty()) {
                return "Erro: Tipo, descrição, laboratório e situação são obrigatórios.";
            }

            // Regra 3: Verifica no banco se já existe algum equipamento com esse patrimônio
            if (dao.buscarPorPatrimonio(patrimonio) != null) {
                return "Erro: Já existe um equipamento cadastrado com o patrimônio " + patrimonio + ".";
            }

            // Se passou por todas as regras, preenche a ficha (Model) e manda o DAO salvar no banco
            Equipamento novo = new Equipamento(patrimonio, tipo, descricao, laboratorio, situacao, memoriaRam, luminosidade);
            dao.inserir(novo);
            return "Sucesso: Equipamento cadastrado com sucesso!";

        } catch (SQLException e) {
            return "Erro no banco de dados ao cadastrar: " + e.getMessage();
        }
    }

    // 2. LISTAR TODOS (Pedir a lista ao DAO e entregar para a tela)
    public List<Equipamento> listarTodos() throws SQLException {
        return dao.listarTodos();
    }

    // 3. BUSCAR POR PATRIMÔNIO
    public Equipamento buscarPorPatrimonio(int patrimonio) throws SQLException {
        return dao.buscarPorPatrimonio(patrimonio);
    }

    // 4. ATUALIZAR (Conferir se existe e mandar atualizar)
    public String atualizar(int patrimonio, String tipo, String descricao, String laboratorio,
                            String situacao, Integer memoriaRam, Integer luminosidade) {
        try {
            // Verifica se o equipamento realmente existe antes de tentar editar
            if (dao.buscarPorPatrimonio(patrimonio) == null) {
                return "Erro: Equipamento com patrimônio " + patrimonio + " não encontrado.";
            }

            if (tipo == null || tipo.trim().isEmpty() ||
                descricao == null || descricao.trim().isEmpty() ||
                laboratorio == null || laboratorio.trim().isEmpty() ||
                situacao == null || situacao.trim().isEmpty()) {
                return "Erro: Nenhum campo obrigatório pode ficar vazio.";
            }

            Equipamento atualizado = new Equipamento(patrimonio, tipo, descricao, laboratorio, situacao, memoriaRam, luminosidade);
            boolean ok = dao.atualizar(atualizado);

            if (ok) {
                return "Sucesso: Dados do equipamento atualizados!";
            } else {
                return "Erro: Não foi possível atualizar o equipamento.";
            }

        } catch (SQLException e) {
            return "Erro no banco de dados ao atualizar: " + e.getMessage();
        }
    }

    // 5. EXCLUIR (Conferir e mandar apagar)
    public String excluir(int patrimonio) {
        try {
            boolean ok = dao.excluir(patrimonio);
            if (ok) {
                return "Sucesso: Equipamento removido do sistema!";
            } else {
                return "Erro: Nenhum equipamento encontrado com o patrimônio " + patrimonio + ".";
            }
        } catch (SQLException e) {
            return "Erro no banco de dados ao excluir: " + e.getMessage();
        }
    }
}