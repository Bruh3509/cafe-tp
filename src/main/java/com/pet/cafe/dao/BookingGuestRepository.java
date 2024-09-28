package com.pet.cafe.dao;

import com.pet.cafe.entity.BookingGuest;
import com.pet.cafe.entity.BookingGuestPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingGuestRepository extends JpaRepository<BookingGuest, BookingGuestPK> {
}
