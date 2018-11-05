# MundialesFutbol - CAMPEONES DE MUNDIALES

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

**1. Definir modelo de datos**

Definir el modelo de datos; requeridos por el Servicio Web.

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

**2. Definir Clase - Simular manejo de datos**

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

**3. Crear interface**

Definir las 'operaciones o servicios' requeridos por el Servicio Web

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

Implementar las 'operaciones' requeridas por el Servicio Web 

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

**1. Definir Interface**
Definir los servicios a requeridos por el negocio

***
```

```
***

**1. Definir Interface**
Definir los servicios a requeridos por el negocio

***
```

```
***

**1. Definir Interface**
Definir los servicios a requeridos por el negocio

***
```

```
***



















**1. Modificar en el portType WorldSoccerChampions, cambiar la operación NewOperation ->  WorldSoccerChampions**

![wsdl-2](https://user-images.githubusercontent.com/7141537/46909399-6dd94680-cef7-11e8-8d71-966def59b6b1.png)

**2. Cambiar el nombre del parámetro de entrada**

![wsdl-3](https://user-images.githubusercontent.com/7141537/46909400-72056400-cef7-11e8-857b-aa453e9e0b4e.png)

**3. Crear un XML Schema 'Team' para nuestros mensajes**

![wsdl-4](https://user-images.githubusercontent.com/7141537/46909401-7467be00-cef7-11e8-90fe-ace93c35aeae.png)

**4. Cambiar el parámetro de salida por un XML Schema 'Team'**

![wsdl-5](https://user-images.githubusercontent.com/7141537/46909404-7893db80-cef7-11e8-8fd1-11bea8888be6.png)

**5. Cambiar el endpoint del servicio web**

Nuestro servicio 'WorldSoccerChampions'. contiene el port (puerto); punto de entrada que implementa el binding, 
que a su vez está asociado al portType. 

Cambiar esa dirección (endpoint), poniendo la que luego usará Axis, 

http://www.example.org/ --> http://localhost:8080/WorldSoccerChampions/services/WorldSoccerChampionsSOAP

![wsdl-7](https://user-images.githubusercontent.com/7141537/46909642-5d2acf80-cefb-11e8-900d-bf43dbb7171f.png)

**Resultado Final**

![wsdl-6](https://user-images.githubusercontent.com/7141537/46909405-7a5d9f00-cef7-11e8-87ff-627e42a93df2.png)

## Generar el esqueleto de nuestro servicio

**1. Crear un nuevo servicio web (eclipse)**

![webservice-1](https://user-images.githubusercontent.com/7141537/46909623-fefdec80-cefa-11e8-89b2-3c47b7a21c0f.png)

Nota: este WSDL nos permite crear el cliente del servicio en cualquier plataforma

**Esquema generado**

![webservice-2](https://user-images.githubusercontent.com/7141537/46909624-00c7b000-cefb-11e8-8d61-d09dc8b2f5b0.png)

## Interface del servicio Consulta datos Campeones Mundiales

***
```
package org.wlopera.client;

import java.util.List;
import org.wlopera.WorldSoccerChampions.Team;

/**
 * @author wlopera
 *
 */
public interface ClientWSCApi {
	/**
	 * Simulador de Cliente que consultar la lista de paises ganadores de una
	 * cantidad especifica de mundiales de futbol
	 * 
	 * @param wonChampionships
	 *            Cantidad de campeonatos ganados
	 * 
	 * @return Listado de paises que solo han ganado esa cantidad exacta de
	 *         campeonatos
	 */
	List<Team> getListWSCByChampionWin(String wonChampionships);

	/**
	 * Simulador de Cliente que consulta todos los paises ganadores de campeones
	 * mundiales de futbol
	 * 
	 * @return Listado de paises gnadores de mundiales
	 */
	List<Team> getListWSCAll();

	/**
	 * Simulador de Cliente que consultar el pais ganador del mundial de un annio
	 * requerido
	 * 
	 * @param year
	 *            Annio del mundial
	 * 
	 * @return Pais que gano el campeonato ese annio
	 */
	Team getTeamWSCByYear(String year);
}
```
***

## Implementación del servicio Consulta datos Campeones Mundiales

***
```
/**
 * 
 */
package org.wlopera.client;

import java.util.List;
import org.wlopera.WorldSoccerChampions.Team;

/**
 * @author wlopera
 *
 */
public class ClientWSCService implements ClientWSCApi {
	
	@Override
	public List<Team> getListWSCByChampionWin(String wonChampionships) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Team> getListWSCAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team getTeamWSCByYear(String year) {
		DataTeams data = new DataTeams();
		for(Integer key : data.getYears().keySet()){
	        if(data.getYears().get(key).equals(year)){
	            System.out.println(key); 
	            System.out.println(data.getHeadquarters().get(key));
	            System.out.println(data.getYears().get(key));
	            System.out.println(data.getChampions().get(key));
	            System.out.println(data.getScores().get(key));
	            System.out.println(data.getSubChampions().get(key));
	            
	            return new Team(data.getHeadquarters().get(key), 
	            		Integer.parseInt(data.getYears().get(key)), 
	            		data.getChampions().get(key),
	            		data.getScores().get(key),
	            		data.getHeadquarters().get(key));
	        }
	     }	
		return null;
	}
	/**
	 * Main solo para prueba de la data 
	 * 
	 * @param arg
	 */
	public static void main(String[] arg) {
		new ClientWSCService().getTeamWSCByYear("2018");
	}	
}
```
***

## Clase que permite "obtener" los datos originales de los campeonatos mundiales ## 

Esta clase retorna datos originales de los resultados de los campeonatos y separa los mismos en mapas que contienen:
* Sedes de los mundiales
* Años de los mundiales
* Campeones de cada mundial
* Resultados de cada partidos finales
* Subcampeones de cada mundial 

Su objetivo es facilitar la data de los campeonatos mundiales para procesar las operaciones requeridas por la interface

***
```
package org.wlopera.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wlopera
 *
 */
public class DataTeams {

	private Map<Integer, String> headquarters; // Sedes
	private Map<Integer, String> years; // Annios
	private Map<Integer, String> champions; // Campeones
	private Map<Integer, String> scores; // Resultados de la finales
	private Map<Integer, String> subChampions; // Sub Campeones

	public DataTeams() {
		initData();
	}
	
	//============================
	// Metodos publicos 
	//=============================
	
	public Map<Integer, String> getHeadquarters() {
		return headquarters;
	}

	public Map<Integer, String> getYears() {
		return years;
	}

	public Map<Integer, String> getChampions() {
		return champions;
	}

	public Map<Integer, String> getScores() {
		return scores;
	}

	public Map<Integer, String> getSubChampions() {
		return subChampions;
	}

	/**
	 * Main solo para prueba de la data 
	 * 
	 * @param arg
	 */
	public static void main(String[] arg) {
		DataTeams data = new DataTeams();
		data.initData();
		data.headquarters.forEach((x,y) -> System.out.println(y));
		data.years.forEach((x,y) -> System.out.println(y));
		data.champions.forEach((x,y) -> System.out.println(y));
		data.scores.forEach((x,y) -> System.out.println(y));
		data.subChampions.forEach((x,y) -> System.out.println(y));
	}

	//============================
	// Metodos privados 
	//============================
	/**
	 * Inicializador de mapa de datos originales
	 * 
	 */
	private void initData() {
		headquarters = getMapaByPosition(getData(), 0);
		years = getMapaByPosition(getData(), 1);
		champions = getMapaByPosition(getData(), 2);
		scores = getMapaByPosition(getData(), 3);
		subChampions = getMapaByPosition(getData(), 4);
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


## Modificar clase WorldSoccerChampionsSOAPImpl ##
Esta clase es la que responde a las peticiones del usuario

Nota: Si se modifcia el WSDL y se regenera el codigo (esqueleto), se pierde el codigo modificado en esta clase.

***
```
/**
 * WorldSoccerChampionsSOAPImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.wlopera.WorldSoccerChampions;

import org.wlopera.client.ClientWSCApi;
import org.wlopera.client.ClientWSCService;

public class WorldSoccerChampionsSOAPImpl implements org.wlopera.WorldSoccerChampions.WorldSoccerChampions_PortType{
    public org.wlopera.WorldSoccerChampions.Team worldSoccerChampions(java.lang.String year) throws java.rmi.RemoteException {
    	ClientWSCApi clientWSCApi = new ClientWSCService();
    	return clientWSCApi.getTeamWSCByYear(year);
    }

}
```
***


## Levantar servidor y probar el servicio desde SOAPUI ##

![salida-1](https://user-images.githubusercontent.com/7141537/46909906-0bd10f00-cf00-11e8-96a3-31c143afa957.png)

