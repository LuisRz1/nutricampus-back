package com.upao.pe.nutricampus.repositorios;

import com.upao.pe.nutricampus.serializers.LlamaResponse;

public interface LlamaIARepositorio {

    LlamaResponse generarMensaje(String prompt);
    LlamaResponse generarBroma(String tema);
}
