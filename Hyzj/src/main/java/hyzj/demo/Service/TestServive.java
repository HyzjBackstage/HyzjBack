package hyzj.demo.Service;

import hyzj.demo.Dao.TestDao;
import hyzj.demo.Exception.DataLinkException;
import hyzj.demo.ExceptionEnum.ExceptionEnum;
import hyzj.demo.Model.T_girl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service()
public class TestServive {

    @Resource
    private TestDao testDao;

    public List<T_girl> loadGirl() throws Exception{

        try {
            return testDao.loadGirl();
        }
        catch (Exception e){
            throw new DataLinkException(ExceptionEnum.DATALINK_Exception);
        }


    }
}
