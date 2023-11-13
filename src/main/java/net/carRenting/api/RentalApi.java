package net.carRenting.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.carRenting.entity.RentalEntity;
import net.carRenting.service.RentalService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/rental")
public class RentalApi {
    @Autowired
    RentalService rentalService;

    @GetMapping("/{id}")
    public ResponseEntity<RentalEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(rentalService.get(id));
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody RentalEntity rentalEntity) {
        return ResponseEntity.ok(rentalService.create(rentalEntity));
    }

    @PutMapping("")
    public ResponseEntity<RentalEntity> update(@RequestBody RentalEntity rentalEntity) {
        return ResponseEntity.ok(rentalService.update(rentalEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(rentalService.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<RentalEntity>> getPage(Pageable pageable,
    @RequestParam(value = "customer", defaultValue = "0", required = false) Long customerId) {
        return ResponseEntity.ok(rentalService.getPage(pageable, customerId));
    }

    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(rentalService.populate(amount));
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(rentalService.empty());
    }

    @GetMapping("/byCarsNumberDesc")
    public ResponseEntity<Page<RentalEntity>> getPageByCarsNumberDesc(Pageable pageable,
            @RequestParam(value = "customer", defaultValue = "0", required = false) Long customerId) {
        return ResponseEntity.ok(rentalService.getPageByCarsNumberDesc(pageable, customerId));
    }
}
