package vn.tdat.jobhunter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.tdat.jobhunter.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    
} 
