package net.carRenting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import net.carRenting.entity.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
    Page<CarEntity> findByCostumerId(Long id, Pageable pageable);

    Page<CarEntity> findByRentalId(Long id, Pageable pageable);

    @Modifying
    @Query(value = "ALTER TABLE car AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();


}
