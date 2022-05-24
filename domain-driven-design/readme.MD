Domain Driven Design DDD

O que e DDD?
E uma forma de desenvolver software com o foco no coracao da aplicacao, o que chamamos de dominio.
Tendo o objetivo de entender suas regras, processos e complexidades, separando-as assim de outros pontos complexos que normalmente
sao adicionados durante o processo de desenvolvimento

De onde vem o DDD?
Eric Evans, em 2003


Software Complexos
DDD deve ser aplicado em casos de projetos de softwares complexos


Domain -> esta ligado a parte principal do software, coracao do negocio
obiqus language -> projeto seguir um padrao de linguagem
Criar o design estrategico utilizando bounded contexts -> contextos delimitados


Domain e subdomain
Core domain ->  nucleo do negocio, essencial
Support SubDomain -> ajudam o dominio
Generic subdomain  -> softwares auxiliares

espaco do problema vs espaco da solucao

Problema -> visao geral do dominio e suas complexidades
quebrandos as complexidades em subdominios, e atacamos modelando os contextos

contextos delimitados -> bounded contexts 

padroes de context mapping

partnership
shared kernel
customer-supplier development
conformist
anti corruption layer
open host service
published language
separated ways
big ball of mud
https://github.com/ddd-crew/context-mapping

Entities -> identidade
Uma entidade e algo unico que e capaz de ser alterado de forma continua durante um longo periodo de tempo

Uma entidade e algo que possui uma continuidade em seu clico de vida e pode ser distinguida independente dos atributos que sao
importantes para aplicacao do usuario. pode se uma pessoa, cidade, ticket, carro, transacao

Entidades anemicas -> 
    entidades padroes criadas 
    -> get set delas mesmos, ela nao tem comportamento, geralmente orientado a ORM

Entidades ricas -> sao entidades baseadas nas regras de negocio, elas tem comportanto, validacoes
                    uma entidade rica deve sempre representar o estado atual e correto

Regras de negocios -> sao formas de mudar o comportamento de uma entidade rica

Entidade VS ORM
Entidade sao nossas regras, a "entidade do ORM" e o modelo de persistencia do banco de dados
SEMPRE ENTIDADES SAO NOSSAS REGRAS E NAO DEVEM ESTAR ACOPLADAS AO FRAMEWORK

Objetos de valor
Quando voce se preocupa apenas com os atributos de um elemento de um model, classifique isso como um Value Object
Trate o como imutavel
Assim como as nossas entidades ricas, eles devem sempre se auto validar
Nao possuem ID, pois nao usao unicos


aggregate
um agregado e um conjunto de objetos associados que tratamos como uma unidade para proposito de mudanca de dados


domain services
um servico de dominio e uma operacao sem estado que cumpre uma tarefa especifica do dominio.
muitas vezes, a melhor indicacao de que voce deve criar um servico no modelo de dominio e quando a operacao
que voce precisa executar parece nao se encaixar como um metodo em um agregado ou um objeto de valor
domain services sao stateless


domain events
- event - datetime
- handler: executa o processamento quando um evento e chamado
- dispatcher: responsavel por armazenar e executar os handlers de um evento quando ele for disparado

- criar um event dispatcher
- criar um event
- criar um handler para evento 
- registrar o evento, juntamento com o handler no event dispatcher

