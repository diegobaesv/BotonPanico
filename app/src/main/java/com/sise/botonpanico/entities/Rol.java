package com.sise.botonpanico.entities;

import java.util.Date;

public class Rol {
    private Integer idRol;
    private String descripcion;
    private String estadoAuditoria;
    private Date fechaCreacionAuditoria;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadoAuditoria() {
        return estadoAuditoria;
    }

    public void setEstadoAuditoria(String estadoAuditoria) {
        this.estadoAuditoria = estadoAuditoria;
    }

    public Date getFechaCreacionAuditoria() {
        return fechaCreacionAuditoria;
    }

    public void setFechaCreacionAuditoria(Date fechaCreacionAuditoria) {
        this.fechaCreacionAuditoria = fechaCreacionAuditoria;
    }
}
