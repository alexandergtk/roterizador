# roterizador


+ Tenologia (Spring, Lombok, JPA, Hibernate, Mysql)

A escolha se deu por ter vivenciado alguns projetos os quais utilizavam essa arquitetura, utilizei tomcat a estrutura de tabela ficou a cargo do hibernate montar, a escolha pela utilização do lombok foi apenas p/ deixar as entidades mais limpas, apenas isso.
Por meio de pesquisa, encontrei o algoritmo dijkstra, o que achei mais facil para implementação.
Fontes:
  http://www.inf.ufsc.br/grafos/temas/custo-minimo/dijkstra.html
  http://www.ime.usp.br/~pf/algoritmos_para_grafos/aulas/dijkstra.html

+ Execução do projeto
    Instalar -> Mysql
Criar inicialmente: 
 Base de dados roteriza 
 Usuário: root
 Senha:   root

Após feito os procedimento acima, basta rodar Application.java, a criação das tabelas e relações será criada automaticamente.

Api
URL: /rotas
Método POST
Método GET
Método PUT
