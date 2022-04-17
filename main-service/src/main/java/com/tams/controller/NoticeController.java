package com.tams.controller;

import com.tams.domain.Notice;
import com.tams.dto.AjaxResult;
import com.tams.service.NoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author swiChen
 * @date 2022/4/8
 **/

@RestController
@RequestMapping("/notice")
public class NoticeController {

  @Resource
  private NoticeService noticeService;

  @PostMapping("/add")
  public AjaxResult add(@RequestBody Notice notice){
     return AjaxResult.succ(noticeService.addNotice(notice));
  }

  @GetMapping("/{type}/list")
  public AjaxResult getNotice(@PathVariable String type){
    return AjaxResult.succ(noticeService.getNotice(type));
  }

  @PostMapping("/{id}/switch")
  public AjaxResult switchStatus(@PathVariable Long id){
    return AjaxResult.succ(noticeService.switchStatus(id));
  }

  @PostMapping("/{id}/remove")
  public AjaxResult removeNotice(@PathVariable Long id){
    return AjaxResult.succ(noticeService.deleteNotice(id));
  }}
