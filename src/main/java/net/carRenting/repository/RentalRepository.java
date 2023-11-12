package net.carRenting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import net.carRenting.entity.RentalEntity;

public interface RentalRepository extends JpaRepository<RentalEntity, Long> {
    Page<RentalEntity> findByCustomerId(Long id, Pageable pageable);

    @Query(value = "SELECT t.*,count(r.id) FROM rental t, car r WHERE t.id = r.id_rental GROUP BY t.id ORDER BY COUNT(r.id) desc", nativeQuery = true)
    Page<RentalEntity> findThreadsByRepliesNumberDesc(Pageable pageable);

    @Query(value = "SELECT t.*,count(r.id) FROM rental t, car r WHERE t.id = r.id_rental and t.id_costumer=$1 GROUP BY t.id ORDER BY COUNT(r.id) desc", nativeQuery = true)
    Page<RentalEntity> findRentalsByCarsNumberDescFilterByCustomerId(Long customerId, Pageable pageable);

    @Modifying
    @Query(value = "ALTER TABLE rental AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}