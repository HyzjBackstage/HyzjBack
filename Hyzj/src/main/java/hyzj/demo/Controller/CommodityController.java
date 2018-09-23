package hyzj.demo.Controller;

import hyzj.demo.ExceptionEnum.ExceptionEnum;
import hyzj.demo.ModelVo.CommodityVo;
import hyzj.demo.RsultModel.R_data;
import hyzj.demo.Service.CommodityService;
import hyzj.demo.Utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("commodity")
public class CommodityController {

    @Autowired
    CommodityService commodityService;

    @RequestMapping("/list")
    @ResponseBody
    public R_data<List<CommodityVo>> loadCommodity() {
        return ResultUtils.success(commodityService.loadCommodity(), ExceptionEnum.SUCCESS);
    }

    @RequestMapping("/add")
    @ResponseBody
    public R_data<CommodityVo> addCommodity(HttpServletRequest request, HttpSession session, MultipartFile detailfile) throws Exception {
        return ResultUtils.success(commodityService.addCommodity(request, session, detailfile), ExceptionEnum.SUCCESS);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public R_data deleteCommodity(String cid) throws Exception {
        return ResultUtils.success(commodityService.deleteCommodity(cid), ExceptionEnum.SUCCESS);
    }
    //通过id 获取商品
    @RequestMapping("/loadById")
    @ResponseBody
    public R_data<CommodityVo> loadById(String cid){
        return ResultUtils.success(commodityService.loadById(cid),ExceptionEnum.SUCCESS);
    }

    @RequestMapping("/update")
    @ResponseBody
    public R_data<CommodityVo> updateCommodity( HttpServletRequest request, HttpSession session, MultipartFile detailfile ){
        return ResultUtils.success(commodityService.updateCommodity(request,session,detailfile),ExceptionEnum.SUCCESS);

    }
}
