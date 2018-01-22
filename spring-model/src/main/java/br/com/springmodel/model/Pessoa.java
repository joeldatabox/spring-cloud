package br.com.springmodel.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Joel Rodrigues Moreira on 10/01/18.
 * @project demo
 */
@Document(collection = "pessoas")
public class Pessoa implements Serializable {

    @Id
    private String id;
    @Indexed
    @NotBlank(message = "informe uma razão valida")
    private String razao;
    @Indexed
    private String fantasia;
    @Indexed
    private String cpfCnpj;
    private Date dtCadastro;

    public Pessoa() {
    }

    public String getId() {
        return id;
    }

    public Pessoa setId(final String id) {
        this.id = id;
        return this;
    }

    public String getRazao() {
        return razao;
    }

    public Pessoa setRazao(final String razao) {
        this.razao = razao;
        return this;
    }

    public String getFantasia() {
        return fantasia;
    }

    public Pessoa setFantasia(final String fantasia) {
        this.fantasia = fantasia;
        return this;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public Pessoa setCpfCnpj(final String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
        return this;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public Pessoa setDtCadastro(final Date dtCadastro) {
        this.dtCadastro = dtCadastro;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
