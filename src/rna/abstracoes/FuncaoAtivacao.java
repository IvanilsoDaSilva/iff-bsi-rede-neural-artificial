package rna.abstracoes;

public interface FuncaoAtivacao {
    double ativar(double entrada);

    /**
     * Calcula a derivada da função de ativação para um determinado valor de saída.
     * <p>
     * A derivada é usada para ajustar os pesos da rede neural durante o treinamento
     * por meio do processo de retropropagação. A derivada da função de ativação
     * é essencial para calcular os gradientes e, consequentemente, ajustar os pesos
     * de forma a minimizar o erro.
     *
     * @param saida O valor da saída atual da função de ativação para o qual a derivada
     *              deve ser calculada. Este valor é geralmente o resultado da aplicação
     *              da função de ativação em uma entrada.
     * @return A derivada da função de ativação no ponto especificado pela saída fornecida.
     *         Este valor é usado para atualizar os pesos da rede neural durante o treinamento.
     */
    double derivada(double saida);
}