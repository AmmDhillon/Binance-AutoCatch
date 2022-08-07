package com.ammdhillon.autocatch.repo;

import com.ammdhillon.autocatch.model.entity_model.data.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface DataRepository extends JpaRepository<Data, Long> {


}