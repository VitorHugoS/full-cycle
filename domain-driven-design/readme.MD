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