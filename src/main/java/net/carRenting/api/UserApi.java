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

import net.carRenting.entity.UserEntity;
import net.carRenting.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    UserService userService;

    @GetMapping("/get/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @GetMapping("/get/byUsername/{username}")
    public ResponseEntity<UserEntity> get(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.getByUsername(username));
    }

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody UserEntity userEntity) {
        return ResponseEntity.ok(userService.create(userEntity));
    }

    @PutMapping("/update")
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity userEntity) {
        return ResponseEntity.ok(userService.update(userEntity));
    }

    @DeleteMapping("/get/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }

    @GetMapping("/getpage")
    public ResponseEntity<Page<UserEntity>> getPage(Pageable pageable) {
        return ResponseEntity.ok(userService.getPage(pageable));
    }

    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(userService.populate(amount));
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(userService.empty());
    }

    @GetMapping("/getpage/byCarsNumberDesc")
    public ResponseEntity<Page<UserEntity>> getPageByCarsNumberDesc(Pageable pageable) {
        return ResponseEntity.ok(userService.getPageByCarsNumberDesc(pageable));
    }
}
