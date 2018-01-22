package br.com.springmodel.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Joel Rodrigues Moreira on 22/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
public class Endereco implements Serializable {
    private String id;
    private String descricao;

    public Endereco() {
    }

    public Endereco(final String id, final String descricao) {
        this();
        this.id = id;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public Endereco setId(final String id) {
        this.id = id;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Endereco setDescricao(final String descricao) {
        this.descricao = descricao;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
