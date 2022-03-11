Comunicao entre sistemas

comunicacao sincrona
    comunicacao real time
        request e espera response

comunicacao asincrona
    comunicacao nao espera a resposta na hora


Rest -> Representation state of transfer -> ano 2000 -> stateless -> cacheavel

Niveis de maturidade

Nivel 0 -> the swamp of POX -> sem padronizacao -> RPC
Nivel 1 -> utilizacao de resources

Verbo GET, POST, PUT, DELETE

Nivel 2 -> 
GET -> recuperar informacao
POST -> inserir
PUT -> alterar
DELETE -> remover

Nivel 3 -> HATEOAS: Hypermedia as the Engine of Application State
Sempre responde seu endpoint, e ainda oferece tudo oq vc pode fazer
Sua API se torna auto explicavel

 HTTP/1.1 200 oOK
 Content-Type: application/vnd.acme.account+json
 Content-Length: ...
   "account": (
      "account_number": 12345,
      "balance": {
        "currency": "usd",
        "value": 100.00
      "links": {
        "deposit": "/accounts/12345/deposit",
        "withdraw": "/accounts/12345/withdraw",
        "transfer": "/accounts/12345/transfer",
        "close": "/accounts/12345/close"
      }
    }
   )

Method e content negotiation
• Utiliza URIS únicas para serviços e itens que expostos para esses serviços
• Utiliza todos os verbos HTTP para realizar as operações em seus recursos, incluindo caching
• Provê links relacionais para os recursos exemplificando o que pode ser feito


Rest: HAL, Collection + JSON, Siren
JSON nao prove um padrao de hipermidia para realizar a linkagem
HAl hypermedia application language
siren

Media type = application/hal+json
                 "_links":{0
                   "self" :(e
                     "href": "http://fullcycle.com.br/api/user /wesley"
                 "id" : "wesley",
                 "name": "Wesley Willias",
                 "_embedded" :{8
                   "familly" :[B
                         "_links" :{0
                           "self" :(0
                             "href": "http://fullcycle.com.br/api/user/mari"
                         }.
                         "id": "mari",
                         "name" : "Mariana"
                }
            ]
        }
    }

REST: HTTP Method Negotiation
HTTP possui um outro método: OPTIONS. Esse método nos permite informar quais métodos são
permitidos ou não em determinado recurso.
OPTIONS /api/product HTTP/1.1
Host: fullcycle.com.br
Resposta pode ser:
HTTP/1.1 200 OK
Allow: GET, POST
Caso envie a requisição em outro formato:
HTTP/1.1 405 Not Allowed
Allow: GET, POST

REST: Content Negotiation
O processo de content negotiation é baseado na requisição que o client está fazendo para o server. Nesse
caso ele solicita o que e como ele quer a resposta. O server então retornará ou não a informação no
formato desejado.
Accept Negotiation
- Client solicita a informação e o tipo de retorno pelo server baseado no media type informado por ordem
de prioridade.
GET /product
Accept: application/json
Resposta pode ser o retorno dos dados ou:
HTTP/1.1 406 Not Acceptable

REST: Content Negotiation
Content-Type Negotiation
- Através de um content-type no header da request, o servidor consegue verificar se ele irá conseguir
processar a informação para retornar a informação desejada.
POST /product HTTP/1.1
Accept: application/json
Content-Type: application/json
{
"name":"Product 1"
Caso o servidor não aceite o content type, ele poderá retornar:
HTTP/1.1 415 Unsupported Media Type