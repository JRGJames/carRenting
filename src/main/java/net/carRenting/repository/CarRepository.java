package net.carRenting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.carRenting.entity.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
    Page<CarEntity> findByUserId(Long id, Pageable pageable);

    @Query("SELECT c FROM CarEntity c JOIN c.rentals r WHERE r.id = :id_rental")
    Page<CarEntity> findByRentalId(@Param("id_rental") Long id, Pageable pageable);

    @Modifying
    @Query(value = "ALTER TABLE car AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();


}
