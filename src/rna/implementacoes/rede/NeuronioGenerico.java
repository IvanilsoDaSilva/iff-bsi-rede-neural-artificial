package rna.implementacoes.rede;

import rna.abstracoes.Neuronio;
import rna.abstracoes.FuncaoAtivacao;

import java.util.Arrays;

public class NeuronioGenerico implements Neuronio {
    private double[] pesos;
    private double[] entradas;
    private double saida;
    private double gradiente;
    final private FuncaoAtivacao funcaoAtivacao;

    public NeuronioGenerico(FuncaoAtivacao funcaoAtivacao) {
        this.funcaoAtivacao = funcaoAtivacao;
    }

    @Override
    public void inicializarPesos(int numEntradas, boolean aleatorio) {
        pesos = new double[numEntradas + 1];
        for (int i = 0; i < pesos.length; i++) {
            pesos[i] = aleatorio ? (Math.random() - 0.5) * 0.1 : 0.0; // Inicialização com valores pequenos
        }
        System.out.printf("Pesos inicializados: %s%n", Arrays.toString(pesos));
    }

    @Override
    public double calcularSaida(double[] entradas) {
        this.entradas = new double[entradas.length + 1];
        this.entradas[0] = 1.0;
        System.arraycopy(entradas, 0, this.entradas, 1, entradas.length);

        double soma = 0.0;
        for (int i = 0; i < pesos.length; i++) {
            soma += pesos[i] * this.entradas[i];
        }
        saida = funcaoAtivacao.ativar(soma);
        System.out.printf("Entrada: %s | Pesos: %s | Soma: %.4f | Saída: %.4f%n",
                Arrays.toString(this.entradas), Arrays.toString(pesos), soma, saida);
        return saida;
    }

    @Override
    public double calcularErro(double erroEsperado) {
        double erro = erroEsperado - saida;
        gradiente = erro * funcaoAtivacao.derivada(saida);
        System.out.printf("Erro esperado: %.4f | Saída: %.4f | Erro calculado: %.4f | Gradiente: %.4f%n",
                erroEsperado, saida, erro, gradiente);
        return gradiente;
    }

    @Override
    public void ajustarPesos(double taxaAprendizado) {
        for (int i = 0; i < pesos.length; i++) {
            pesos[i] += taxaAprendizado * gradiente * entradas[i];
        }
        System.out.printf("Pesos ajustados: %s%n", Arrays.toString(pesos));
    }

    @Override
    public double[] getPesos() {
        return pesos;
    }

    @Override
    public int getNumEntradas() {
        return entradas.length - 1;
    }
}
