import java.util.List;

public interface LojaDAO {

	void salvar(Produto p);
	List<Produto> listar(String id);
	void excluir(Produto p);
	void atualizar(Produto p);
}
