package rna.abstracoes;

public interface Neuronio {
    /**
     * Inicializa os pesos das sinapses deste neurônio.
     * <p>
     * Atualmente, os pesos podem ser inicializados aleatoriamente ou de acordo
     * com uma lógica interna, dependendo do valor do parâmetro **aleatorio**.
     *
     * @param numEntradas O número de entradas que o neurônio irá processar.
     * @param aleatorio   Se **true**, os pesos serão inicializados de forma aleatória.
     *                    Futuramente, substituir por pesos passados como parâmetro e
     *                    lançar uma **IllegalArgumentException** se o número de
     *                    entradas não corresponder ao número de pesos fornecidos.
     *                    - O array de pesos for **null**, caso em que os pesos serão
     *                      inicializados de forma aleatória.
     */
    void inicializarPesos(int numEntradas, boolean aleatorio);

    /**
     * Calcula e retorna a saída de um neurônio.
     * <p>
     * A saída é obtida a partir da fórmula da soma ponderada das entradas,
     * onde cada entrada é multiplicada pelo respectivo peso associado.
     * Fórmula: soma( pesos[i] * entradas[i] )
     *
     * @param entradas Um array contendo os valores das entradas do neurônio.
     *                 Cada valor corresponde a uma entrada específica.
     * @return A saída do neurônio após a aplicação da soma ponderada.
     */
    double calcularSaida(double[] entradas);

    /**
     * Calcula e retorna o erro do neurônio com base no erro esperado.
     * <p>
     * O erro é determinado comparando a saída atual do neurônio com o valor
     * esperado. Esta função é usada para ajustar os pesos durante o processo
     * de aprendizado, como parte do cálculo de retropropagação.
     *
     * @param erroEsperado O valor de erro esperado para o neurônio, ou seja, a diferença
     *                     entre a saída esperada e a saída obtida.
     * @return O valor do erro calculado, que será utilizado para ajustar os pesos.
     */
    double calcularErro(double erroEsperado);
    void ajustarPesos(double taxaAprendizado);
    double[] getPesos();
    int getNumEntradas();
}