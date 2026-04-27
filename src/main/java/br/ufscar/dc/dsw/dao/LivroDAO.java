package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Editora;
import br.ufscar.dc.dsw.domain.Livro;

public class LivroDAO extends GenericDAO {

    public void insert(Livro livro) {
        String sql = "INSERT INTO Livro (titulo, autor, ano, preco, editora_id) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getAutor());
            statement.setInt(3, livro.getAno());
            statement.setFloat(4, livro.getPreco());
            statement.setLong(5, livro.getEditora().getId());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Livro> getAll() {
        List<Livro> listaLivros = new ArrayList<>();
        String sql = "SELECT l.*, e.id as editora_id, e.cnpj, e.nome FROM Livro l, Editora e WHERE l.editora_id = e.id ORDER BY l.id";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                int ano = resultSet.getInt("ano");
                float preco = resultSet.getFloat("preco");
                Editora editora = new Editora(resultSet.getLong("editora_id"), resultSet.getString("cnpj"), resultSet.getString("nome"));
                Livro livro = new Livro(id, titulo, autor, ano, preco, editora);
                listaLivros.add(livro);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaLivros;
    }

    public void delete(Livro livro) {
        String sql = "DELETE FROM Livro WHERE id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, livro.getId());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Livro livro) {
        String sql = "UPDATE Livro SET titulo = ?, autor = ?, ano = ?, preco = ?, editora_id = ? WHERE id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getAutor());
            statement.setInt(3, livro.getAno());
            statement.setFloat(4, livro.getPreco());
            statement.setLong(5, livro.getEditora().getId());
            statement.setLong(6, livro.getId());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Livro get(Long id) {
        Livro livro = null;
        String sql = "SELECT l.*, e.id as editora_id, e.cnpj, e.nome FROM Livro l, Editora e WHERE l.id = ? AND l.editora_id = e.id";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                int ano = resultSet.getInt("ano");
                float preco = resultSet.getFloat("preco");
                Editora editora = new Editora(resultSet.getLong("editora_id"), resultSet.getString("cnpj"), resultSet.getString("nome"));
                livro = new Livro(id, titulo, autor, ano, preco, editora);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return livro;
    }
}