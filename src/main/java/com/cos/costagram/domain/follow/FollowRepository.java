package com.cos.costagram.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FollowRepository extends JpaRepository<Follow, Integer> {

	
	// insert,delete,update (@Modifying)
	// 서비스에  (@Transactional)
	
	@Modifying
	@Query(value ="INSERT INTO follow(fromUserId,toUserId,createDate) values(:fromUserId,:toUserId,now())",nativeQuery = true)
	int mFollow(int fromUserId,int toUserId);
	
	@Modifying
	@Query(value ="DELETE FROM follow WHERE fromUserId =:fromUserId AND toUserId =:toUserId",nativeQuery = true)
	int mUnFollow(int fromUserId,int toUserId);
}
