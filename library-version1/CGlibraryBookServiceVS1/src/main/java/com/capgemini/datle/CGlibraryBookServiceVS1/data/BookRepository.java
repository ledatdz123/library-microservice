package com.capgemini.datle.CGlibraryBookServiceVS1.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
