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
