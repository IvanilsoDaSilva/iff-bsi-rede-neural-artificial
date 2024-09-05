import rna.RedeNeural;
import rna.implementacoes.funcoesativacao.*;
import rna.implementacoes.rede.Camada;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        RedeNeural rede = new RedeNeural(0.5);

        // Camada de entrada -> 4 neurônios (de acordo com o número de entradas)
        Camada camadaEntrada = new Camada(
                4, 4, new FuncaoSigmoide(), true);
        rede.adicionarCamada(camadaEntrada); // 4 entradas para os neuronios de entrada

        // Camada escondida -> 2 neurônios
        Camada camadaEscondida = new Camada(
                4, 2, new FuncaoSigmoide(), true);
        rede.adicionarCamada(camadaEscondida); // 4 entradas da camada de entrada para dois neuronios escondidos

        // Camada de saída -> 2 neurônios (de acordo com o número de saídas esperadas)
        Camada camadaSaida = new Camada(
                2, 2, new FuncaoSigmoide(), true);
        rede.adicionarCamada(camadaSaida); // 2 entradas da camada escondida para dois neuronios de saida

        // Entradas de treinamento
        double[][] entradasTreinamento = {
                {1.0, 0.0, 1.0, 0.0}, // Gripe
                {0.0, 1.0, 0.0, 1.0}, // Dengue
                {0.0, 0.0, 1.0, 0.0}, // Gripe
                {0.0, 0.0, 0.0, 1.0}, // Dengue
        };

        // Saídas esperadas
        double[][] saidasEsperadas = {
                {1.0, 0.0}, // Gripe
                {0.0, 1.0}, // Dengue
                {1.0, 0.0}, // Gripe
                {0.0, 1.0}, // Dengue
        };

        // Treinamento da rede (número de épocas)
        int numeroEpocas = 1;
        rede.treinar(entradasTreinamento, saidasEsperadas, numeroEpocas);

        // Testando a rede após o treinamento
        double[] entradaTeste = {1.0, 0.0, 1.0, 0.0}; // Entrada de teste deve ter 4 valores
        double[] resultado = rede.calcularSaida(entradaTeste);
        System.out.println("Resultado para entrada de teste "
                + Arrays.toString(entradaTeste)
                + ": "
                + Arrays.toString(resultado));
    }
}