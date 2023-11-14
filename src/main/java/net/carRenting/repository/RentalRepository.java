package net.carRenting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import net.carRenting.entity.RentalEntity;

public interface RentalRepository extends JpaRepository<RentalEntity, Long> {
    Page<RentalEntity> findByCustomerId(Long id, Pageable pageable);

    @Query(value = "SELECT rent.*,count(c.id) FROM rental rent, car c WHERE rent.id = c.id_rental GROUP BY rent.id ORDER BY COUNT(c.id) desc", nativeQuery = true)
    Page<RentalEntity> findRentalsByCarsNumberDesc(Pageable pageable);

    @Query(value = "SELECT rent.*,count(c.id) FROM rental rent, car c WHERE rent.id = c.id_rental and rent.id_costumer=$1 GROUP BY rent.id ORDER BY COUNT(c.id) desc", nativeQuery = true)
    Page<RentalEntity> findRentalsByCarsNumberDescFilterByCustomerId(Long customerId, Pageable pageable);

    @Modifying
    @Query(value = "ALTER TABLE rental AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}