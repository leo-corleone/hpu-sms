package com.tams.test;

import com.tams.domain.Role;
import com.tams.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author swiChen
 * @date 2022/1/13
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnumTest {

    private RoleService roleService;

    @Test
    public  void test1(){

        List<Role> list = roleService.list();
        list.forEach(System.out::println);

    }

}
