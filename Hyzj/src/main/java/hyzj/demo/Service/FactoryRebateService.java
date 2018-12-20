package hyzj.demo.Service;

import hyzj.demo.Dao.*;
import hyzj.demo.Model.FactoryRebate;
import hyzj.demo.Model.Income;
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
            boolean Income_day = updateIncomeDay(rebate_time,rebate_amount);

            //插入或更新income_month表的数据
            boolean Income_month = updateIncomeMonth(rebate_time,rebate_amount);

            //插入火更新income_year表的数
            boolean Income_year = updateIncomeYear(rebate_time,rebate_amount);

            if (factoryRebate == true && Income_day == true && Income_month == true && Income_year == true){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    //插入income_month表中，按年
    private boolean updateIncomeYear(String rebate_time, String rebate_amount) {
        String Income_year_id = "#In_year2018";
        String rebate_time_year = "2018";
        if (rebate_time.indexOf(" ") == -1){
            rebate_time_year = rebate_time.substring(0,rebate_time.indexOf("-"));
            Income_year_id = "#In_year" + rebate_time_year;
        }
        //按时间升序，查询数据库中最新的一条数据，查看时间是否相同，相同则修改金额，不相同则添加新的纪录
        boolean updateIn_year = false;
        boolean addIn_Year = false;
        try{
            //字符串的比较，注意，知识点
            if (Income_year_id.equals(incomeYearDao.searchIn_year_id())){
                updateIn_year = incomeYearDao.updateIncomeYear(rebate_amount);
            }else{
                addIn_Year = incomeYearDao.addIncomeYear(Income_year_id,rebate_time_year,rebate_amount);
            }
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        if (updateIn_year==true || addIn_Year==true){
            return true;
        }else {
            return false;
        }
    }

    //插入income_month表中，按月
    private boolean updateIncomeMonth(String rebate_time, String rebate_amount) {
        String Income_month_id = "#In_month2018-12";
        String rebate_time_month = "2018-12";
        if (rebate_time.indexOf(" ") == -1){
            rebate_time_month = rebate_time.substring(0,rebate_time.indexOf("-",rebate_time.indexOf("-")+1));
            Income_month_id = "#In_month" + rebate_time_month;
        }
        //按时间升序，查询数据库中最新的一条数据，查看时间是否相同，相同则修改金额，不相同则添加新的纪录
        boolean updateIn_month = false;
        boolean addIn_Month = false;
        try{
//            System.out.println("searchIn_month_id:"+incomeMonthDao.searchIn_month_id());
//            System.out.println("Income_month_id"+Income_month_id);
//            System.out.println(Income_month_id.equals(incomeMonthDao.searchIn_month_id()));
            if (Income_month_id.equals(incomeMonthDao.searchIn_month_id())){
                updateIn_month = incomeMonthDao.updateIncomeMonth(rebate_amount);
            }else{
                addIn_Month = incomeMonthDao.addIncomeMonth(Income_month_id,rebate_time_month,rebate_amount);
            }
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        if (updateIn_month==true || addIn_Month==true){
            return true;
        }else {
            return false;
        }
    }

    //插入income_day表中，按天
    private boolean updateIncomeDay(String rebate_time,String rebate_amount){
        String Income_day_id = "#In_day2018-12-18";
        String rebate_time_day = "2018-12-18";
        //System.out.println("rebate time" + rebate_time);
        if (rebate_time.indexOf(" ") == -1){
            rebate_time_day = rebate_time.substring(0,rebate_time.indexOf(" "));
            Income_day_id = "#In_day" + rebate_time_day;
        }
        //按时间升序，查询数据库中最新的一条数据，查看时间是否相同，相同则修改金额，不相同则添加新的纪录
        boolean updateIn_day = false;
        boolean addIn_Day = false;
        try{
            if (Income_day_id.equals(incomeDayDao.searchIn_day_id())){
                updateIn_day = incomeDayDao.updateIncomeDay(rebate_amount);
            }else{
                addIn_Day = incomeDayDao.addIncomeDay(Income_day_id,rebate_time_day,rebate_amount);
            }
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        if (updateIn_day==true || addIn_Day==true){
            return true;
        }else {
            return false;
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
