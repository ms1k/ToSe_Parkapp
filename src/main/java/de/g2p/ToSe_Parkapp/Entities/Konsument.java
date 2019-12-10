package de.g2p.ToSe_Parkapp.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Konsument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nid;

    private Integer kid;

    private String kennzeichen;

    @Column(name = "FTYP")
    private String fahrzeugtyp;

    @Column(name = "PBEL")
    private boolean belegt;

    private double saldo;

    @Column(name = "PRES")
    private boolean reserviert;

    public Konsument(Integer kid, String kennzeichen, String fahrzeugtyp, boolean belegt, double saldo, boolean reserviert) {
        this.kid = kid;
        this.kennzeichen = kennzeichen;
        this.fahrzeugtyp = fahrzeugtyp;
        this.belegt = belegt;
        this.saldo = saldo;
        this.reserviert = reserviert;
    }
}
