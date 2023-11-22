package net.carRenting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.carRenting.entity.RentalEntity;

public interface RentalRepository extends JpaRepository<RentalEntity, Long> {
    Page<RentalEntity> findByUserId(Long id, Pageable pageable);

    Page<RentalEntity> findByCarId(Long id, Pageable pageable);

    @Query(value = "SELECT r.*,count(c.id) FROM rental r, car c WHERE r.id = c.id GROUP BY r.id ORDER BY COUNT(c.id) desc", nativeQuery = true)
    Page<RentalEntity> findRentalsByCarsNumberDesc(Pageable pageable);

    @Query(value = "SELECT r.*,count(c.id) FROM rental r, car c WHERE r.id = r.id and c.id_user=$1 GROUP BY r.id ORDER BY COUNT(c.id) desc", nativeQuery = true)
    Page<RentalEntity> findRentalsByCarsNumberDescFilterByUserId(Long userId, Pageable pageable);

    @Modifying
    @Query(value = "ALTER TABLE rental AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}