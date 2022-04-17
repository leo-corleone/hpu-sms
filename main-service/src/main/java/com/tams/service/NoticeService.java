package com.tams.service;


import com.tams.domain.Notice;

import java.util.List;

/**
 * @data 2021-6-22
 */
public interface NoticeService {

  boolean addNotice(Notice notice);

  List<Notice> getNotice(String type);


  boolean switchStatus(Long nId);

  boolean deleteNotice(Long nId);

}
