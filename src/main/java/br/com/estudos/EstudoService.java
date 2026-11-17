package br.com.estudos;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class EstudoService {
    private final ChatClient chatClient;

    public EstudoService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    // Envia o assunto para a IA e recebe uma resposta.
    public String gerar(String tipo, String assunto) {
        String instrucao;
        switch (tipo) {
            case "Resumo":
                instrucao = "Faça um resumo curto sobre o assunto: ";
                break;
            case "Exercícios":
                instrucao = "Crie 3 questões de estudo com gabarito sobre: ";
                break;
            default:
                instrucao = "Explique de maneira simples, com um exemplo, o assunto: ";
        }
        return chatClient.prompt()
                .system("Você é um professor. Responda em português, com linguagem simples e texto sem Markdown.")
                .user(instrucao + assunto)
                .call()
                .content();
    }
}
