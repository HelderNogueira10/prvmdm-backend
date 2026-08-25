package com.privguard.mdm.server.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long>{
 
    <Optional> EnrollmentEntity findByDeviceId(Long _id);
    List<EnrollmentEntity> findAllByStatus(EnrollmentStatus _status);
    Optional<EnrollmentEntity> findAllByStatusOrderByCreatedAtDesc(EnrollmentStatus _status);
}
