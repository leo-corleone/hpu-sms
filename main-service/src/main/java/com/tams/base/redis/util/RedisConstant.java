package com.tams.base.redis.util;

import com.tams.enums.RoleEnum;

/**
 * @author swiChen
 * @date 2022/1/21
 **/

public final class RedisConstant {

    public static final String CACHE_STUDENT = "user_student_cache:";

    public static final String CACHE_TEACHER = "user_teacher_cache:";

    public static final String CACHE_ADMIN = "user_admin_cache:";

    public static final String CACHE_ROOT = "user_root_cache:";

    public static final String USER_INFO_CACHE = "user_info_cache";

    public static final String USER_TOKEN_CACHE = "user_token_cache";

    public static final String USER_EXPIRE_TIME = "user_expire_time";

    public static final String USER_CACHE_PATTERN = "user_*_cache:*";

    public static final Long  USER_DEFAULT_RESTORE_TIME = 20L;

    public static String getRedisK(RoleEnum role){
        String k = "";
        switch (role){
            case STUDENT:
                k = RedisConstant.CACHE_STUDENT;
                break;
            case TEACHER:
                k = RedisConstant.CACHE_TEACHER;
                break;
            case ROOT:
                k = RedisConstant.CACHE_ROOT;
                break;
            case ADMIN:
                k = RedisConstant.CACHE_ADMIN;
                break;
        }
      return k;
    }

}
