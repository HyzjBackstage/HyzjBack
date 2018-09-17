package hyzj.demo.Controller;


import hyzj.demo.ExceptionEnum.ExceptionEnum;
import hyzj.demo.Model.Year;
import hyzj.demo.RsultModel.R_data;
import hyzj.demo.Service.YearService;
import hyzj.demo.Utils.ResultUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("year")
public class YearController {

    @Autowired
    YearService yearService;

    @RequestMapping("/list")
    @ResponseBody
    public R_data<List<Year>> loadYear() throws Exception {
        System.out.println(ResultUtils.success(yearService.loadYear(), ExceptionEnum.SUCCESS));

        return ResultUtils.success(yearService.loadYear(), ExceptionEnum.SUCCESS);
    }


    @RequestMapping("/add")
    @ResponseBody
    public R_data addYear(String yid, String name) throws Exception {
//        System.out.println("yid"+yid+":"+"name"+name);
        return ResultUtils.success(yearService.addYear(yid, name), ExceptionEnum.SUCCESS);
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public R_data delete(String y_id) {
        return ResultUtils.success(yearService.delete(y_id), ExceptionEnum.SUCCESS);
    }

    /**
     * 更新
     */
    @RequestMapping("/update")
    @ResponseBody
    public R_data update(String yid,String name) {
        return ResultUtils.success(yearService.update(yid,name),ExceptionEnum.SUCCESS);
    }

}
