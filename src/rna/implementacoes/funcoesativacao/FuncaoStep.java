package rna.implementacoes.funcoesativacao;

import rna.abstracoes.FuncaoAtivacao;

public class FuncaoStep implements FuncaoAtivacao {
    private final double limiar;

    /**
     * Construtor da função Step.
     *
     * @param limiar O valor do limiar para decidir a ativação.
     */
    public FuncaoStep(double limiar) {
        this.limiar = limiar;
    }

    @Override
    public double ativar(double entrada) {
        // Retorna 1 se a entrada for maior ou igual ao limiar; caso contrário, retorna 0
        return entrada >= limiar ? 1.0 : 0.0;
    }

    @Override
    public double derivada(double saida) {
        // A derivada da função Step é indefinida em pontos de descontinuidade e zero fora deles
        // Aqui retornamos 0 para indicar a ausência de uma derivada útil para retropropagação
        return 0.0;
    }
}