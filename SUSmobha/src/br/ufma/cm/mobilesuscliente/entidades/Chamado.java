package br.ufma.cm.mobilesuscliente.entidades;

import java.util.Date;

public class Chamado {
	public static String STATUS_ABERTO="ABERTO";
	public static String STATUS_FECHADO="FECHADO";
	public static String STATUS_EM_ATENDIMENTO="EM ATENDIMENTO";
	public static String STATUS_INDETERMINADO="INDETERMINADO";
	
	private String descricao;
	private String latitude;
	private String longitude;
	private String status=STATUS_ABERTO;
	private String id;
	private Date data;

	private String relatorio;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ID:" + id;
	}

	public String getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}
}
