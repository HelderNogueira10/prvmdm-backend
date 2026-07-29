package com.privguard.mdm.server.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long>{
 
    <Optional> EnrollmentEntity findByDeviceId(Long _id);
}
