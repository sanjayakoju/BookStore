package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.security.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByname(String name);
}
