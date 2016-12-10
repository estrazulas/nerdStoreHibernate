package br.edu.ifsc.nerdstore.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.edu.ifsc.nerdstore.dao.ProdutoDAO;
import br.edu.ifsc.nerdstore.modelo.Produto;

@ManagedBean
@ViewScoped
public class VendasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public BarChartModel getVendasModel() {

	    BarChartModel model = new BarChartModel();
	    model.setAnimate(true);
	    ChartSeries vendaSerie = new ChartSeries();
	    vendaSerie.setLabel("Vendas 2016");

	    List<Produto> produtos = ProdutoDAO.getInstance().buscaPorSimilaridade(null);

	    for (Produto produto : produtos) {
	    	int x = new Random().nextInt(1000)+25;
	        vendaSerie.set(produto.getNome(), x);
	    }

	    model.addSeries(vendaSerie);
	    return model;
	}
}