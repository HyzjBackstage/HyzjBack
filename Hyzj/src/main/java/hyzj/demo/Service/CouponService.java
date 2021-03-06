package hyzj.demo.Service;

import hyzj.demo.Dao.CouponDao;
import hyzj.demo.Exception.DataLinkException;
import hyzj.demo.ExceptionEnum.ExceptionEnum;
import hyzj.demo.Model.CouponType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CouponService {

    @Resource
    CouponDao couponDao;

    /**
     * 折扣优惠
     *
     * @return
     */
    public List<CouponType> loadDiscount() {
        try {
            return couponDao.loadDiscount();
        } catch (Exception e) {
            throw new DataLinkException(e, ExceptionEnum.DATALINK_Exception);

        }
    }

    //添加
    @Transactional
    public boolean addDiscount(int cid, String discount, String strat, String end, String condition) {
        try {
            System.out.println("12345:::" + condition);
            return couponDao.addDiscount(String.valueOf(cid), discount, strat, end, condition);
        } catch (Exception e) {
            throw new DataLinkException(e, ExceptionEnum.DATALINK_Exception);

        }
    }

    //删除
    public boolean deleteDiscount(String coid) {
        try {
            return couponDao.deleteDiscount(coid);
        } catch (Exception e) {
            throw new DataLinkException(e, ExceptionEnum.DATALINK_Exception);

        }
    }

    //更新
    public boolean updateDiscount(int cid, int discount, String strat, String end, String condition) {
        try {
            return couponDao.updateDiscount(String.valueOf(cid), String.valueOf(discount), strat, end, condition);

        } catch (Exception e) {
            throw new DataLinkException(e, ExceptionEnum.DATALINK_Exception);
        }

    }

    /**
     * 金额优惠
     *
     * @return
     */
    public List<CouponType> loadAmount() {

        try {
            return couponDao.loadAmount();
        } catch (Exception e) {
            throw new DataLinkException(e, ExceptionEnum.DATALINK_Exception);

        }
    }


    //添加
    public boolean addAmount(int cid, String amount, String strat, String end, String condition) {
        try {
            return couponDao.addAmount(String.valueOf(cid), amount, strat, end, condition);
        } catch (Exception e) {
            throw new DataLinkException(e, ExceptionEnum.DATALINK_Exception);

        }
    }

    public boolean updateAmount(String cid, String amount, String strat, String end, String condition) {
        try {
            return couponDao.updateAmount(cid, amount, strat, end, condition);
        } catch (Exception e) {
            throw new DataLinkException(e, ExceptionEnum.DATALINK_Exception);

        }
    }

    public boolean deleteAmount(String coid) {
        try {
            return couponDao.deleteAmount(coid);
        } catch (Exception e) {
            throw new DataLinkException(e, ExceptionEnum.DATALINK_Exception);

        }
    }
}
