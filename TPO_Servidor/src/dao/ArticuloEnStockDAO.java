package dao;

import negocio.ArticuloEnStock;

public class ArticuloEnStockDAO {
	private static ArticuloEnStockDAO instancia;
	
	private ArticuloEnStockDAO(){}
	
	public static ArticuloEnStockDAO getInstance(){
		if(instancia==null)
			instancia= new ArticuloEnStockDAO();
		return instancia;
	}
	
	public void grabar(ArticuloEnStock ae){
		
		
	}
}
