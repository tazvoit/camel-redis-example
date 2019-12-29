package org.mycompany.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    private String observacion;
    private String fecha;

    @JsonProperty("observacion")
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @JsonProperty("fecha")
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
