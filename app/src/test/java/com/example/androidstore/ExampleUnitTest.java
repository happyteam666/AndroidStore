package com.example.androidstore;

import com.example.androidstore.Util.GsonUtils;
import com.example.androidstore.bean.Category;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void foo() {
        String string = "[{\"id\":5,\"name\":\"家电\",\"pid\":0,\"image\":\"\",\"categories\":[{\"id\":31,\"name\":\"冰箱\",\"pid\":5,\"image\":\"https://g-search3.alicdn.com/img/bao/uploaded/i4/i2/3937106725/O1CN01JpyOtU1zY64HK8x1h_!!0-item_pic.jpg_580x580Q90.jpg_.webp\",\"categories\":null},{\"id\":32,\"name\":\"洗衣机\",\"pid\":5,\"image\":\"https://g-search1.alicdn.com/img/bao/uploaded/i4/i4/470168984/O1CN01PMsrfN2GEimRSwbfj_!!0-item_pic.jpg_580x580Q90.jpg_.webp\",\"categories\":null},{\"id\":33,\"name\":\"电视机\",\"pid\":5,\"image\":\"https://g-search2.alicdn.com/img/bao/uploaded/i4/i3/1714128138/O1CN01iTi4qw29zFiZlkWcz_!!0-item_pic.jpg_580x580Q90.jpg_.webp\",\"categories\":null}]},{\"id\":4,\"name\":\"食品\",\"pid\":0,\"image\":\"\",\"categories\":[{\"id\":11,\"name\":\"零食\",\"pid\":4,\"image\":\"https://g-search2.alicdn.com/img/bao/uploaded/i4/i1/628189716/O1CN01R0qpFn2Ldyi3iZ3Ck_!!0-item_pic.jpg_580x580Q90.jpg_.webp\",\"categories\":null},{\"id\":28,\"name\":\"糖果布丁\",\"pid\":4,\"image\":\"https://g-search1.alicdn.com/img/bao/uploaded/i4/i3/725677994/O1CN013ZvLHi28vIfYMQEqG_!!0-item_pic.jpg_580x580Q90.jpg_.webp\",\"categories\":null},{\"id\":29,\"name\":\"酒\",\"pid\":4,\"image\":\"https://g-search1.alicdn.com/img/bao/uploaded/i4/i4/2549841410/O1CN01TSghNc1MHozKQM20I_!!2549841410-0-sm.jpg_580x580Q90.jpg_.webp\",\"categories\":null},{\"id\":30,\"name\":\"乳制品\",\"pid\":4,\"image\":\"https://g-search1.alicdn.com/img/bao/uploaded/i4/i3/725677994/O1CN01tm9RHU28vIgVHY7bb_!!0-item_pic.jpg_580x580Q90.jpg_.webp\",\"categories\":null}]},{\"id\":3,\"name\":\"服装\",\"pid\":0,\"image\":\"\",\"categories\":[{\"id\":24,\"name\":\"牛仔裤\",\"pid\":3,\"image\":\"https://g-search2.alicdn.com/img/bao/uploaded/i4/i4/1679808547/O1CN01GhV2722D0ZgWSbOov_!!0-item_pic.jpg_580x580Q90.jpg_.webp\",\"categories\":null},{\"id\":23,\"name\":\"休闲裤\",\"pid\":3,\"image\":\"https://g-search2.alicdn.com/img/bao/uploaded/i4/i2/2243753077/O1CN01BbiSPb1YbJEE7MB34_!!0-item_pic.jpg_580x580Q90.jpg_.webp\",\"categories\":null},{\"id\":22,\"name\":\"裙子\",\"pid\":3,\"image\":\"https://g-search3.alicdn.com/img/bao/uploaded/i4/i1/112394247/O1CN01GK5CxI1hFAcpzCAH8_!!0-item_pic.jpg_580x580Q90.jpg_.webp\",\"categories\":null},{\"id\":19,\"name\":\"卫衣\",\"pid\":3,\"image\":\"https://g-search3.alicdn.com/img/bao/uploaded/i4/i2/196993935/O1CN01FjKhBT1ewH32sJJdk_!!0-item_pic.jpg_580x580Q90.jpg_.webp\",\"categories\":null},{\"id\":18,\"name\":\"衬衫\",\"pid\":3,\"image\":\"https://g-search3.alicdn.com/img/bao/uploaded/i4/i1/196993935/O1CN01Ck29Gv1ewH2Lbw8Um_!!0-item_pic.jpg_580x580Q90.jpg_.webp\",\"categories\":null},{\"id\":27,\"name\":\"糕点\",\"pid\":3,\"image\":\"https://g-search3.alicdn.com/img/bao/uploaded/i4/i2/880734502/O1CN011juPaD1j7xcf43DZC_!!0-item_pic.jpg_580x580Q90.jpg_.webp\",\"categories\":null}]},{\"id\":1,\"name\":\"手机\",\"pid\":0,\"image\":\"\",\"categories\":[{\"id\":16,\"name\":\"手机耳机\",\"pid\":1,\"image\":\"https://g-search3.alicdn.com/img/bao/uploaded/i4/i1/4061549550/O1CN01fxLagq2KPwx634lrz_!!0-item_pic.jpg_580x580Q90.jpg_.webp\",\"categories\":null},{\"id\":15,\"name\":\"充电宝\",\"pid\":1,\"image\":\"https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/1714128138/O1CN01q7uXS429zFibto3Fa_!!0-item_pic.jpg_580x580Q90.jpg_.webp\",\"categories\":null},{\"id\":14,\"name\":\"手机壳\",\"pid\":1,\"image\":\"https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/2455420587/O1CN01LdrfOX1GCt0Nme0dB_!!0-item_pic.jpg_580x580Q90.jpg_.webp\",\"categories\":null}]}]\n";
        List<Category> categories = GsonUtils.GsonToList(string,Category[].class);
        for (Category category : categories) {

            System.out.println(category.toString());
        }
    }

}
