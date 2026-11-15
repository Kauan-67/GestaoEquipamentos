package model;

public class Equipamento {
    private int patrimonio;
    private String tipo;
    private String descricao;
    private String laboratorio;
    private String situacao;
    private Integer memoriaRam;
    private Integer luminosidade;

    // Construtor vazio
    public Equipamento() {
    }

    // Construtor completo
    public Equipamento(int patrimonio, String tipo, String descricao, String laboratorio,
                    String situacao, Integer memoriaRam, Integer luminosidade) {
        this.patrimonio = patrimonio;
        this.tipo = tipo;
        this.descricao = descricao;
        this.laboratorio = laboratorio;
        this.situacao = situacao;
        this.memoriaRam = memoriaRam;
        this.luminosidade = luminosidade;
    }

    // Getters e Setters
    public int getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(int patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Integer getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(Integer memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public Integer getLuminosidade() {
        return luminosidade;
    }

    public void setLuminosidade(Integer luminosidade) {
        this.luminosidade = luminosidade;
    }

    @Override
    public String toString() {
        String extra = "";
        if (memoriaRam != null) {
            extra += " | RAM: " + memoriaRam + " GB";
        }
        if (luminosidade != null) {
            extra += " | Luminosidade: " + luminosidade + " lúmens";
        }
        return "Património: " + patrimonio +
            " | Tipo: " + tipo +
            " | Descrição: " + descricao +
            " | Lab: " + laboratorio +
            " | Situação: " + situacao + extra;
    }
}