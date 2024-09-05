package rna;

import rna.implementacoes.rede.Camada;

import java.util.ArrayList;
import java.util.List;

public class RedeNeural {
    final private List<Camada> camadas;
    final private double taxaAprendizado;

    public RedeNeural(double taxaAprendizado) {
        camadas = new ArrayList<>();
        this.taxaAprendizado = taxaAprendizado;
    }

    public void adicionarCamada(Camada camada) {
        camadas.add(camada);
    }

    // Propagação direta
    public double[] calcularSaida(double[] entradas) {
        double[] saidas = entradas;
        for (Camada camada : camadas) {
            saidas = camada.calcularSaidas(saidas);
        }
        return saidas;
    }

    // Método de treinamento usando a retropropagação
    public void treinar(double[][] entradasTreinamento, double[][] saidasEsperadas, int epocas) {
        for (int epoca = 0; epoca < epocas; epoca++) {
            double erroTotal = 0;
            for (int i = 0; i < entradasTreinamento.length; i++) {
                // Passo 1: Propagação direta
                double[] saidaCalculada = calcularSaida(entradasTreinamento[i]);

                // Passo 2: Calcular erro (usando erro quadrático)
                double[] errosSaida = new double[saidasEsperadas[i].length];
                for (int j = 0; j < saidasEsperadas[i].length; j++) {
                    errosSaida[j] = saidasEsperadas[i][j] - saidaCalculada[j];
                    erroTotal += Math.pow(errosSaida[j], 2);
                }

                // Passo 3: Retropropagação do erro
                retropropagarErro(errosSaida);

                // Passo 4: Ajuste dos pesos
                ajustarPesos();
            }
            System.out.println("Época: " + (epoca + 1) + " - Erro Total: " + erroTotal / entradasTreinamento.length);
        }
    }

    // Método de retropropagação do erro
    private void retropropagarErro(double[] erroSaida) {
        double[] erros = erroSaida;
        for (int i = camadas.size() - 1; i >= 0; i--) {
            erros = camadas.get(i).retropropagarErro(erros, taxaAprendizado);
        }
    }

    // Método de ajuste dos pesos
    private void ajustarPesos() {
        for (Camada camada : camadas) {
            camada.ajustarPesos(taxaAprendizado);
        }
    }
}