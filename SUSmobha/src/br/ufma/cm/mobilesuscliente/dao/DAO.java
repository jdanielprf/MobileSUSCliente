package br.ufma.cm.mobilesuscliente.dao;

import android.database.sqlite.SQLiteDatabase;
import br.ufma.cm.mobilesuscliente.entidades.Chamado;

public class DAO {
	private SQLiteDatabase db;
	public boolean salvarChamado(Chamado c){
		return true;
	}
	public Chamado lerChamado(){
		return null;
	}
	public String lerID(){
		return "";
	}
	public void salvarID(String ID){
		 
	}
	
	public void criar(String ID){
		 db.execSQL("CREATE TABLE IF not EXISTS Chamado(id varchar(255),descricao varchar(1000),data varchar(1000),status varchar(1000));");
		 db.execSQL("CREATE TABLE IF not EXISTS ID(id varchar(255));");
	}
}
