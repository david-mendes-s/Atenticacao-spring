package edu.projetosecurity.projetocomsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.projetosecurity.projetocomsecurity.model.Products;

public interface ProductRepository extends JpaRepository<Products, String>{
    
}
