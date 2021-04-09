package com.business.application.repository;

import com.business.application.entity.Alloy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlloyRepository extends JpaRepository<Alloy, String> {

}
