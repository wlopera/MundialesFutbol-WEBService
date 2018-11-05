# MundialesFutbol - CAMPEONATOS MUNDIALES DE FUTBOL 

Exponer un Servicio Web. Que permita consultar datos de los campeonatos mundiales de Futbol.

Uso de CXF - STS - Eclipse

Uso de la interface 'ClientWSCApi', que permite simular la consulta al 'core de la compañia' para responder peticiones.


### Campeones mundiales
Listado de paises campeones de mundiales:

Entrada: 

Salida: 
* Campeonatos ganados
* Participaciones
* Años en que gano
* Sedes de los mundiales ganados

### Campeones por cantidad de campeonatos ganados
Lista de paises campeones de mundiales para una cantidad de campeonatos requerida

Entrada: 
* Cantidad de campeonatos ganados

Salida: 
* Campeonatos ganados
* Años en que gano
* Sedes de los mundiales ganados
		
### Campeón para un año requerido
Campeon mundial de ese año.

Entrada: 
* Año del mundial

Salida:
* Año
* Sede
* Equipo Campeón
* Equipo Sub - Campeón

## Requerimientos.
* Java 1.8
* STS - Eclipse
* Pivotal tc Server Developer Edition v4.0
* SoapUI (opcional)

## Crear de un proyecto web dinámico
> Nuevo Proyecto WEB - MundialesFutbol (eclipse), a desplegar en un servidor de Tomcat

![web1](https://user-images.githubusercontent.com/7141537/48009069-eabba280-e0e8-11e8-968b-ea195803d150.png)

## Implementar códigos

**1. Modelo de datos**

Clase que permite definir el modelo de datos; requeridos por el Servicio Web.

***
```
package com.wlopera.cliente.domain;

import java.io.Serializable;

/**
 * @author wlopera
 *
 */
public class Campeonato implements Serializable {
	
	private static final long serialVersionUID = 1093902254370601100L;
	
	private String nombreSede;
	private int annio;
	private String nombreCampeon;
	private String resultado;
	private String nonbreSubcampeon;

	public Campeonato() {
	}

	public Campeonato(String nombreSede, int annio, String nombreCampeon, String resultado, String nonbreSubcampeon) {
		this.nombreSede = nombreSede;
		this.annio = annio;
		this.nombreCampeon = nombreCampeon;
		this.resultado = resultado;
		this.nonbreSubcampeon = nonbreSubcampeon;
	}

	public String getNombreSede() {
		return nombreSede;
	}

	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}

	public int getAnnio() {
		return annio;
	}

	public void setAnnio(int annio) {
		this.annio = annio;
	}

	public String getNombreCampeon() {
		return nombreCampeon;
	}

	public void setNombreCampeon(String nombreCampeon) {
		this.nombreCampeon = nombreCampeon;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getNonbreSubcampeon() {
		return nonbreSubcampeon;
	}

	public void setNonbreSubcampeon(String nonbreSubcampeon) {
		this.nonbreSubcampeon = nonbreSubcampeon;
	}

	@Override
	public String toString() {
		return "Campeonato [nombreSede=" + nombreSede + ", annio=" + annio + ", nombreCampeon=" + nombreCampeon
				+ ", resultado=" + resultado + ", nonbreSubcampeon=" + nonbreSubcampeon + "]";
	}
}
```
***

**2. Manejo de datos**

Clase que permite simular el manejo de los datos - DUMMY - 
Para un caso mas real se puede obtener de una Base de datos.

***
```
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

```
***

**3. Interface**

Plantilla que permite definir las 'operaciones o servicios' requeridos por el Servicio Web

***
```
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
```
***

**4. Implementar la interface**

Implementar las 'operaciones o servicios' requeridas por el Servicio Web 

***
```
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

```
***

## Crear Servicio WEB
Generar el Servicio Web.

**1. Instalar y configurar apache-cxf**
* Instalar localmente Apache CXF --> D:\programs\apache-cxf-3.2.6
* Configurar Apache CXF en las preferencias de eclipse 

![config-1](https://user-images.githubusercontent.com/7141537/48011600-1f7e2880-e0ee-11e8-9f58-19e6f8addfaa.png)

**2. Uso de Wizard de eclipse: Crear nuevo Servicio Web - XML**
Seleccione la clase que implementa los servicios; click botón derecho del mouse y seleccionar wizard para generar el Servicio Web.

![wsdl](https://user-images.githubusercontent.com/7141537/48011113-00cb6200-e0ed-11e8-8c83-47ce4d18a33d.png)


**1. Definir Interface**
Definir los servicios a requeridos por el negocio

***
```

```
***
