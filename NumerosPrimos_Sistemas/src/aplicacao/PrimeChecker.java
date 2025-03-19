package aplicacao;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class PrimeChecker {

    // Método para verificar se um número é primo
    private static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // Método para ler números de um arquivo
    private static List<Integer> readNumbersFromFile(String fileName) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    numbers.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.out.println("Ignorando entrada inválida: " + line);
                }
            }
        }
        return numbers;
    }

    // Método para escrever números primos em um arquivo
    private static void writeNumbersToFile(List<Integer> primes, String fileName) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int prime : primes) {
                bw.write(prime + "\n");
            }
        }
    }

    // Implementação Sequencial
    private static long sequentialProcessing(String inputFile, String outputFile) throws IOException {
        List<Integer> numbers = readNumbersFromFile(inputFile);
        List<Integer> primes = new ArrayList<>();

        long startTime = System.currentTimeMillis();
        for (int num : numbers) {
            if (isPrime(num)) {
                primes.add(num);
            }
        }
        long endTime = System.currentTimeMillis();
        
        long duration = endTime - startTime;
        writeNumbersToFile(primes, outputFile);
        System.out.println("Tempo (Sequencial): " + (duration) + " ms");
        
        return duration;
    }

    // Implementação Paralela com Threads
    private static long parallelProcessing(String inputFile, String outputFile, int numThreads) throws IOException {
        List<Integer> numbers = readNumbersFromFile(inputFile);
        List<Integer> primes = Collections.synchronizedList(new ArrayList<>());

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        long startTime = System.currentTimeMillis();

        for (int num : numbers) {
            executor.execute(() -> {
                if (isPrime(num)) {
                    primes.add(num);
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.err.println("Execução interrompida!");
        }

        long endTime = System.currentTimeMillis();
        
        long duration = endTime - startTime;
        writeNumbersToFile(primes, outputFile);
        System.out.println("Tempo (Paralelo com " + numThreads + " threads): " + (duration) + " ms");
        
        return duration;
    }
    
    
    // Grava em txt os tempos de execução
    private static void writeOutput (long time_sequencial, long time_5_threads, long time_10_threads) {
    	String filePath = "..\\NumerosPrimos_Sistemas\\src\\time_output.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Escrevendo informações no arquivo
        	writer.println("Tempo (Sequencial): " + time_sequencial + " ms");
            writer.println("Tempo (Paralelo com 5 threads): " + time_5_threads + " ms");
            writer.println("Tempo (Paralelo com 10 threads): " + time_10_threads + " ms");
            
            System.out.println("\nInformações escritas no arquivo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    
    
    public static void main(String[] args) {
        String inputFile = "..\\NumerosPrimos_Sistemas\\src\\Entrada01.txt";
        long time_sequencial;
        long time_5_threads;
        long time_10_threads;
        try {
        	time_sequencial = sequentialProcessing(inputFile, "output_sequencial.txt");
        	time_5_threads = parallelProcessing(inputFile, "output_5_threads.txt", 5);
        	time_10_threads = parallelProcessing(inputFile, "output_10_threads.txt", 10);
        	writeOutput(time_sequencial, time_5_threads, time_10_threads);
        } catch (IOException e) {
            System.err.println("Erro ao processar arquivos: " + e.getMessage());
        }
    }
}
