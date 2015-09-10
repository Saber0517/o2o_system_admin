package serviceimpl;

import com.oocl.jyhon.entiy.FoodEntity;
import com.oocl.jyhon.entiy.UserEntity;
import com.jyhon.service.FoodEntityService;
import com.jyhon.service.UserEntityService;
import com.jyhon.serviceimpl.FoodEntityServiceImpl;
import com.jyhon.serviceimpl.UserEntityServiceImpl;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Map;

/**
 * Created by ZHANGJA4 on 8/8/2015.
 */
public class updateChangeEntityStatusTest {
    @Test
    public void testupdataUserStatus() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserID(48);
        userEntity.setStatusId(1);
        UserEntityService userEntityService = new UserEntityServiceImpl();
        TestCase.assertEquals(userEntityService.changeUserEntityStatus(userEntity), 1);
    }

    @Test
    public void testUpdateFoodStatus() {
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setFoodID(47);
        foodEntity.setStatusID(1);
        FoodEntityService foodEntityService = new FoodEntityServiceImpl();
        Map<String, String> reusltMap = foodEntityService.updateFoodEntityStatus(foodEntity.getFoodID(), foodEntity.getFoodID());
        TestCase.assertEquals(reusltMap.get("successMessage"), "update success");
    }
}
