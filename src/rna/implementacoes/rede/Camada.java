package rna.implementacoes.rede;

import rna.abstracoes.FuncaoAtivacao;
import rna.abstracoes.Neuronio;

import java.util.Arrays;

public class Camada {
    final private Neuronio[] neuronios;

    public Camada(int numEntradas, int numNeuronios, FuncaoAtivacao funcaoAtivacao, boolean aleatorio) {
        neuronios = new Neuronio[numNeuronios];
        for (int i = 0; i < numNeuronios; i++) {
            neuronios[i] = new NeuronioGenerico(funcaoAtivacao);
            neuronios[i].inicializarPesos(numEntradas, aleatorio);
        }
    }

    public double[] calcularSaidas(double[] entradas) {
        double[] saidas = new double[neuronios.length];
        for (int i = 0; i < neuronios.length; i++) {
            saidas[i] = neuronios[i].calcularSaida(entradas);
        }
        return saidas;
    }

    // Retropropagar erro para cada neurônio da camada
    public double[] retropropagarErro(double[] errosSaida, double taxaAprendizado) {
        double[] errosEntrada = new double[neuronios[0].getNumEntradas()];
        Arrays.fill(errosEntrada, 0.0);

        for (int i = 0; i < neuronios.length; i++) {
            double erroNeuronio = neuronios[i].calcularErro(errosSaida[i]);
            neuronios[i].ajustarPesos(taxaAprendizado);

            for (int j = 0; j < neuronios[i].getNumEntradas(); j++) {
                if (j < errosEntrada.length) {
                    errosEntrada[j] += neuronios[i].getPesos()[j] * erroNeuronio;
                }
            }
        }
        return errosEntrada;
    }

    public void ajustarPesos(double taxaAprendizado) {
        for (Neuronio neuronio : neuronios) {
            neuronio.ajustarPesos(taxaAprendizado);
        }
    }
}