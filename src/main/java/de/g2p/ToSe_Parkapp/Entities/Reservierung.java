package de.g2p.ToSe_Parkapp.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservierung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;

    private Integer kid;

    private Integer pid;

    private Date start;

    private Date ende;

    //wurde das Parken durch eine Reservierung ausgelöst?
    @Column(name = "REMARK")
    private boolean resZuParken;

    private boolean beendet;

    public Reservierung(Integer kid, Integer pid, Date start, Date ende, boolean resZuParken, boolean beendet) {
        this.kid = kid;
        this.pid = pid;
        this.start = start;
        this.ende = ende;
        this.resZuParken = resZuParken;
        this.beendet = beendet;
    }
}
