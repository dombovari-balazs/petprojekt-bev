package com.dombi.krakn2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Wine {
    @Id
    @GeneratedValue
    private Long id;

    private String eanKod;
    private String VTSZSZJ;  // vámtarifaszám +/ szolgáltatási jegyzék szám
    private String afaKulcs;
    private String termekcsoport;
    private String termekcsoport2;
    private String mEgyseg;
    private int beszerzesiAr;
    private String beszerzesiDevizanem;
    private int tovabbiCikkszam;
    private int borhaloBruttoAr;
    private int akciosBorhaloAr;
    private int woltBruttoAr;
    private String  partner;
    private int mennyiseg;
    private int nettoEgysegar;
    //private String afa_kulcs;
    private int bruttoEgysegar;
    private String megnevezes;

    public void fillUpFromNav(WineFromNAV wineFromNAV){
        this.megnevezes = wineFromNAV.getMegnevezes();
        this.nettoEgysegar = wineFromNAV.getNettoEgysegar();
        this.bruttoEgysegar = wineFromNAV.getBruttoEgysegar();
        this.mennyiseg = wineFromNAV.getMennyiseg(); //  todo: ezt meg kell kérdezni, hogy hogyan adja hozzá
    }


}
