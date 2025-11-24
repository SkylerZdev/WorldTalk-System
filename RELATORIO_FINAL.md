# Documentação técnica oficial do sistema.
## PROJETO: WORLDTALK SYSTEM (GESTÃO ESCOLAR)

---

### 1. INTRODUÇÃO
Este documento apresenta a documentação técnica do "WorldTalk System", um software desenvolvido em Java para a administração integral de uma escola de idiomas. O sistema foi projetado para resolver problemas de gestão de matrículas, controle de acesso e organização de turmas, atendendo a três perfis de usuários: Administradores, Professores e Alunos.

### 2. ARQUITETURA DO SISTEMA
O projeto adota uma arquitetura em camadas (Layered Architecture), separando claramente as responsabilidades de visualização, lógica de negócio e armazenamento de dados. A estrutura de pacotes no diretório `src` reflete essa organização:

* **View (`src.menus`):** Camada de interação com o usuário via console.
* **Controller (`src.gerenciadores`):** Camada responsável pelas regras de negócio e validações.
* **Model (`src.modelos`):** Representação das entidades e objetos de domínio.

O sistema é inicializado através de um `ContextoSistema`, que utiliza Injeção de Dependência para garantir que todos os menus tenham acesso às mesmas instâncias dos gerenciadores.

---

### 3. ANÁLISE TÉCNICA DETALHADA

#### 3.1. Estrutura de Dados e Persistência
O sistema optou por não utilizar banco de dados relacional externo nesta versão, implementando um modelo de **Persistência em Memória** de alta performance.
* **Uso de HashMaps:** No `GerenciadorAlunos.java`, a utilização de `Map<Long, Aluno>` permite que a busca de alunos e validações de login ocorram com complexidade de tempo **O(1)** (constante), garantindo resposta instantânea mesmo com grande volume de registros.
* **Integridade Referencial:** O sistema mantém duas estruturas sincronizadas: uma para dados cadastrais e outra para credenciais de acesso (`GerenciadorLogins`), assegurando que não existam inconsistências no acesso.

#### 3.2. Funcionalidades do Módulo Administrativo (`MenuAdm_Alunos.java`)
O painel administrativo é o núcleo de controle do sistema, implementando operações completas de CRUD (Create, Read, Update, Delete):
* **Consistência de Dados:** Ao remover um aluno, o algoritmo garante a exclusão tanto da lista de dados acadêmicos quanto da base de autenticação, prevenindo falhas de segurança ("usuários fantasmas" no sistema).
* **Gestão de Estados (VIP):** A edição de alunos permite a alteração dinâmica de status (Normal/VIP). O código implementa gatilhos (`triggers`) que reorganizam as filas de prioridade de inscrição automaticamente quando um aluno se torna VIP.

#### 3.3. Funcionalidades do Módulo do Aluno (`MenuAluno.java`)
O front-end do aluno foca na usabilidade e proteção de dados:
* **Matrícula Inteligente:** O sistema de pré-inscrição verifica duplicidade de vínculos antes de efetivar a operação, impedindo matrículas duplicadas no mesmo curso.
* **Encapsulamento:** O acesso às notas e turmas é feito através de listas imutáveis (`Collections.unmodifiableList`), impedindo que a camada visual altere dados sensíveis indevidamente.

---

### 4. PRINCIPAIS DESAFIOS E SOLUÇÕES
Durante o desenvolvimento, o maior desafio técnico foi garantir a integridade dos dados entre os diferentes gerenciadores.
* **Problema:** Sincronizar a remoção de um aluno de uma turma, do sistema de login e da lista geral simultaneamente.
* **Solução:** Implementação de métodos centralizados no `GerenciadorAlunos` que orquestram a limpeza dos dados em cascata.

### 5. LIMITAÇÕES E ESCOPO FUTURO (ADENDO TÉCNICO)
Devido a restrições de prazo acadêmico e à decisão estratégica de priorizar a estabilidade do núcleo do sistema (Core), algumas funcionalidades foram mapeadas mas não integradas à versão final (v1.0):

1.  **Sistema de Armazenamento e Permanência de Dados:** Atualmente, o sistema opera com dados voláteis (na memória RAM). A implementação de persistência em arquivos (`.txt`, `.json` ou serialização) ou banco de dados foi arquitetada para a versão 2.0, visando garantir que os registros não sejam perdidos ao encerrar a execução do programa.
2.  **Tratamento Exaustivo de Inputs (`Scanner`):** Embora existam validações lógicas, o sistema não implementa tratamento completo para exceções de tipo de entrada (ex: inserção de caracteres em campos numéricos), o que pode gerar `InputMismatchException` em casos de uso indevido.
3.  **Módulos Financeiro e Pedagógico Avançado:** As seguintes funcionalidades foram postergadas para garantir a entrega de um MVP (Produto Viável Mínimo) sem bugs críticos:
    * Registro e controle de pagamentos e mensalidades.
    * Sistema de agendamento de aulas particulares.
    * Lógica automática de progressão de nível dos alunos baseada na média de notas.

### 6. CONCLUSÃO
O "WorldTalk System" cumpre os requisitos centrais de um sistema de gestão escolar robusto. A escolha pelo uso de estruturas de dados avançadas (Maps e Listas Dinâmicas) ao invés de vetores simples demonstra maturidade técnica, resultando em um software rápido, seguro e de fácil manutenção, preparado para futuras expansões conforme detalhado no escopo futuro.

---

