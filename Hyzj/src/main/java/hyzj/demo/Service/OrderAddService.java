package hyzj.demo.Service;

import hyzj.demo.Dao.*;
import hyzj.demo.Model.*;
import hyzj.demo.Utils.CodeUtils;
import hyzj.demo.Utils.IncomeAddUtils;
import hyzj.demo.Utils.TimeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderAddService {

    @Resource
    OrderAddDao orderAddDao;

    @Resource
    IncomeDao incomeDao;

    @Resource
    MallUserDao mallUserDao;

    @Resource
    ProjectDao projectDao;

    @Resource
    AgentDao agentDao;

    @Resource
    CommodityDao commodityDao;

    @Resource
    IncomeDayDao incomeDayDao;

    @Resource
    IncomeMonthDao incomeMonthDao;

    @Resource
    IncomeYearDao incomeYearDao;

    /**
     * 获取补录记录
     * @return
     */
    public List<OrderAdd> loadList() {

        System.out.println("补录表记录：" + orderAddDao.loadList());
        return orderAddDao.loadList();
    }

    /**
     * 添加补录信息order_add,并向income表中插入数据
     * @param single_people
     * @param single_phone
     * @param c_id
     * @param number
     * @param price
     * @param add_describe
     * @return
     */
    public boolean add(String single_people,String single_phone,String c_id,
                       String number,String price,String add_describe) {

        CodeUtils codeUtils = new CodeUtils();
        IncomeAddUtils incomeAddUtils = new IncomeAddUtils();

        try{

            String In_id = "In" + codeUtils.createRandom(false,7);
            String add_record = "AR" + codeUtils.createRandom(false,6) + c_id;
            String add_time = TimeUtils.getTime();
            //System.out.println("add_time:" + add_time);

            /**
             * 通过手机号single_phone查询该用户是代理商、投资人还是发起人R_id,拿到M_id。
             * 投资人，发起人：通过获取到的M_id,从projects表找到永久折扣，
             * 代理商：通过获取到的M_id,从agent表找到永久折扣，
             * 根据其永久折扣,商品的price承折扣算出该投资人或代理商价格，price减去该价格得出投资人或代理商赚取金额income_amount;
             *
             * 根据C_id获取商品进价purchase_price，投资人或代理商价格减去purchase_price算出unallocated_amount
             */

            float discount = 0;
            MallUser mallUser = mallUserDao.searchByPhone(single_phone);
            if (mallUser.getR_id() == "1" || mallUser.getR_id() == "2"){

                Project project = projectDao.searchByMid(mallUser.getM_id());
                discount = project.getDiscount_lowest();

            }else if (mallUser.getR_id() == "4"){
                Agent agent = agentDao.searchByMid(mallUser.getM_id());
                discount = Float.parseFloat(agent.getDiscount());
            }else{
                System.out.println("可能是其他新的角色");
            }

            Commodity commodity = commodityDao.loadCommodityByid(c_id);

            //投资人或者代理商使用永久折扣后得出的价格discountedPrice(单价)
            float discountedPrice = Float.parseFloat(price)*discount;

            //投资人或代理商个人赚取金额income_amount
            String income_amount = (Float.parseFloat(price) - discountedPrice)+"";

            //未分配利润
            String unallocated_amount = (discountedPrice - commodity.getPurchase_price()*Float.parseFloat(number))+"";

            boolean b1 = incomeDao.add(In_id,mallUser.getM_id(),add_time,income_amount,unallocated_amount);
            boolean b2 = orderAddDao.add(add_record,single_people,single_phone,c_id,number,price,add_time,add_describe,In_id);

            //插入和更新income_day表的数据
            boolean Income_day = incomeAddUtils.updateIncomeDay(add_time,income_amount,incomeDayDao);

            //插入或更新income_month表的数据
            boolean Income_month = incomeAddUtils.updateIncomeMonth(add_time,income_amount,incomeMonthDao);

            //插入火更新income_year表的数
            boolean Income_year = incomeAddUtils.updateIncomeYear(add_time,income_amount,incomeYearDao);

            if (b1 == true && b2 == true && Income_day == true && Income_month == true && Income_year == true){
                return true;
            }else {
                return false;
            }

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 更新补录信息
     * @param add_record
     * @param single_people
     * @param single_phone
     * @param c_id
     * @param number
     * @param price
     * @param add_describe
     * @return
     */
    public boolean update(String add_record, String single_people, String single_phone, String c_id, String number, String price, String add_describe) {

        String add_time = TimeUtils.getTime();
        OrderAdd orderAdd = orderAddDao.searchByAddRecord(add_record);

        Income income = incomeDao.searchByInid(orderAdd.getIn_id());

        //更新income
        float discount = 0;
        MallUser mallUser = mallUserDao.searchByPhone(single_phone);
        if (mallUser.getR_id() == "1" || mallUser.getR_id() == "2"){

            Project project = projectDao.searchByMid(mallUser.getM_id());
            discount = project.getDiscount_lowest();

        }else if (mallUser.getR_id() == "4"){
            Agent agent = agentDao.searchByMid(mallUser.getM_id());
            discount = Float.parseFloat(agent.getDiscount());
        }else{
            System.out.println("这是个普通用户");
        }

        Commodity commodity = commodityDao.loadCommodityByid(c_id);

        //投资人或者代理商使用永久折扣后得出的价格discountedPrice(折后总价)
        float discountedPrice = Float.parseFloat(price)*discount;

        //投资人或代理商个人赚取金额income_amount
        String income_amount = (Float.parseFloat(price) - discountedPrice) + "";

        //未分配利润
        String unallocated_amount = (discountedPrice - commodity.getPurchase_price()*Float.parseFloat(number))+"";

        boolean b1 = incomeDao.update(orderAdd.getIn_id(),mallUser.getM_id(),add_time,income_amount,unallocated_amount);
        boolean b2 = orderAddDao.update(add_record,single_people,single_phone,c_id,number,price,add_time,add_describe);

        if (b1 && b2 == true){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 删除补录信息
     * @param add_record
     * @return
     */
    public boolean delete(String add_record) {
        return orderAddDao.delete(add_record);
    }
}
