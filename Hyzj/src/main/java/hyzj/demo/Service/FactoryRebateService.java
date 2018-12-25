package hyzj.demo.Service;

import hyzj.demo.Dao.*;
import hyzj.demo.Model.FactoryRebate;
import hyzj.demo.Model.Income;
import hyzj.demo.Utils.CodeUtils;
import hyzj.demo.Utils.IncomeAddUtils;
import hyzj.demo.Utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FactoryRebateService {

    @Resource
    FactoryRebateDao factoryRebateDao;

    @Resource
    IncomeDayDao incomeDayDao;

    @Resource
    IncomeMonthDao incomeMonthDao;

    @Resource
    IncomeYearDao incomeYearDao;

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
     * 为了配合项目收入按年，月，日显示数据
     * 需要分别向income，income_day,income_month,income_year四张表插入数据
     * @param rebate_amount
     * @return
     */
    public boolean add(String rebate_amount) {
        try {
            IncomeAddUtils incomeAddUtils = new IncomeAddUtils();

            //获取当前时间，每有一笔新的订单，就按时间插入四张表中
            //年月日表是相同时间，则修改其对应的金额
            CodeUtils codeUtils = new CodeUtils();
            String F_id = "F" + codeUtils.createRandom(false,6);
            String rebate_time = TimeUtils.getTime();

            //向商家返点表中插入数据
            String user_id = "#123";
            String project_id = "PmA1bP2PAVSUItWEZsLjeTTQAD1NFpktz";
            boolean factoryRebate = factoryRebateDao.add(F_id,user_id,project_id,rebate_time,rebate_amount);

            //插入和更新income_day表的数据
            boolean Income_day = incomeAddUtils.updateIncomeDay(rebate_time,rebate_amount,incomeDayDao);

            //插入或更新income_month表的数据
            boolean Income_month = incomeAddUtils.updateIncomeMonth(rebate_time,rebate_amount,incomeMonthDao);

            //插入火更新income_year表的数
            boolean Income_year = incomeAddUtils.updateIncomeYear(rebate_time,rebate_amount,incomeYearDao);

            if (factoryRebate == true && Income_day == true && Income_month == true && Income_year == true){
                return true;
            }else {
                return false;
            }
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
