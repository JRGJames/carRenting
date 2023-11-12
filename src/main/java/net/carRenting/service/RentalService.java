package net.carRenting.service;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public class RentalService extends JpaRepository<RentalEntity, Long> {
    Page<RentalEntity> findByCostumerId(Long id, Pageable pageable);

    @Query(value = "SELECT t.*,count(r.id) FROM rental t, car r WHERE t.id = r.id_rental GROUP BY t.id ORDER BY COUNT(r.id) desc", nativeQuery = true)
    Page<RentalEntity> findRentalsByCarsNumberDesc(Pageable pageable);

    @Query(value = "SELECT t.*,count(r.id) FROM rental t, car r WHERE t.id = r.id_rental and t.id_costumer=$1 GROUP BY t.id ORDER BY COUNT(r.id) desc", nativeQuery = true)
    Page<RentalEntity> findRentalsByCarNumberDescFilterByCostumerId(Long costumerId, Pageable pageable);

    @Modifying
    @Query(value = "ALTER TABLE thread AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}