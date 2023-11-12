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
import org.springframework.web.bind.annotation.RestController;

import net.carRenting.entity.CustomerEntity;
import net.carRenting.service.CustomerService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/customer")
public class CustomerApi {

    @Autowired
    CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.get(id));
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody CustomerEntity customerEntity) {
        return ResponseEntity.ok(customerService.create(customerEntity));
    }

    @PutMapping("")
    public ResponseEntity<CustomerEntity> update(@RequestBody CustomerEntity customerEntity) {
        return ResponseEntity.ok(customerService.update(customerEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<CustomerEntity>> getPage(Pageable pageable) {
        return ResponseEntity.ok(customerService.getPage(pageable));
    }

    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(customerService.populate(amount));
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(customerService.empty());
    }
}
