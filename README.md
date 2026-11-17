# Assistente de Estudos com Spring AI

Trabalho de Java desenvolvido por William da Silva.

## Objetivo

O sistema ajuda estudantes a estudar. O usuário informa um assunto e pode pedir uma explicação, um resumo ou três exercícios com gabarito. A inteligência artificial gera a resposta e o sistema salva a consulta no banco de dados H2. Também é possível consultar e excluir o histórico.

## Tecnologias

- Java 17 ou superior
- Spring Boot, Spring MVC e Thymeleaf (interface web)
- Spring AI (integração com OpenAI)
- Spring Data JPA e H2 (banco de dados)
- HTML e CSS
- Maven

## Como executar

Instale o Java 17 ou superior e o Maven. Abra a pasta do projeto em um terminal.

Crie uma chave de API em https://platform.openai.com/api-keys. A API precisa de acesso ao modelo e créditos disponíveis; a assinatura do ChatGPT não inclui automaticamente créditos da API.

No PowerShell do Windows, defina a chave apenas na sessão atual:

```powershell
$env:OPENAI_API_KEY = "COLOQUE_SUA_CHAVE_AQUI"
mvn spring-boot:run
```

No Linux ou macOS:

```bash
export OPENAI_API_KEY="COLOQUE_SUA_CHAVE_AQUI"
mvn spring-boot:run
```

Abra http://localhost:8080 no navegador. O banco H2 é criado automaticamente na pasta `dados`. Os registros continuam salvos após reiniciar a aplicação.

## Como funciona

1. O estudante escolhe uma opção e digita um assunto.
2. O Controller recebe os dados do formulário.
3. O Service usa o ChatClient do Spring AI para consultar a OpenAI.
4. O Repository salva a pergunta e a resposta no banco H2.
5. A página mostra o histórico de consultas.

## Arquivos principais

- `Aplicacao.java`: inicia o sistema.
- `Consulta.java`: representa um registro do banco de dados.
- `ConsultaRepository.java`: permite salvar, listar e excluir consultas.
- `EstudoService.java`: envia os pedidos à IA.
- `EstudoController.java`: controla as páginas e formulários.
- `index.html`: interface do sistema.
- `application.properties`: configuração do banco e da IA.

## Roteiro para testar

1. Escolha Explicação e digite `O que é Java?`.
2. Clique em Gerar com IA e confira o histórico.
3. Teste Resumo e Exercícios.
4. Reinicie a aplicação e confira se as consultas continuam salvas.
5. Exclua uma consulta e confirme que ela desapareceu.

## Segurança

A chave da API não está no código. O Spring lê a variável de ambiente `OPENAI_API_KEY`. Não coloque uma chave real no repositório. O `.gitignore` também impede que arquivos locais de configuração e o banco sejam enviados. O servidor escuta apenas no computador local; o projeto é para demonstração acadêmica.
