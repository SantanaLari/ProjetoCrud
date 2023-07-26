import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProdutoControl {

	private ObservableList<Produto> produto = FXCollections.observableArrayList();
	private LojaDAOImpl dao = new LojaDAOImpl();
	private StringProperty id = new SimpleStringProperty();
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty tipo = new SimpleStringProperty();
	private StringProperty preco = new SimpleStringProperty();
	
	public StringProperty idProperty() {
		return id;
	}
	
	public StringProperty nomeProperty() {
		return nome;
	}
	
	public StringProperty tipoProperty() {
		return tipo;
	}
	
	public StringProperty precoProperty() {
		return preco;
	}
	
	public void salvar() {
		Produto p = new Produto();
		p.setId(id.get());
		p.setNome(nome.get());
		p.setPreco(preco.get());
		p.setTipo(tipo.get());
		dao.salvar(p);
	}
	
	public void excluir() {
		Produto p = new Produto();
		p.setId(id.get());
		dao.excluir(p);
		listar();
	}
	
	public void atualizar() {
		Produto p = new Produto();
		p.setId(id.get());
		p.setNome(nome.get());
		p.setPreco(preco.get());
		p.setTipo(tipo.get());
		dao.atualizar(p);
		listar();
	}
	
	public void listar() {
		List<Produto> encontrados = dao.listar(nome.get());
		produto.clear();
		produto.addAll(encontrados);
	}
	
	public ObservableList<Produto> getProduto(){
		return produto;
	}
}
