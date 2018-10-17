package hyzj.demo.Service;

import hyzj.demo.Dao.FactoryRebateDao;
import hyzj.demo.Model.FactoryRebate;
import hyzj.demo.Utils.CodeUtils;
import hyzj.demo.Utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FactoryRebateService {

    @Resource
    FactoryRebateDao factoryRebateDao;

    /**
     * 获取返点记录
     * @return
     */
    public List<FactoryRebate> ladList() {
        return factoryRebateDao.loadList();
    }


    /**
     * 更新记录
     * @param F_id
     * @param rebate_amount
     * @return
     */
    public boolean update(String F_id,String rebate_amount) {
        return factoryRebateDao.update(F_id,rebate_amount);
    }


    /**
     * 添加记录
     * @param rebate_amount
     * @return
     */
    public boolean add(String rebate_amount) {
        try {
            CodeUtils codeUtils = new CodeUtils();
            String F_id = "F" + codeUtils.createRandom(false,6);
            String rebate_time = TimeUtils.getTime();

            return factoryRebateDao.add(F_id,rebate_time,rebate_amount);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 删除记录
     * @param F_id
     * @return
     */
    public boolean delete(String F_id) {
        return factoryRebateDao.delete(F_id);
    }
}
