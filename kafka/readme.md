#kafka

alto throughput
baixa latencia 2ms
escalavel
armazenamento -> possui um bd para historico
alta disponibilidade
kafka se connecta com qse tudo
tem inumeras libs prontas pra kafka, se torna muito facil comecar usar
kafka open source

foi feito em java



#conceito basico kafka

producer -> kafka broker <- consumer

broker armazena a mensagem 
   consumer consome a mensagem do broker

recomendacao minima 3 broker

zookeper


kafka topic
   -> topico e o canal de comunicacao responsavel por receber e disponibilizar os dados enviados para o kafka

   topic
      particoes

   topico X fila
      topico pode ter diversos consumidores lendo, no rabbit ao ler uma mensagem essa mensagem e destruida, no kafka nao

   topic pode reprocessar as mensagens
   topicos sempre salvam em discos as mensagens, podem ser lidas e relidas

   rabbit trabalha em memoria

anatomia de um registro
   -> headers
      metadados, n obrigatorio
   -> keys
   -> value
   -> timestamp

particoes
   os topicos sao separados em particoes, cada particao cai em um broker, aumentando assim o throghput
   para garantir ordem, informar as keys

particoes distribuidas
   replication factor
      replicar particoes nos brokers

partition leadership
   elenca a particao lider de um broker

garantia de entrega
   producer
      -> ack 0 -> sem retorno de entrega de mensagem -> ff
         none
         -> garante maior processamento de mensagem
         -> pode perder mensagens no processo
         0% de garantia
      -> ack 1 -> retorno de entrega apenas no lider
         leader
         -> pode ter problema no broker e ainda nao ter replicado os followers
      -> ack -1 -> retorno de entrega do lider e followers
         all
         -> salva
         -> replica
         -> retorna
         -> 100% de garantia

   at most once: melhor performace, pode perder msgs
   at least once: performace moderada, pode duplicar mensagens
   exactly once: pior performace, garante a entrega

producer: indepotencia
   interpolation
      ON
         descarta mensagens duplicadas do producer
      OFF
         pode duplicar mensagens

consumer e consumer groups


converters
sink -> envia dados
source -> pega dados
transform -> transform dados antes de serem enviados para um conector sunk
converter -> converte dados

avro
protobuffer
json
jsoschema


dlq -> dead letter queue
errors.tolerance
   none: mata a task imediatamente
   all: erros sao ignorados e a task continua normalmente
   errors.deadletterqueue.topic.name = topic-name -> so pode ser usado no modo all
      toda vez que der um erro, a mensagem sera jogada em um topico
      o erro fica no header das mensagem


   