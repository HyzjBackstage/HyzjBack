package hyzj.demo.Service;

import hyzj.demo.Dao.StockDao;
import hyzj.demo.Exception.DataLinkException;
import hyzj.demo.Exception.NoLoginException;
import hyzj.demo.ExceptionEnum.ExceptionEnum;
import hyzj.demo.ModelVo.StockVo;
import hyzj.demo.ModelVo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StockService {

    @Resource
    StockDao stockDao;

    public List<StockVo> loadStock() {
        try {
            return stockDao.loadStock();
        } catch (Exception e) {
            throw new DataLinkException(e, ExceptionEnum.DATALINK_Exception);

        }
    }

    @Transactional
    public boolean update(String cid, String stock, String unmodifiedNumble, HttpSession session) {
        try {
            UserVo userVo = (UserVo) session.getAttribute("UserVo");
            if (userVo == null) {
                throw new NoLoginException(ExceptionEnum.NoLogin_Exception);
            }
            String mid = userVo.getUser().getM_id();
            //获取当前时间
            Date now = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowtime = df.format(now);
            System.out.println(nowtime);

            if (stockDao.addModifuedStock(cid, stock, unmodifiedNumble,mid,nowtime))
                return stockDao.update(cid, stock);
            else return false;
        } catch (Exception e) {
            throw new DataLinkException(e, ExceptionEnum.DATALINK_Exception);
        }
    }
}
