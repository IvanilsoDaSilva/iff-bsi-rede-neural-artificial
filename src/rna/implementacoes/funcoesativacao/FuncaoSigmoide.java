package rna.implementacoes.funcoesativacao;

import rna.abstracoes.FuncaoAtivacao;

public class FuncaoSigmoide implements FuncaoAtivacao {
    @Override
    public double ativar(double entrada) {
        return 1.0 / (1.0 + Math.exp(-entrada));
    }

    @Override
    public double derivada(double saida) {
        return saida * (1 - saida);
    }
}