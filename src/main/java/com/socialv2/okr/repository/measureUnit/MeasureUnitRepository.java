package com.socialv2.okr.repository.measureUnit;

import com.socialv2.okr.entities.measureUnits.MeasureUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MeasureUnitRepository extends JpaRepository<MeasureUnit, UUID>, JpaSpecificationExecutor<MeasureUnit> {
    Optional<MeasureUnit> findById(UUID id);
    
    List<MeasureUnit> findAll();
}
