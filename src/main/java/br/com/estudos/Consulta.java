package br.com.estudos;

import jakarta.persistence.*;

@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    @Column(length = 4000)
    private String assunto;
    @Lob
    private String resposta;

    public Consulta() {
    }

    public Consulta(String tipo, String assunto, String resposta) {
        this.tipo = tipo;
        this.assunto = assunto;
        this.resposta = resposta;
    }

    public Long getId() { return id; }
    public String getTipo() { return tipo; }
    public String getAssunto() { return assunto; }
    public String getResposta() { return resposta; }
}
