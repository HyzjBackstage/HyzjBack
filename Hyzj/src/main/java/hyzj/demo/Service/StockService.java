package hyzj.demo.Service;

import hyzj.demo.Dao.StockDao;
import hyzj.demo.Exception.DataLinkException;
import hyzj.demo.ExceptionEnum.ExceptionEnum;
import hyzj.demo.ModelVo.StockVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class StockService {

    @Resource
    StockDao stockDao;

    public List<StockVo> loadStock() {
        try{
        return stockDao.loadStock();
        }catch(Exception e){
            throw new DataLinkException(e,ExceptionEnum.DATALINK_Exception);

        }
    }

    public boolean update(String cid, String stock) {
        try{
            return stockDao.update(cid,stock);
        }catch(Exception e){
            throw new DataLinkException(e,ExceptionEnum.DATALINK_Exception);
        }
    }
}
