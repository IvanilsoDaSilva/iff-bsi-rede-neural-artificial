package rna.implementacoes.funcoesativacao;

import rna.abstracoes.FuncaoAtivacao;

public class FuncaoLinear implements FuncaoAtivacao {
    @Override
    public double ativar(double entrada) {
        return entrada;  // Função linear
    }

    @Override
    public double derivada(double saida) {
        return 1.0;  // Derivada da função linear
    }
}