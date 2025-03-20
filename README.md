
# PROJETO
O projeto desenvolvido tem o objetivo de realizar um estudo do comportamento de das threads através de um objetivo de realizar a leitura de números de um arquivo de entrada e determinando quais desses números (tendenciosamente gerados de forma "aleatória") são números primos.
## "O que são números primos?"
Números primos são números que não possue outros divisores além de do numeral "1" e o próprio número. Por exemplo: O número 2 é primo pois ele é apenas passivo de divisão apenas do número 1 e do próprio número 2. Já o número 4 deixa de ser primo pela sua divisão ser passível do número 1, do próprio número 4, mas também tem 2 como um possível divisor, o que foge o conceito de número primo.

## Detalhes da Tarefa

Sendo explicado apropriadamente o que são os números primos, o projeto engloba justamente os mesmo que foram explicados. Através da utilização de threads que venham identificar, no arquivo de entrada, quais números são de fato números primos, descartando os números que não se encaixam no conceito. Gerando arquivos das saídas, as saídas apresentam esses números na mesma ordem que foram identificados.

Outro ponto importante de ser citado sobre o projeto é: O projeto engloba 3 implementações, sendo elas utilizando apenas 1 thread, 5 threads e 10 threads, no objetivo de estudar seu desempenho e velocidade na leitura, fazendo também o estudo do comportamento das threads e da escolha do Sistema Operacional para realizar a tarefa. Também é gerado um gráfico para que esse estudo seja feito de uma forma mais visual, além da exibição do tempo que foram realizados cada uma dessas implementações.
## Explicação do Funcionamento dos Códigos

O projeto apresenta 2 códigos aplicados para realizar, por completo, a execução da tarefa: o código que realiza a geração e execução das threads e o código que realiza a tradução dos dados de saída para gerar os gráficos.

O código de geração de gráficos foi desenvolvido em Python, que permite, com a biblioteca matplotlib, a criação facilitada e edição dos gráficos gerados pelos dados concedidos pelo código que realiza a tarefa de fato. Realizando "o input dos outputs", é gerado um gráfico que permite a comparação dos tempos de realização das execuções para estudo, sendo um gráfico "1 thread vs 5 threads vs 10 threads".

O código do exercício das threads, realizado em Java, é a grande chave para a experimentação implementada. Inicialmente, é recebido o arquivo de input (entrada) que, para esse projeto, utilizaremos o arquivo concedido pelo professor (que instruiu a tarefa) chamado de "Entrada01.txt", sendo delcarado como "inputFile" para que possa ser uma variável que carregue os dados em uma variável String e parâmetro para os diversos métodos. Há também a declaração de 3 variáveis do tipo "long" que serão impressos os tempos dos processamentos das threads, serão gerados os arquivos de output (saída) com os números primos identificados e na mesma ordem em que foram lidos. Dentro dos blocos de código "try/catch", será uma "proteção" de código que faz a análise se os arquivos serão gerados corretamente e evitar erros como arquivos vazios, conteúdos que não sejam o apropriado, etc. Caso não estejam corretos, projeta uma mensagem de erro com tratamento de exceção.

- Métodos aplicados

Bibliotecas: As bibliotecas importadas são a java.io, java.util, java.util.concurrent;

A java.io será utilizada para manipulação de dados de entrada e saída em arquivos, já a java.util será utilizada para utilitários como as listas e manipulação de dados, enquanto java.util.concurrent será utilizada para a programação concorrente, oferecendo controles sobre as threads e seu gerenciamento.

Método isPrime(int num): um método com uma lógica simples de realizar a checagem se o número inteiro recebido como parâmetro no método é um número primo ou não, sendo um método estático (que pode ser utilizado em toda a classe), booleano (retornando se o número é verdadeiramente primo ou não) e privado (que somente pode ser acessado pela classe presente).

Método readNumbersFromFile(String fileName): o método estático e privado é do tipo List (lista) de inteiros, com o parâmetro de recebimento o arquivo de input, registrando os números inteiros que estão dentro do arquivo de enmtrada, utilizando um buffer de leitura para armazenagem em um arraylist, retornando a lista de inteiros.

Método writeNumbersToFile(List<Integer> primes, String fileName): O método estático e privado é do tipo Void (Vazio) pois se trata de um método que servirá apenas para a escrita dos números inteiros que são primos em um arquivo .txt a parte utilizando um buffer de escrita, tendo como parâmetro a lista de inteiros dos números primos e o nome do arquivo.

Método sequentialProcessing(String inputFile, String outputFile): o método estático e privado do tipo Long, recebendo como parâmetro o arquivo de input e o arquivo de saída com retorno da duração da execução, instancia uma lista para os números de input e um arraylist para os números primos que forem identificados pela thread que fará o processamento sequencial, ou seja, uma única thread irá fazer a execução da atividade proposta. Além de executar a atividade, é feito o registro do tempo em ms (milissegundos) para que seja comparado o processamento com os processamentos paralelos.

Método parallelProcessing(String inputFile, String outputFile, int numThreads): O método estático e privado do tipo Long, como parâmetro o arquivo de entrada, o arquivo de saída e o número de threads que serão utilizadas para a execução da atividade. A grade diferença entre os processamentos, além do número de threads que estão trabalhando, será a sua organização de processamento para sincronização das execuções, utilizando o princípio do "synchronizedList" para criar objetos protegidos no processamento. Há também um executor que fará o processo de iniciação das threads e sua "Pool" para que haja o ocorrimento das mesmas. Com isso, semelhante ao processo sequencial, também será feita a captura do tempo da execução com base na quantidade de threads propostas para a tarefa e será escrito os números primos que forem identificados.

Método writeOutput(long time_sequencial, long time_5_threads, long time_10_threads): O método estático e privado do tipo Void, como parâmetro o tempo de cada execução de processamento e sendo escrito em um arquivo do tipo .txt o registro dos tempos para comparação, de forma que possa ser utilizada em um gráfico que será plotado em um código em Python.

## Gráfico comparativo

É possível comparar o desempenho das três abordagens analisadas. O processamento sequencial foi o mais lento, com um tempo de execução de 174 ms, pois verifica cada número de forma individual, sem explorar o paralelismo. Já a abordagem com 5 threads reduziu esse tempo para 95 ms, resultando em um ganho de 45.4%, ao dividir a carga de trabalho entre múltiplas threads. O uso de 10 threads melhorou ainda mais o desempenho, reduzindo o tempo para 78 ms e proporcionando uma redução total de 55.2% em relação à execução sequencial.

O paralelismo se mostrou eficiente para essa tarefa, permitindo uma execução mais rápida ao distribuir o processamento entre várias threads. No entanto, o desempenho depende de fatores como o número de núcleos da CPU e o tamanho do conjunto de dados. Em sistemas com poucos núcleos, um alto número de threads pode gerar concorrência excessiva, impactando negativamente o desempenho. Além disso, para conjuntos de dados pequenos, o o custo adicional de gerenciamento de threads pode não compensar o ganho de velocidade, tornando o processamento sequencial mais viável. Já para grandes volumes de dados, o paralelismo é essencial para reduzir o tempo de execução. Para obter o melhor desempenho, é recomendável testar diferentes quantidades de threads e considerar o hardware disponível.

![imagem_2025-03-19_232759309](https://github.com/user-attachments/assets/b36c711f-f866-4df0-90b2-ebefd67e1d64)


## Conclusão

Com isso, esse é o detalhamento e a explicação de cada trecho do código para que se haja entendimento completo de como os processos são feitos e como os métodos dentro de todo o código executado possam ter ligação entre si, com envio e recebimentos de parâmetros para executar as tarefas e realizar o registro dos resultados oriundos dos processamentos.
## Autores

- [@diegoperpetuo](https://www.github.com/diegoperpetuo): Diego Perpétuo Andrade de Oliveira
- [@DSantana04](https://www.github.com/DSantana04): Danilo Santana Garcia
- [@Miltchola](https://www.github.com/Miltchola): Milton Kiefer de Albuquerque Mello

