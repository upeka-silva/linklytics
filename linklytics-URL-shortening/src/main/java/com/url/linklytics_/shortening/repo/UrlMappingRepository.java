package com.url.linklytics_.shortening.repo;
import com.url.linklytics_.shortening.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping,Long> {

    @Query("SELECT u from UrlMapping u where u.shorterUrl=:shortUrl")
    UrlMapping findByShorterUrl(@Param("shortUrl") String shortUrl);

    @Query("SELECT u from UrlMapping u where u.user.id=:id")
    List<UrlMapping> findAllByUserId(@Param("id") Long id);

}
