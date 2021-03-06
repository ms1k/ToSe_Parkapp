package de.g2p.ToSe_Parkapp.Repositories;

import de.g2p.ToSe_Parkapp.Entities.Konsument;
import de.g2p.ToSe_Parkapp.Entities.Parkplatz;
import de.g2p.ToSe_Parkapp.Entities.Reservierung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReservierungenRepository extends JpaRepository<Reservierung, Integer> {

    Reservierung findByKid(Konsument kid);
    Reservierung findByPid(Parkplatz pid);
    List<Reservierung> findByBeendet(boolean beendet);

    @Query(value = "select * from reservierung where pid = :pid ", nativeQuery = true)
    List<Reservierung> findByPidList(@Param("pid") Integer pid);

    @Transactional
    @Modifying
    @Query(value = "update reservierung set beendet = :beendet  where rid = :rid", nativeQuery = true)
    void updateBeendet(@Param("beendet") boolean beendet, @Param("rid") Integer rid);

    @Transactional
    @Modifying
    @Query(value = "update reservierung set remark = :remark  where rid = :rid", nativeQuery = true)
    void updateResZuParken(@Param("remark") boolean remark, @Param("rid") Integer rid);
}
