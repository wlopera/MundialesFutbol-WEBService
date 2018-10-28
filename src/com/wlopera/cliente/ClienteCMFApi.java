package com.wlopera.cliente;

import com.wlopera.cliente.domain.Campeonato;

/**
 * @author wlopera
 *
 */
public interface ClienteCMFApi {

	/**
	 * Simulador de Cliente que consultar la lista de paises ganadores de una
	 * cantidad especifica de mundiales de futbol
	 * 
	 * @param numeroCMFGanados
	 *            Cantidad de campeonatos ganados
	 * 
	 * @return Arreglo de paises que solo han ganado esa cantidad exacta de
	 *         campeonatos
	 */
	Campeonato[] getListaCMFPorCampeonatoGanado(String numeroCMFGanados);

	/**
	 * Simulador de Cliente que consulta todos los paises ganadores de campeones
	 * mundiales de futbol
	 * 
	 * @return Arreglo de paises gnadores de mundiales
	 */
	Campeonato[] getListaCMFTodos();

	/**
	 * Simulador de Cliente que consultar el pais ganador del mundial de un annio
	 * requerido
	 * 
	 * @param year
	 *            Annio del mundial
	 * 
	 * @return Pais que gano el campeonato ese annio
	 */
	Campeonato getCMFPorAnnio(String annio);

}
