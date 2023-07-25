package org.platformlib.platformbackend.repository;

 import org.platformlib.platformbackend.entity.Member;
 import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByEmail(String email);
    Member findByEmailAndPassword(String email, String password);
}
