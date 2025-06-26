package com.sise.botonpanico.shared;

import com.sise.botonpanico.dto.TipoDocumento;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<TipoDocumento> getTipoDocumentos(){
        List<TipoDocumento> tipoDocumentos = new ArrayList<>();
        tipoDocumentos.add(new TipoDocumento("DNI","Documento Nacional de Identidad"));
        tipoDocumentos.add(new TipoDocumento("CEX","Carnet de Extranjería"));
        tipoDocumentos.add(new TipoDocumento("RUC","Registro Unico Contribuyente"));
        return tipoDocumentos;
    }
}
