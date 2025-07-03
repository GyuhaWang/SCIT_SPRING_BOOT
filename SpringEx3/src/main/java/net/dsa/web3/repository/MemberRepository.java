package net.dsa.web3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.dsa.web3.entity.MemberEntity;

@Repository
// <Entity, id type>
public interface MemberRepository extends 
JpaRepository<MemberEntity,String>{
	
}
