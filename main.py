import threading
import time

# Função para verificar se um número é primo
def is_prime(n):
    if n <= 1:
        return False
    if n <= 3:
        return True
    if n % 2 == 0 or n % 3 == 0:
        return False
    i = 5
    while i * i <= n:
        if n % i == 0 or n % (i + 2) == 0:
            return False
        i += 6
    return True

# Função para processar uma lista de números e verificar primos
def process_numbers(numbers, result, start, end):
    for i in range(start, end):
        if is_prime(numbers[i]):
            result[i] = numbers[i]

# Implementação sequencial
def sequential_processing(numbers):
    result = [None] * len(numbers)
    process_numbers(numbers, result, 0, len(numbers))
    return result

# Implementação paralela com N threads
def parallel_processing(numbers, num_threads):
    result = [None] * len(numbers)
    threads = []
    chunk_size = len(numbers) // num_threads

    for i in range(num_threads):
        start = i * chunk_size
        end = (i + 1) * chunk_size if i != num_threads - 1 else len(numbers)
        thread = threading.Thread(target=process_numbers, args=(numbers, result, start, end))
        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()

    return result

# Ler os números do arquivo arquivo
def read_numbers_from_file(filename):
    with open(filename, 'r') as file:
        return [int(line.strip()) for line in file]

# Escrever resultados em um arquivo novo
def write_results_to_file(filename, results):
    with open(filename, 'w') as file:
        for number in results:
            if number is not None:
                file.write(f"{number}\n")

def main():
    input_file = 'Entrada01.txt'  # Arquivo de entrada
    output_files = {
        'sequential': 'saida_sequencial.txt',
        'parallel_5': 'saida_paralela_5.txt',
        'parallel_10': 'saida_paralela_10.txt'
    }

    # Ler números do arquivo de entrada
    numbers = read_numbers_from_file(input_file)

    # Processamento sequencial
    start_time = time.time()
    sequential_result = sequential_processing(numbers)
    sequential_time = time.time() - start_time
    write_results_to_file(output_files['sequential'], sequential_result)

    # Processamento paralelo com 5 threads
    start_time = time.time()
    parallel_5_result = parallel_processing(numbers, 5)
    parallel_5_time = time.time() - start_time
    write_results_to_file(output_files['parallel_5'], parallel_5_result)

    # Processamento paralelo com 10 threads
    start_time = time.time()
    parallel_10_result = parallel_processing(numbers, 10)
    parallel_10_time = time.time() - start_time
    write_results_to_file(output_files['parallel_10'], parallel_10_result)

    # Exibir tempos de execução
    print(f"Tempo sequencial: {sequential_time:.4f} segundos")
    print(f"Tempo paralelo (5 threads): {parallel_5_time:.4f} segundos")
    print(f"Tempo paralelo (10 threads): {parallel_10_time:.4f} segundos")

if __name__ == "__main__":
    main()