/**
 * 
 */
package com.wlopera.cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.wlopera.cliente.domain.Campeonato;

/**
 * @author wlopera
 *
 */
@WebService(targetNamespace = "http://cliente.wlopera.com/", portName = "ClienteCMFServicioPort", serviceName = "ClienteCMFServicioService")
public class ClienteCMFServicio implements ClienteCMFApi {

	// http://localhost:8080/CampeonatosMundialesFutbol/services/ClienteCMFApi?wsdl

	@Override
	public Campeonato[] getListaCMFPorCampeonatoGanado(String numeroCMFGanados) {

		DataCampeonatos data = DataCampeonatos.getInstance();
		
		List<Campeonato> campeonatosGanados = new ArrayList<>();
		Map<String, List<Campeonato>> mapa = new HashMap<>();
		Campeonato campeonato = null;

		for (int i = 1; i < data.getNombreSedes().size() + 1; i++) {

			campeonato = new Campeonato(data.getNombreSedes().get(i), Integer.parseInt(data.getAnnios().get(i)),
					data.getNombreCampeones().get(i), data.getResultados().get(i), data.getNombreSubCampeones().get(i));

			if (mapa.containsKey(campeonato.getNombreCampeon())) {
				mapa.get(campeonato.getNombreCampeon()).add(campeonato);
				continue;
			}

			List<Campeonato> campeonatos = new ArrayList<>();
			campeonatos.add(campeonato);
			mapa.put(campeonato.getNombreCampeon(), campeonatos);
		}

		mapa.forEach((k, v) -> {
			if (numeroCMFGanados.equals(String.valueOf(v.size()))) {
				campeonatosGanados.addAll(v);
			}
		});

		return campeonatosGanados.toArray(new Campeonato[campeonatosGanados.size()]);
	}

	@Override
	public Campeonato[] getListaCMFTodos() {

		DataCampeonatos data = DataCampeonatos.getInstance();

		List<Campeonato> campeonatos = new ArrayList<>();

		for (int i = 1; i < data.getNombreSedes().size() + 1; i++) {
			campeonatos.add(new Campeonato(data.getNombreSedes().get(i), Integer.parseInt(data.getAnnios().get(i)),
					data.getNombreCampeones().get(i), data.getResultados().get(i),
					data.getNombreSubCampeones().get(i)));
		}

		return campeonatos.toArray(new Campeonato[campeonatos.size()]);
	}

	@Override
	public Campeonato getCMFPorAnnio(String annio) {

		DataCampeonatos data = DataCampeonatos.getInstance();
		
		for (Integer key : data.getAnnios().keySet()) {
			if (data.getAnnios().get(key).equals(annio)) {
				return new Campeonato(data.getNombreSedes().get(key), Integer.parseInt(data.getAnnios().get(key)),
						data.getNombreCampeones().get(key), data.getResultados().get(key),
						data.getNombreSubCampeones().get(key));
			}
		}

		return new Campeonato();
	}

	/**
	 * Main solo para prueba de la data
	 *
	 * @param arg
	 */
	public static void main(String[] arg) {
		new ClienteCMFServicio().getCMFPorAnnio("2018");

		// System.out.println(new ClienteCMFServicio().getListaCMFTodos());

		// System.out.println(new
		// ClienteCMFServicio().getListaCMFPorCampeonatoGanado("2"));

	}

}
