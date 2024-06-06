package com.upao.pe.nutricampus.controladores;

import io.github.flashvayne.chatgpt.dto.ChatRequest;
import io.github.flashvayne.chatgpt.dto.ChatResponse;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gpt")
//@CrossOrigin
public class GPTControlador implements InitializingBean {

    @Autowired private ChatgptService chatgptService;

    @PostMapping("/chat")
    public String chatear(@RequestBody String mensaje){
        System.out.println(mensaje);
        return chatgptService.sendMessage(mensaje);
    }

    @GetMapping("/prompt")
    public ChatResponse prompt(@RequestParam String mensaje){
        Integer maxTokens = 300;

        String model = "text-davinci-003";

        Double temperature = 0.5;

        Double topP = 1.0;

        ChatRequest chatRequest = new ChatRequest(model, mensaje, maxTokens, temperature, topP);

        ChatResponse res = chatgptService.sendChatRequest(chatRequest);

        System.out.println("Response was: " + res.toString());

        return res;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(" ==== Iniciando Chat GPT ====");
    }
}
