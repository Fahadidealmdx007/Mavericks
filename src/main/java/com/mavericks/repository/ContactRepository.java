package com.mavericks.repository;

import com.mavericks.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContactRepository extends JpaRepository<ContactMessage, String> {
    List<ContactMessage> findAllByOrderBySentAtDesc();
}
