Tipos de arquitetura


Arquiteto tecnologico
    -> especializado em uma tech, java, salesforce, elastic, oracle...

Arquiteto de software
    -> ligado a engenharia de software
    -> ligado diretamente ao desenvolvimento
    -> impacta na formacao de times
    papel do arquiteto
        -> transforma requisitos de negocio em padroes arquiteturais
        -> orquestra desenvolvedores e experts de dominio
        -> entender de forma profunda conceitos e modelos arquiteturais
        -> auxilia na tomada de decisao
        -> reforca boas praticas de desenvolvimento
        -> code review

Arquiteto de solucao
    -> fica entre negocio e software
    -> requisitos em solucoes de software
    -> desenha arquitetura do software software
        -> C4
        -> UML
    -> analisa o impacto comercial a uma escolha de tecnologia
    -> participa do processo comercial
    -> analisa o impacto dos custos para o negocio

Arquiteto corporativa
    -> avalia custos
    -> padroniza tecnologias
    -> planeja implementacoes
    -> avalia novas tech

Requisitos arquiteturais
    performace
        ex:
            requisicao nao pode passar de 500ms
            suporta 50 requisicoes por segundo
    armazenamento de dados
        ex:
            leis lgpd
    escalabilidade
        horizontal
    seguranca
    legal
    auditoria
    marketing

Caracteristicas arquiteturais
    operacionais
        disponibilidade
            o que eu faco para garantir a disponibilidade do sistema?
        recuperacao de desastres
            caso ocorra um problema, quais sao os passos parar subir novamente
        performance
            requisicao nao pode passar de 500ms
            suporta 50 requisicoes por segundo
        recuperacao(backup)
            meu backup e confiavel? de qnt em qnt tempo, testo o backp?
        confiabilidade e seguranca
            protecao contra males
            meu endpoint suporta spam?
        robustes
            verticalmente = aumentar capacidade de maquina
            horizontal = aumentar as maquinas

    estruturais
        configuravel
            quanto mais facil realizar mudancas sem mexer no codigo, variaveis de ambiente por ex
        extensibilidade
            uso de interfaces e adaptadores, quao dificil esta para adicionar algo
        facil instalacao
        reuso de componentes
        internacionalizacao
        facil manutencao
        portabilidade (opcao de mudar gateways, bds...)
        facil suporte (logs, debug)

    cross-cutting (pontos q cruzam seu software)
        acessibilidade 
        processo de retencao e recuperacao de dados (quanto tempo os dados serao mantidos)
        autenticacao e autorizacao
        legal -> juridica
        privacidade
        seguranca
        usabilidade



perspectivas para arquitetar software de qualidade

    performance
        e o desempenho que um software possui para completar um determinado workload
        as unidades de medida para avaliar a performance de um software:
            latencia -> response time
            throughput -> quanto o software suporta
        ter um software perfomatico e diferente de ser escalavel

        praticas para melhorar perfomance
            diminuir response time(latencia)
                medida em miliseconds
                e afetada por tempo de processamento da aplicacao, redes, chamadas externas
            aumentando o throughput
                aumentando o quanto software suporta de requisicoes
                ele esta diretamente ligado a latencia 
        
        principais razoes de ter baixa performace
            procesamento ineficiente
            recursos computacionais limitados
            trabalhar de forma bloqueante
                receber uma requisicao trava minha aplicacao?
                ou seja, so qnd terminar de processar, ela podera receber outra?
            acesso serial a recursos
                fila de requisicoes, nao uso de multi thread

        principais formas de aumentar performace
            escala da capacidade computacional (cpu, memoria, rede, disco)
            logica por tras de software (algoritmos, queries, overhead de frameworks)
            concorrencia e paralelismo
            banco de dados (tipos de banco, schema)
            caching

    escalabilidade
        escala vertical
            aumento do recurso computacional
        escala horizontal
            aumento a quantidade de maquinas
        diferenca entre concorrencia e paralelismo
            Concorrencia e sobre lidar com muitas coisa ao mesmo tempo
            Paralelismo e fazer muitas coisas ao mesmo tempo
    
    caching
        cache na borda/edge computing
            fora do seu software/infra
                cloudflare ex: cacheia as paginas pra sua entrega
        dados estaticos
            -> edge computing
        pagina web
            -> edge computing
        funcoes internas
            evitar reprocessamento de funcoes desnecessarias
            acesso ao banco
        objetos

        Exclusivo vs compatilhado

        Exclusivo (local)
            Baixa latencia
            Duplicado entre nos
            problemas de sessoes

        Compartilhado centralizado
            Latencia maior
            nao ha duplicacao
            sessoes compartilhadas
            banco de dados externos
                mysql
                redis
                memcache
        
        caching: edge computing
            cache realizado mais proximo ao user
            evita a requisicao chegar ate o cloud provider / infra
            normalmente arquivos estaticos
            cdn -> content delivery network
                midgress -> cobra pra espalhar o conteudo na rede e a taxa de banda
            cloudflare workers
            vercel
            akamai
    
    escalabilidade
        escalabilidade e a capacidade de sistemas suportarem o aumento (ou a reducao) dos workloads incrementando
        (ou reduzindo) o custo em menor ou igual proporcao
    
        escalabilidade vs performance
            enquanto performance tem o foco em reduzir o response time e aumentar o throughput, a escalabilidade 
            visa termos a posibilidade de aumentar ou diminuir o throughput adicionando ou removendo a capacidade
            computacional
        
        escalando software
            descentralização 
                disco efemero
                    tudo que esta no disco pode ser perdido
                    usar apenas para arquivos temporarios
                servidor de aplicacao
                servidor de assets
                    contem arquivos fixos, css, jpg, html estatico, etc
                cache centralizado
                sessoes centralizadas
                upload/gravacao de arquivos
        
        escalando banco de dados
            aumentando recurso computacional
            distribuindo responsabilidades (escrita vs leitura)
            shards de forma horizontal
            serverless
            otimizacao de queries e indices
                UTILIZAR APM PARA APLICATION PERFORMACE MONITORING identificar os gargalos
                trabalhar com indices de forma consciente
                explain nas queries
                CQRS(command query responsability segretation)
                    separar escrita e leitura
        
        proxy reverso

    resiliencia
        e o conjunto de estrategias adotadas intecionalmente para a adaptacao de um sistema
        quando uma falha ocorre
            ou seja, falha um recurso, qual o plano b?

        estragegias
            health check
                verificar a saude da aplicacao, caso esteja baixa, parar de processar requisicoes
                um sistema c saude baixa pode se recuperar
                um ex: criar um endpoint c a media das ultiams requisicoes
                       um endpoint aonde faz uma busca no banco, se reporta em x ms, saude ok
            rate limiting
                protege o sistema baseado no que ele foi projetado pra suportar
                configurar pro client
                    micro service X possui 60 req
                    req geral 40 ao mesmo tempo
            circuit breaker
                protege o sistema fazendo com que as requisicoes feitas para ele sejam negadas
                circuito fechado = req chegam normalmente
                circuito aberto = req nao chegam, erro instantaneo
                meio aberto = permite uma quantidade limitada de requisicoes
            api gateway
                garante que requisicoes inapropriadas nem cheguem no sistema
                implementa politicas rate liming, health check, etc
        
        service mesh -> malha de servicos
            controla o trafego de rede
            evita implementacao de protecao pelo proprio sistema
            mTLS -> criptografa suas comunicacoes
            service mesh cria proxys para cada sistema
            prove
                circuit breaker, retry, timeout, fault injection, etc
        
        comunicacao assyncrona
            evita perda de dados
            nao ha perda de dados
            client -> messageria 
            application -> consumer -> messageria
        
        garantias de entrega com retry
            exponencial backoff
                variacao de tempo de espera de tentativa de requisicao
                req 1 falha, 1s, 2s, 4s
                o problema e que se tivermos 10 ao mesmo tempo, o problema persisitira
            exponencial backoff - jitter
                adicionamos um tempo variavel na requisicao, ou seja, vamos aumentar exponencialmente, mas c um ruido
                ou seja, 1.05s, 2.1s
                isso proteje nosso ambiente da situacao acima, dado q as requisicoes seram processadas em tempos diferentes
        
        garantias de entrega com broker (kafka)
            ack none -> apenas manda a mensagem, tem maior performace, mas tem o risco de perder VULGO fire and forget
            ack leader -> manda a mensagem, e recebe uma confimacao do lider, risco moderado
            ack all -> manda a mensagem, ela e replicada pra todos os brokers, e ai sim retorna a confirmacao
            confirmacao de recebimento