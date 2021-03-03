package br.com.springbank.rest.contrato;

/**
 * Contrato do adaptador primário hexagonal endpoint REST de teste.
 * @author Renan Muniz Merlo
 * @version 1.0 - 02/03/21
 * @since 02/03/21
 */
public interface TesteAdaptadorRest {

    public static final String TESTAR = "/testar";

    /**
     * Testa chamada rest do projeto
     */
    void testar(String nome);

}
