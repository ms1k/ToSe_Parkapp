package de.g2p.ToSe_Parkapp.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Standort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ortid;

    private String strasse;

    private Integer plz;

    private Integer hausnummer;

    private String latitude;

    private String longitude;

    public Standort(String strasse, Integer plz, Integer hausnummer, String latitude, String longitude) {
        this.strasse = strasse;
        this.plz = plz;
        this.hausnummer = hausnummer;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
