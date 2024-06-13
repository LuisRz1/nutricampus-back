package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.repositorios.LlamaIARepositorio;
import com.upao.pe.nutricampus.serializers.LlamaResponse;
import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LlamaIAServicio implements LlamaIARepositorio {

    @Autowired ChatClient chatClient;


    @Override
    public LlamaResponse generarMensaje(String prompt) {
        final String llamaMensaje = chatClient.call(prompt);
        return new LlamaResponse().setMessage(llamaMensaje);
    }

    @Override
    public LlamaResponse generarBroma(String tema) {
        final String llamaMensaje = chatClient.call(String.format("Dime una broma sobre %s", tema));
        return new LlamaResponse().setMessage(llamaMensaje);
    }
}
