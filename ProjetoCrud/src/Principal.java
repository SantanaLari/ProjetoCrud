import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Principal extends Application {

	private Label lblId = new Label("Id: ");
	private Label lblNome = new Label("Nome: ");
	private Label lblPreco = new Label("Preco: ");
	private Label lblTipo = new Label("Tipo: ");
	private TextField txtId = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtPreco = new TextField();
	private TextField txtTipo = new TextField();
	private ProdutoControl control = new ProdutoControl();
	private Button btnSalvar = new Button("Salvar");
	private Button btnListar = new Button("Listar");
	private Button btnExcluir = new Button("Excluir");
	private Button btnAtualizar = new Button("Atualizar");
	private TableView<Produto> table = new TableView<>();

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane principal = new BorderPane();
		GridPane grid = new GridPane();
		principal.setTop(grid);
		principal.setCenter(table);
		Scene scn = new Scene(principal,400,400); 
		
		//coluna, linha
		grid.add(lblId, 0, 0);
		grid.add(lblNome, 0, 1);
		grid.add(lblPreco, 0, 2);
		grid.add(lblTipo, 0, 3);
		
		grid.add(txtId, 1, 0);
		grid.add(txtNome, 1, 1);
		grid.add(txtPreco, 1, 2);
		grid.add(txtTipo, 1, 3);
		
		grid.add(btnSalvar, 0, 5);
		grid.add(btnListar, 1, 5);
		grid.add(btnExcluir, 2, 5);
		grid.add(btnAtualizar, 3, 5);
		
		Bindings.bindBidirectional(control.idProperty(), txtId.textProperty());
		Bindings.bindBidirectional(control.nomeProperty(), txtNome.textProperty());
		Bindings.bindBidirectional(control.precoProperty(), txtPreco.textProperty());
		Bindings.bindBidirectional(control.tipoProperty(), txtTipo.textProperty());
		
		TableColumn<Produto, String> col1 = new TableColumn<>("Id");
		col1.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Produto, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Produto, String> col3 = new TableColumn<>("Preco");
		col3.setCellValueFactory(new PropertyValueFactory<>("preco"));
		
		TableColumn<Produto, String> col4 = new TableColumn<>("Tipo");
		col4.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		
		table.getColumns().addAll(col1, col2, col3, col4);
		
		table.setItems(control.getProduto());
		
		btnSalvar.setOnAction((e) -> {
			control.salvar();
		});
		
		btnListar.setOnAction((e) -> {
			control.listar();
		});
		
		btnExcluir.setOnAction((e) -> {
			control.excluir();
		});
		
		btnAtualizar.setOnAction((e) -> {
			control.atualizar();
		});
		
		stage.setScene(scn);
		stage.setTitle("Cadastrar Produto");
		stage.show();
	}
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
