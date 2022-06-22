<h1 align="center"> O que e o docker? </h1>

<p align="justify">
    O docker e um container que empacota nossos códigos, criando um processo isolado.
</p>
<h2 align="center">E como isso funciona?</h2>

# O docker tem 3 pilares

1. Namespaces
    * o processo criado com um namespace e isolado e so se comunica c processos que tem o mesmo namespace, ou seja a comunicação so existe dentro deste processo.
2. Cgroups
    - Aqui e aonde definimos nosso recurso computacional para esse processo, como memoria, cpu, etc.
3. OFS(Overlay File System) -> Camadas
    * Aqui e aonde a magica do docker funciona, as camadas sao compartilhadas entre os processos, ou seja,
        se eu tenho uma camada do ubuntu, e eu precisar de uma nova imagem c ubuntu, as 2 compatilham entre si.

# Dockerfile

* O dockerfile e nosso snapshot, no docker file definimos o que usaremos, as camadas, e recursos, a partir disso, temos uma imagem
    imutavel, caso precisemos alterar, criaremos uma nova versao da imagem
    - Boas praticas
        - imagens devem seguir sempre um padrao  user/imagename:version
        * Isso e um padrao atoado pela comunidade e deve ser seguido
* Bem se o dockerfile e imutavel, como podemos colocar arquivos dentro dele?
    - O container quando sobe, tambem sobe uma camada de read/write, e enquanto esse container estiver rodando, 
        essa camada que fara a persistencia de dados, lembrando que quando eu matar esse container, tudo que estava nesta camada, 
        tambem sera apagado
    - Para salvar essas alteracoes, usamos o docker commit, quando este comando roda, ele cria uma nova versao da imagem, com
        a camada read-write ja persistida

# DockerRegistry
* Aqui e aonde fica nosso repositorio de imagens, uma vez que construimos nosso dockerfile, damos push para um registry, 
    depois disso basta darmos pull de qualquer ambiente que teremos a mesma imagem pronta para ser utilizada

# Docker por tras
    Temos 3 principios basicos
        DockerClient
            Aonde roda nossos comandos docker
                Container
                Run, pull, push, commit
                Volumes -> bind de volumes
                Network -> comunicacao entre os containers
        DockerHost
            Todos os comandos enviados pelo dockerclient, sao processados pelo daemon api, localizado no nosso docker host,
            ela que ira fazer a orquestracao dos recursos
                O docker host que controla
                    Volumes -> compartilha os volumes entre os containers e o host
                    Network -> controla a comunicao dos container
                    Cache
                        O cache e aonde ficara nosso armazenamento de imagens, como dito acima o docker roda em camadas, e essas camadas sao
                        compartilhadas entre si, e aqui e esse recurso e gerenciado e distribuido
        DockerRegistry
            Repositório de imagens
        Network
            bridge -> e o padrao usado, permite a comunicao do containers
            host -> mescla a network do host com a do container, ou seja usa a network do host diretamente
            overlay ->  bastante usado em cluster swarm -> criar um pool das redes e gerencia?
            maclan -> habilita um macaddress pro seu container, permitindo que ele seja visivel no host
            none -> isola o container, desabilita todo network
        host.docker.internal -> permite o container se comunicar com o host
            

# Comandos
    docker run -> cria um novo container a partir de uma imagem
            run e uma combinação de create + start
    docker start -> iniciar um container
    docker stop -> para um container em execução
    docker rm -> remove um container
    docker rm -f $(docker ps -a) -> remove todos os container, o -f forca a excluir
    docker exec -> executa comandos dentro do nosso container
    docker create -> cria um container de uma imagem
    docker build -> criar uma imagem por um dockerfile

# Tags
    -i -> modo interativo, mantém o processo rodando
    -t -> tty, permite enviarmos os comandos do nosso terminal
    --rm -> todo container que for criado, sera removido apos o processo terminar
    -d -> roda o container em background
    -p OR -publish -> bind a porta do host com o container
    -P OR -publish-all -> bind todas as portas que estão com expose no container, com portas aleatórias no host
    -expose -> expõe uma porta do container, isso permite a comunicação dos containers na rede dockerhost
    --name -> atribui um nome a um container
    -v OR -volume -> compartilha uma pasta do host para um container, ele cria a pasta caso n exista no host
    --mount type=bind -> ele bind um source->target do container, diferentemente do -v, ele da erro caso a pasta n exista no host
    --mount type=volume -> ele bind um volume->target do container, ou seja, pegamos um volume criado no, docker volume ls, e compartilhamos para a imagem
    -f -> passa o caminho do dockerfile

# Entrypoint VS CMD
    Entrypoint -> ele e um comando imutavel do seu docker file, ou seja ele sempre ira rodar e nao pode ser substituido por parametro
    CMD -> por default ele roda o comando do dockerfile, porem se passar algum parametro no run, ele ira substituir
