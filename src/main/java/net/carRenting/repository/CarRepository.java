package net.carRenting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.carRenting.entity.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
    Page<CarEntity> findByUserId(Long id, Pageable pageable);

    @Query(value = "SELECT c.*,count(r.id) FROM car c, rental r WHERE c.id = r.id_car GROUP BY c.id ORDER BY COUNT(r.id) desc", nativeQuery = true)
    Page<CarEntity> findRentalsByCarsNumberDesc(Pageable pageable);

    @Query(value = "SELECT c.*,count(r.id) FROM car c, rental r WHERE c.id = r.id_car and c.id_user=$1 GROUP BY c.id ORDER BY COUNT(c.id) desc", nativeQuery = true)
    Page<CarEntity> findRentalsByCarsNumberDescFilterByUserId(Long userId, Pageable pageable);

    @Modifying
    @Query(value = "ALTER TABLE car AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
