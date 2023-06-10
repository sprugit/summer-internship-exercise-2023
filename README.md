# Just Snail It - Summer internship exercise

## O problema

O caracol está ligado ao mundo da matemática faz muito tempo e agora vamos tentar com que ele entre no mundo da programação.
O caracol não é o animal mais rapido, nem muitas vezes o mais eficiente, mas o teu código deverá sê-lo.

![alt text](image.svg?raw=true)

Deverá ser implementado um mecanismo de desenho de uma casca de caracol. Dada uma matriz NxN, esse mecanismo deve retornar os elementos da matriz organizados, dos mais exteriores para os interiores, percorrendo no sentido horário.
```
array = [[1,2,3],
         [4,5,6],
         [7,8,9]]
getSnail(array) #=> [1,2,3,6,9,8,7,4,5]
```
O primeiro e único parâmetro do método é uma matriz de inteiros (int[][]) com os varios elementos.


## Regras

* O caminho deve começar na primeira posição da matriz ([0,0]) e seguir pelos ponteiros do relógio.
* A matriz vazia é representada por `[[]]`.


## O que é preciso fazer

* Implementar o método `getSnailShell` em `SnailShellPattern`.
* Implementar mais testes em `SnailShellPatternTest`

Vai ser valorizado a performance tanto para grandes matrizes como para pequenas, assim como o facto do código estar escrito de forma perceptível e organizada.

## Como correr os testes

* Instalar o mvn [https://maven.apache.org/install.html]
* Correr `mvn test`

## Solução

Aplicação da ideia da cebola: dividir o problemas em camadas

Para camada, usar threads para obter as linhas e colunas que compõem cada camada. Por fim a thread principal faz merge dos sub-arrays obtidos por cada array.

Auto-crítica: o código aqui apresentado poderia ser posteriormente melhorado e optimizado. As optimizações seriam sobretudo na junção dos resultados obtidos
de cada thread. A thread principal atinge um estado bloqueante quando está a juntar os arrays obtidos por cada uma das subthreads. Deste modo, ela não consegue
delegar novas tarefas paras as outras threads, sendo ineficiente deste modo.

Para este projeto foi utilizado o padrão Factory para gerar as diversas tarefas, através da classe SubTask (que deveria ter o nome SubTaskFactory!).


