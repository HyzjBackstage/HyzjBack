package hyzj.demo.Utils;

import hyzj.demo.Dao.IncomeDayDao;
import hyzj.demo.Dao.IncomeMonthDao;
import hyzj.demo.Dao.IncomeYearDao;

import javax.annotation.Resource;

public class IncomeAddUtils {



    //插入income_month表中，按年
    public boolean updateIncomeYear(String rebate_time, String rebate_amount, IncomeYearDao incomeYearDao) {
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
    public boolean updateIncomeMonth(String rebate_time, String rebate_amount,IncomeMonthDao incomeMonthDao) {
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
    public boolean updateIncomeDay(String rebate_time,String rebate_amount,IncomeDayDao incomeDayDao){
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
}
