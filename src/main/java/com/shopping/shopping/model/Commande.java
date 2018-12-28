package com.shopping.shopping.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@Entity
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;
    @NaturalId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int numeroCommande;
    private LocalDateTime datecreation;
    private LocalDateTime dateSuppression;
    @ManyToOne
    @JoinColumn(name="utilisateur_id")
    private Utilisateur utilisateur;
    @ManyToMany
    @JoinTable(name = "commande_produit", joinColumns = @JoinColumn(name = "id_commande"), inverseJoinColumns = @JoinColumn(name = "id_produit"))
    private List<Produit>produits;
    @OneToMany
    private List<Historique> historiques;
    private String statusCommande;
    private LocalDateTime dateLivraison;

    public Commande() {
    }

    public Long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Long idCommande) {
        this.idCommande = idCommande;
    }

    public int getNumeroCommande() {
        return numeroCommande;
    }

    public void setNumeroCommande(int numeroCommande) {
        this.numeroCommande = numeroCommande;
    }

    public LocalDateTime getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(LocalDateTime datecreation) {
        this.datecreation = datecreation;
    }

    public LocalDateTime getDateSuppression() {
        return dateSuppression;
    }

    public void setDateSuppression(LocalDateTime dateSuppression) {
        this.dateSuppression = dateSuppression;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public List<Historique> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<Historique> historiques) {
        this.historiques = historiques;
    }

    public String getStatusCommande() {
        return statusCommande;
    }

    public void setStatusCommande(String statusCommande) {
        this.statusCommande = statusCommande;
    }

    public LocalDateTime getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(LocalDateTime dateLivraison) {
        this.dateLivraison = dateLivraison;
    }
}
