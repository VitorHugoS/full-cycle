SOLID

S Single responsabilty principle
O Open closed principle
L liskov substitution principle
I interface segregation principle
D dependecy inversion principle


SRP
uma classe deve ter apenas uma responsabilidade
Por exemplo, criar uma conexao, inserir dados, filtrar items

OCP
Aberta para extensao mas fechada para modificacao
O que e isso na pratica?

Por exemplos, temos um classe veiculo
A classe veiculo tem o metodo calcula ipva

Dentro dele, verificamos, carro, moto, caminhao
Se criamos um abstrato veiculo, e abstraimos pra cada um, temos uma performace infinitamente superior

LSP
Subclasses podem ser substituidas pela classe pai

ISP
Uma classe nao e obrigada a implementar todos os metodos de uma interace


DIP







