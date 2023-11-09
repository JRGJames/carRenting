package net.carRenting.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
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

import net.carRenting.entity.CarEntity;
import net.carRenting.service.CarService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/car")
public class CarApi {
    @Autowired
    CarService oCarService;

    @GetMapping("/{id}")
    public ResponseEntity<CarEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCarService.get(id));
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody CarEntity oCarEntity) {
        return ResponseEntity.ok(oCarService.create(oCarEntity));
    }

    @PutMapping("")
    public ResponseEntity<CarEntity> update(@RequestBody CarEntity oCarEntity) {
        return ResponseEntity.ok(oCarService.update(oCarEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCarService.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<CarEntity>> getPage(Pageable oPageable,
            @RequestParam(value = "user", defaultValue = "0", required = false) Long userId,
            @RequestParam(value = "thread", defaultValue = "0", required = false) Long threadId) {
        return ResponseEntity.ok(oCarService.getPage(oPageable, userId, threadId));
    }

    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oCarService.populate(amount));
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oCarService.empty());
    }
}

    