package hyzj.demo.Controller;

import hyzj.demo.ExceptionEnum.ExceptionEnum;
import hyzj.demo.ModelVo.StockVo;
import hyzj.demo.RsultModel.R_data;
import hyzj.demo.Service.StockService;
import hyzj.demo.Utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("stock")
public class StockController {

    @Autowired
    StockService stockService;

    @RequestMapping("/list")
    @ResponseBody
    public R_data<List<StockVo>> loadStock() throws Exception{
        return ResultUtils.success(stockService.loadStock(),ExceptionEnum.SUCCESS);
    }
    @RequestMapping("/update")
    @ResponseBody
    public R_data update(String cid, String stock,String unmodifiedNumble , HttpSession session){
        return ResultUtils.success(stockService.update(cid,stock,unmodifiedNumble,session),ExceptionEnum.SUCCESS);

    }
}
