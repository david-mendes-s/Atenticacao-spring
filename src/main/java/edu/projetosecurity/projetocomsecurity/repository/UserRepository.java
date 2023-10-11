package edu.projetosecurity.projetocomsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.projetosecurity.projetocomsecurity.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    public User findByUserName(String userName);
}
