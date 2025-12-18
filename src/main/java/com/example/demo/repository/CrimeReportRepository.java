package com.example.demo.repository;

import com.example.demo.model.CrimeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrimeReportRepository extends JpaRepository<CrimeReport, Long> {

    @Query(
        "SELECT c FROM CrimeReport c " +
        "WHERE c.latitude BETWEEN :latMin AND :latMax " +
        "AND c.longitude BETWEEN :lngMin AND :lngMax"
    )
    List<CrimeReport> findByLatLongRange(
            @Param("latMin") double latMin,
            @Param("latMax") double latMax,
            @Param("lngMin") double lngMin,
            @Param("lngMax") double lngMax
    );
}
