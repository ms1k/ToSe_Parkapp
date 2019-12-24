package de.g2p.ToSe_Parkapp.Entities;

import de.g2p.ToSe_Parkapp.Fahrzeugtyp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Parkplatz")
public class Parkplatz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(name = "FTYP")
    private String fahrzeugtyp;

    @Column(name = "PSTAT")
    private String status;

    private boolean privat;

    @Column(name = "ZEITBEGR")
    private Integer zeitbegrenzung;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ortid", referencedColumnName = "ortId")
    private Standort ortid;

    private Integer bewertung;

    @Column(name = "BEWANZ")
    private Integer bewertungsanzahl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aid")
    private Anbieter anbieterId;

    private Integer parkgebuehr;

    private Integer strafgebuehr;

    private String beschreibung;

    public Parkplatz(String fahrzeugtyp, String status, boolean privat, Integer zeitbegrenzung, Standort ortid,
                     Integer bewertung, Integer bewertungsanzahl, Anbieter anbieterId, @NotNull Integer strafgebuehr,
                     String beschreibung, Integer parkgebuehr) {
        this.fahrzeugtyp = fahrzeugtyp;
        this.status = status;
        this.privat = privat;
        this.zeitbegrenzung = zeitbegrenzung;
        this.ortid = ortid;
        this.bewertung = bewertung;
        this.bewertungsanzahl = bewertungsanzahl;
        this.anbieterId = anbieterId;
        this.strafgebuehr = strafgebuehr;
        this.beschreibung = beschreibung;
        this.parkgebuehr = parkgebuehr;
    }
}
