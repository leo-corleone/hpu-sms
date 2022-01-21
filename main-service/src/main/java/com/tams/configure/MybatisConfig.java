package com.tams.configure;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.MySqlDialect;
import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author swiChen
 * @date 2022/1/6
 **/

@Configuration
@MapperScan("com.tams.mapper")
public class MybatisConfig {

    @Bean // 配置分页
    public MybatisPlusInterceptor mybatisPlusInterceptor(){

        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
        innerInterceptor.setDbType(DbType.MYSQL);
        innerInterceptor.setDialect(new MySqlDialect());

        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(innerInterceptor);

        return  mybatisPlusInterceptor;
    }


    @Bean
    public PageInterceptor pageInterceptor(){
        return new PageInterceptor();
    }

}
