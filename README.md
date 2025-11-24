# WorldTalk System

## Equipe de Desenvolvimento

* **Carlos Benedito Maciel II**
* **Rodrigo Tavares Bezerra**
* **Eric Santos Moura Lima**
* **Guilherme Augusto de Morais Araújo**
* **João Inacio Torres**

## Visão Geral do Projeto

O WorldTalk System é uma aplicação baseada em console projetada para administrar os processos fundamentais de uma instituição de ensino. O sistema centraliza o controle de dados acadêmicos e oferece um módulo integrado para gestão de acervo bibliotecário.

A arquitetura do sistema foi organizada em pacotes para separar as responsabilidades entre modelos de dados, lógica de gerenciamento e interface com o usuário.

## Funcionalidades

O sistema é composto pelos seguintes módulos principais:

### 1. Gestão Acadêmica
* **Controle de Pessoas:** Cadastro, consulta e gerenciamento de Alunos, Professores e Administradores.
* **Estrutura Pedagógica:** Gerenciamento de Cursos, Níveis de ensino e Turmas.
* **Inscrições:** Funcionalidades para vincular alunos às turmas e cursos disponíveis.

### 2. Gestão de Biblioteca
* **Acervo:** Controle de materiais bibliográficos e organização do acervo.
* **Circulação:** Registro e controle de empréstimos de materiais para os usuários cadastrados.

### 3. Sistema
* **Controle de Acesso:** Sistema de autenticação (Login) para diferentes tipos de usuários.
* **Agenda:** Utilitários para gerenciamento de horários e dias da semana.
* **Tratamento de Erros:** Implementação de exceções personalizadas para garantir a robustez das operações de cadastro e login.



## Estrutura do Projeto
```
WorldTalk-System/
└── src/
    ├── app/
    │   ├── ContextoSistema.java      // Gerencia o estado e contexto global da aplicação
    │   └── Main.java                 // Ponto de entrada do sistema

    ├── modelos/
    │   ├── Aluno.java
    │   ├── Professor.java
    │   ├── Administrador.java
    │   ├── Curso.java
    │   ├── MaterialBB.java
    │   ├── Emprestimo.java
    │   └── ...                       // Demais entidades do domínio

    ├── gerenciadores/
    │   ├── GerenciadorAlunos.java
    │   ├── GerenciadorProfessores.java
    │   ├── GerenciadorTurmas.java
    │   ├── GerenciadorCursos.java
    │   ├── GerenciadorBiblioteca.java
    │   ├── GerenciadorEmprestimos.java
    │   ├── GerenciadorInscricoes.java
    │   └── GerenciadorLogins.java    // Lógica de autenticação e controle de acesso

    ├── menus/
    │   ├── principal/
    │   │   ├── MenuPrincipal.java
    │   │   ├── MenuAdmin.java
    │   │   └── MenuAluno.java
    │   ├── admin/
    │   ├── biblioteca/
    │   ├── cursos/
    │   └── turmas/
    │       ...                       // Interfaces de texto por perfil e função

    ├── agenda/
    │   ├── DiaSemana.java
    │   └── Horario.java              // Utilitários para agenda e controle de horários

    └── exceptions/
        ├── LoginException.java
        ├── CadastroException.java
        └── ...                       // Exceções específicas do domínio
```

## Como Executar o Projeto

### Pré-requisitos

* **Java JDK** instalado (versão 8 ou superior).
* Uma IDE de desenvolvimento Java (recomendado: **VS Code**, **IntelliJ IDEA** ou **Eclipse**).

### Passo a Passo

1. **Abrir o Projeto:**
   Abra a pasta raiz do projeto (`WorldTalk-System-main`) na sua IDE de preferência.

2. **Localizar a Classe Principal:**
   No explorador de arquivos da IDE, navegue até: `src` > `app` > `Main.java`.

3. **Executar:**
   Abra o arquivo `Main.java` e utilize o comando de execução da IDE (geralmente um botão "Run" ou "Play" localizado acima do método `main` ou na barra de ferramentas).

4. **Interação:**
   O sistema será iniciado no terminal integrado da IDE. Utilize o teclado para navegar pelos menus conforme as instruções exibidas na tela.

### Link do Repositório: https://github.com/SkylerZdev/WorldTalk-System
