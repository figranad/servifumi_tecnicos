package com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter;

public class VehiculoBeanAdapter {
    private String marca;
    private String matricula;
    private String numEconomico;

    public VehiculoBeanAdapter(String marca, String matricula, String numEconomico) {
        this.marca = marca;
        this.matricula = matricula;
        this.numEconomico = numEconomico;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNumEconomico() {
        return numEconomico;
    }

    public void setNumEconomico(String numEconomico) {
        this.numEconomico = numEconomico;
    }
}
