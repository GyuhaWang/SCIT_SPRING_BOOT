package net.dsa.web5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.dsa.web5.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,String> {

}
