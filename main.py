import matplotlib.pyplot as plt

# Caminho do arquivo
arquivo = '../parallel-prime-numbers/src/time_output.txt'

# Função para ler os dados do arquivo
def ler_dados(arquivo):
    tempos = {}
    with open(arquivo, 'r') as file:
        for line in file:
            if 'Tempo' in line:
                key, value = line.strip().split(': ')
                tempos[key] = int(value.split()[0])
    return tempos

# Função para plotar o gráfico
def plotar_grafico(tempos):
    labels = list(tempos.keys())
    valores = list(tempos.values())

    plt.figure(figsize=(10, 5))
    plt.plot(labels, valores, marker='o', linestyle='-', color='b')
    plt.title('Tempo de Execução (ms)')
    plt.xlabel('Configuração')
    plt.ylabel('Tempo (ms)')
    plt.grid(True)
    plt.show()


# Ler os dados do arquivo
tempos = ler_dados(arquivo)

# Plotar o gráfico
plotar_grafico(tempos)