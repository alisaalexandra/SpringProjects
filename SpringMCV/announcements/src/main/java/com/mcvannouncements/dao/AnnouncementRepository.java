package com.mcvannouncements.dao;

import com.mcvannouncements.model.AnnouncementModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends CrudRepository<AnnouncementModel,Integer> {

}
