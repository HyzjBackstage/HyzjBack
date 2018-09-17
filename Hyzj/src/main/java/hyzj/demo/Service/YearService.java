package hyzj.demo.Service;

import hyzj.demo.Dao.YearDao;
import hyzj.demo.Exception.DataLinkException;
import hyzj.demo.ExceptionEnum.ExceptionEnum;
import hyzj.demo.Model.Year;
import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class YearService {

    @Resource
    YearDao yearDao;

    /**
     * 获取年份（规格）
     *
     * @return
     */
    public List<Year> loadYear(){
        try {
            return yearDao.loadYear();
        } catch (Exception e) {
            throw new DataLinkException(e,ExceptionEnum.DATALINK_Exception);

        }
    }

    @Transactional
    public boolean addYear( String yid, String name)  {
        try{
            return yearDao.addYear(yid,name);
        }catch(Exception e){
            throw new DataLinkException(e,ExceptionEnum.DATALINK_Exception);

        }
    }
    @Transactional
    public boolean delete(String y_id) {
        try{
            return yearDao.delete(y_id);
        }catch(Exception e){
            throw new DataLinkException(e,ExceptionEnum.DATALINK_Exception);

        }
    }

    public boolean update(String yid, String name) {
        try{
            return yearDao.update(yid,name);
        }catch(Exception e){
            throw new DataLinkException(e,ExceptionEnum.DATALINK_Exception);

        }
    }
}
