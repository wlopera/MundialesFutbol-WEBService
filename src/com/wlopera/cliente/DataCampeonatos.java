package com.wlopera.cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wlopera
 *
 */
public class DataCampeonatos {

	private static final DataCampeonatos instance = new DataCampeonatos();
	
	private Map<Integer, String> nombreSedes; // Sedes
	private Map<Integer, String> annios; // Annios
	private Map<Integer, String> nombreCampeones; // Campeones
	private Map<Integer, String> resultados; // Resultados de la finales
	private Map<Integer, String> nombreSubCampeones; // Sub Campeones

	public DataCampeonatos() {
		initData();
	}
	
	// retorna la instancia singleton creada
	public static DataCampeonatos getInstance() {
        return instance;
	}
	
	//============================
	// Metodos publicos 
	//=============================

	/**
	 * Main solo para prueba de la data 
	 * 
	 * @param arg
	 */
	public static void main(String[] arg) {
		DataCampeonatos data = new DataCampeonatos();
		data.initData();
		data.nombreSedes.forEach((x,y) -> System.out.println(y));
		data.annios.forEach((x,y) -> System.out.println(y));
		data.nombreCampeones.forEach((x,y) -> System.out.println(y));
		data.resultados.forEach((x,y) -> System.out.println(y));
		data.nombreSubCampeones.forEach((x,y) -> System.out.println(y));
	}

	//============================
	// Metodos privados 
	//=============================
	
	public Map<Integer, String> getNombreSedes() {
		return nombreSedes;
	}

	public Map<Integer, String> getAnnios() {
		return annios;
	}

	public Map<Integer, String> getNombreCampeones() {
		return nombreCampeones;
	}

	public Map<Integer, String> getResultados() {
		return resultados;
	}

	public Map<Integer, String> getNombreSubCampeones() {
		return nombreSubCampeones;
	}

	/**
	 * Inicializador de mapa de datos originales
	 * 
	 */
	private void initData() {
		nombreSedes = getMapaByPosition(getData(), 0);
		annios = getMapaByPosition(getData(), 1);
		nombreCampeones = getMapaByPosition(getData(), 2);
		resultados = getMapaByPosition(getData(), 3);
		nombreSubCampeones = getMapaByPosition(getData(), 4);
	}
	
	/**
	 * Retorna una mapa de datos segun la posicion de los datos en la lista de
	 * resultados originales de los campeonatos
	 * 
	 * @param data
	 * @param position
	 * @return
	 */
	private Map<Integer, String> getMapaByPosition(List<String[]> data, int position) {

		Map<Integer, String> map = new HashMap<>();
		int i = 1;

		for (String[] item : data) {
			map.put(i++, item[position]);
		}
		return map;
	}

	/**
	 * Data original de los campeones por sede, annio, campeon, resultado , campeon
	 * y sub-campeon
	 * 
	 * @return Lista de datos originales
	 */
	private List<String[]> getData() {

		List<String[]> data = new ArrayList<>();

		data.add(new String[] { "Uruguay", "1930", "Uruguay", "4-2", "Argentina" });
		data.add(new String[] { "Italia", "1934", "Italia", "2-1", "Checoslovaquia" });
		data.add(new String[] { "Francia", "1938", "Italia", "4-2", "Hungría" });
		data.add(new String[] { "Brasil", "1950", "Uruguay", "2-1", "Brasil" });
		data.add(new String[] { "Suiza", "1954", "Alemania", "3-2", "Hungría" });
		data.add(new String[] { "Suecia", "1958", "Brasil", "5-2", "Suecia" });
		data.add(new String[] { "Chile", "1962", "Brasil", "3-1", "Checoslovaquia" });
		data.add(new String[] { "Inglaterra", "1966", "Inglaterra", "4-2", "Alemania" });
		data.add(new String[] { "México", "1970", "Brasil", "4-1", "Italia" });
		data.add(new String[] { "Alemania", "1974", "Alemania", "2-1", "Holanda" });
		data.add(new String[] { "Argentina", "1978", "Argentina", "3-1", "Holanda" });
		data.add(new String[] { "España", "1982", "Italia", "3-1", "Alemania" });
		data.add(new String[] { "México", "1986", "Argentina", "3-2", "Alemania" });
		data.add(new String[] { "Italia", "1990", "Alemania", "1-0", "Argentina" });
		data.add(new String[] { "Estados Unidos", "1994", "Brasil", "3-2", "Italia" });
		data.add(new String[] { "Francia", "1998", "Francia", "3-0", "Brasil" });
		data.add(new String[] { "Corea/Japón", "2002", "Brasil", "2-0", "Alemania" });
		data.add(new String[] { "Alemania", "2006", "Italia", "5-3", "Alemania" });
		data.add(new String[] { "Sudáfrica", "2010", "España", "1-0", "Holanda" });
		data.add(new String[] { "Brasil", "2014", "Alemania", "1-0", "Argentina" });
		data.add(new String[] { "Rusia", "2018", "Francia", "4-2", "Croacia" });

		return data;
	}
}
