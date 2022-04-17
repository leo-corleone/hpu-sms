package com.tams.model;

import lombok.Data;

import java.util.List;

/**
 * @author swiChen
 * @date 2022/4/14
 **/

@Data
public class OptionsModel {

 private String label;

 private String value;

 private Object desc;

 private List<OptionsModel> children;

}
