package org.learning.springlibrary.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza")
public class Pizza {
    // ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private int id;
    @NotEmpty(message = "Il nome non può essere vuoto")
    @Column(nullable = false)
    private String nome;
    @Column
    private String descrizione;
    @Column
    private String foto;
    @NotNull(message = "Il prezzo non può essere vuoto")
    @Column(nullable = false)
    private BigDecimal prezzo;
    // GETTERS AND SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public List<Offerte> getOfferteSpeciali() {
        return offerteSpeciali;
    }

    public void setOfferteSpeciali(List<Offerte> offerteSpeciali) {
        this.offerteSpeciali = offerteSpeciali;
    }

    @OneToMany(mappedBy = "pizza", orphanRemoval = true)
    // no nuova relazione, l'avevo già definita sull'attributo book di Borrowing
    private List<Offerte> offerteSpeciali;
}
