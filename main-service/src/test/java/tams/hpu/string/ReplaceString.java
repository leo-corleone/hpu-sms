package tams.hpu.string;

/**
 * @author swiChen
 * @date 2022/3/10
 **/
public class ReplaceString {

    public static void main(String[] args) {

        String cache = "user_*_cache:10001";
        int index = cache.lastIndexOf(":");
        System.out.println(cache.substring(index+1));


    }

}
