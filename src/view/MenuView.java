package view;

import controller.EquipamentoController;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Equipamento;

public class MenuView {

    // O Balcão (View) só conversa com o Gerente (Controller) e lê o teclado com o Scanner
    private EquipamentoController controller = new EquipamentoController();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n==========================================");
            System.out.println("   GESTÃO DE EQUIPAMENTOS - LABORATÓRIO   ");
            System.out.println("==========================================");
            System.out.println("1 - Cadastrar novo equipamento");
            System.out.println("2 - Listar todos os equipamentos");
            System.out.println("3 - Buscar equipamento por patrimônio");
            System.out.println("4 - Atualizar equipamento");
            System.out.println("5 - Excluir equipamento");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    telaCadastrar();
                    break;
                case 2:
                    telaListar();
                    break;
                case 3:
                    telaBuscar();
                    break;
                case 4:
                    telaAtualizar();
                    break;
                case 5:
                    telaExcluir();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Digite um número de 0 a 5.");
            }
        }
        scanner.close();
    }

    private void telaCadastrar() {
        System.out.println("\n--- CADASTRAR EQUIPAMENTO ---");
        System.out.print("Número de Patrimônio: ");
        int patrimonio = lerInteiro();

        System.out.print("Tipo (ex: Computador, Projetor, Switch): ");
        String tipo = scanner.nextLine();

        System.out.print("Descrição (ex: Dell Optiplex i5): ");
        String descricao = scanner.nextLine();

        System.out.print("Laboratório (ex: Lab 01): ");
        String laboratorio = scanner.nextLine();

        System.out.print("Situação (ex: Disponível, Em manutenção): ");
        String situacao = scanner.nextLine();

        System.out.print("Memória RAM em GB (ou aperte Enter para deixar vazio): ");
        Integer memoriaRam = lerInteiroOpcional();

        System.out.print("Luminosidade em lúmens (ou aperte Enter para deixar vazio): ");
        Integer luminosidade = lerInteiroOpcional();

        String resultado = controller.cadastrar(patrimonio, tipo, descricao, laboratorio, situacao, memoriaRam, luminosidade);
        System.out.println("\n>> " + resultado);
    }

    private void telaListar() {
        System.out.println("\n--- LISTA DE EQUIPAMENTOS ---");
        try {
            List<Equipamento> lista = controller.listarTodos();
            if (lista.isEmpty()) {
                System.out.println("Nenhum equipamento cadastrado no momento.");
            } else {
                for (Equipamento eq : lista) {
                    System.out.println(eq); // Chama automaticamente o toString() do Equipamento
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar lista: " + e.getMessage());
        }
    }

    private void telaBuscar() {
        System.out.println("\n--- BUSCAR EQUIPAMENTO ---");
        System.out.print("Digite o número de Patrimônio: ");
        int patrimonio = lerInteiro();

        try {
            Equipamento eq = controller.buscarPorPatrimonio(patrimonio);
            if (eq != null) {
                System.out.println("Encontrado: " + eq);
            } else {
                System.out.println("Nenhum equipamento encontrado com o patrimônio " + patrimonio + ".");
            }
        } catch (SQLException e) {
            System.out.println("Erro na busca: " + e.getMessage());
        }
    }

    private void telaAtualizar() {
        System.out.println("\n--- ATUALIZAR EQUIPAMENTO ---");
        System.out.print("Digite o Patrimônio do equipamento que deseja alterar: ");
        int patrimonio = lerInteiro();

        System.out.print("Novo Tipo: ");
        String tipo = scanner.nextLine();

        System.out.print("Nova Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Novo Laboratório: ");
        String laboratorio = scanner.nextLine();

        System.out.print("Nova Situação: ");
        String situacao = scanner.nextLine();

        System.out.print("Nova Memória RAM em GB (ou Enter para vazio): ");
        Integer memoriaRam = lerInteiroOpcional();

        System.out.print("Nova Luminosidade em lúmens (ou Enter para vazio): ");
        Integer luminosidade = lerInteiroOpcional();

        String resultado = controller.atualizar(patrimonio, tipo, descricao, laboratorio, situacao, memoriaRam, luminosidade);
        System.out.println("\n>> " + resultado);
    }

    private void telaExcluir() {
        System.out.println("\n--- EXCLUIR EQUIPAMENTO ---");
        System.out.print("Digite o Patrimônio do equipamento que deseja excluir: ");
        int patrimonio = lerInteiro();

        String resultado = controller.excluir(patrimonio);
        System.out.println("\n>> " + resultado);
    }

    // Método auxiliar para ler números obrigatórios sem travar se o usuário digitar letras
    private int lerInteiro() {
        while (true) {
            try {
                String texto = scanner.nextLine();
                return Integer.parseInt(texto.trim());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido! Digite apenas números inteiros: ");
            }
        }
    }

    // Método auxiliar para campos opcionais (se apertar Enter vazio, devolve null)
    private Integer lerInteiroOpcional() {
        while (true) {
            String texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                return null;
            }
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido! Digite um número inteiro ou aperte Enter para pular: ");
            }
        }
    }
}