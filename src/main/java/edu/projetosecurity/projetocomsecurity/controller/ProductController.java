package edu.projetosecurity.projetocomsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.projetosecurity.projetocomsecurity.model.Products;
import edu.projetosecurity.projetocomsecurity.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository listProducts;

    @GetMapping("")
    /* @PreAuthorize("hasAnyRole('USERS', 'ADMS')") */
    public ResponseEntity getAllList(){
        return ResponseEntity.ok("permisão totaç");
    }

    @GetMapping("/teste")
    /* @PreAuthorize("hasAnyRole('USERS', 'ADMS')") */
    public ResponseEntity getAllProducts(){
        return ResponseEntity.ok("liberado para todos com permissão");
    }


    @GetMapping("/list")
    /* @PreAuthorize("hasAnyRole('USERS', 'ADMS')") */
    public ResponseEntity getAll(){
        return ResponseEntity.ok("so ADM com permissão");
    }

    @PostMapping("/create")
    /* @PreAuthorize("hasAnyRole('USERS', 'ADMS')") */
    public ResponseEntity createdProduct(@RequestBody Products product){
        listProducts.save(product);
        return ResponseEntity.ok().build();
    }
}
