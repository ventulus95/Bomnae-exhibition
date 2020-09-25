package com.projvent.board.web.domain.guestbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestbookRepository extends JpaRepository<Guestbook, Integer> {

    List<Guestbook> findAllByOrderByModifiedDateDesc();
}
