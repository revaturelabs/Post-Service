package com.reverse.postservice.repositories;

import com.reverse.postservice.models.Like;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDao extends JpaRepository<Like, Integer> {
//    @Query(value = "select count(post_id)\n" +
//            "from user_likes\n" +
//            "where post_id = :id", nativeQuery = true)
//    Long countByPostId(@Param("id") int id);

    //Does the same as above with generated query
    Long countByLikeId_PostId(int id);
}
