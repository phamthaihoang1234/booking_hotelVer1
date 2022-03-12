package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.Role;

import java.util.Optional;

public interface TermOfUserService {
    Optional<Role> findByName(String name);

    Role save(Role role);

    Iterable<Role> findAll();
}
