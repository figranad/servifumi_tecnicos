package com.app_2sis.e_voluciona.servifumi_tecnicos.model.ws;

public class BeanProgramacion {
    private String id;
    private String programacion_id;
    private String observaciones;
    private String titulo;  //Solo informativo

    public BeanProgramacion(String id, String programacion_id, String observaciones, String titulo) {
        this.id = id;
        this.programacion_id = programacion_id;
        this.observaciones = observaciones;
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgramacion_id() {
        return programacion_id;
    }

    public void setProgramacion_id(String programacion_id) {
        this.programacion_id = programacion_id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
