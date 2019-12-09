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
public class WineFromNAV {
    // Kell mas valuta, mint a HUF?
    //

    @Id
    @GeneratedValue
    private Long id;
    private String megnevezes;          //
    private int nettoEgysegar;          // 1
    private int bruttoEgysegar;         // 1
    private int mennyiseg;              // 1
    private int fizetendo;              // beszerzési ár?
    private int eredetiBruttoEgysegar;
    private int osszBruttoJutalek;





}
