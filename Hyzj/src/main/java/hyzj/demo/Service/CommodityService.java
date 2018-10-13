package hyzj.demo.Service;

import hyzj.demo.Dao.CommodityDao;
import hyzj.demo.Dao.YearDao;
import hyzj.demo.Exception.DataLinkException;
import hyzj.demo.Exception.FileException;
import hyzj.demo.ExceptionEnum.ExceptionEnum;
import hyzj.demo.Model.Commodity;
import hyzj.demo.Model.Year;
import hyzj.demo.ModelVo.CommodityVo;
import hyzj.demo.Utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommodityService {

    @Resource
    CommodityDao commodityDao;

    @Resource
    YearDao yearDao;

    public List<CommodityVo> loadCommodity() {
        List<CommodityVo> commodityVos = new ArrayList<>();

        List<Commodity> commodities = new ArrayList<>();

        commodities = commodityDao.loadCommodity();

        for (Commodity commodity : commodities) {
            CommodityVo commodityVo = new CommodityVo();
            Year year = new Year();
            year = yearDao.selectByYearid(commodity.getY_id());
            commodityVo.setCommodity(commodity);
            commodityVo.setYear(year);
            commodityVos.add(commodityVo);
        }
        return commodityVos;

    }

    @Transactional
    public CommodityVo addCommodity(HttpServletRequest request, HttpSession session, MultipartFile detailfile) {

       CommodityVo commodityVo = new CommodityVo();
       Commodity commoditys  = new Commodity();

        String commodity = request.getParameter("commodity");
        String addname  = request.getParameter("addname");
        String year = request.getParameter("year");
        String stock= String.valueOf(request.getParameter("stock"));
        float price = Float.valueOf(request.getParameter("price"));
        float purchase_price = Float.valueOf(request.getParameter("purchaseprice"));
        String shelfDate = request.getParameter("shelfDate");
        String productionDate  = request.getParameter("productionDate");


        System.out.println(commodity+":"+
                addname+":"+
                year+":"+
                stock+":"+
                price+":"+
                purchase_price+":"+
                shelfDate+":"+
                productionDate
        );

        try {
            String ctimes = System.currentTimeMillis() + "";
            System.out.println("开始");
//            String path1 = request.getSession().getServletContext().getRealPath("goods");
            String path = "/usr/local/upload/goods/";
            String detailfileName = ctimes + ".jpg";
            System.out.println(detailfileName);
//            System.out.println(path);
//            System.out.println(path1);
//            File targetFile = new File(path1,detailfileName);
//            if (!targetFile.exists()) {
//                targetFile.mkdirs();
//            }
//
//            //保存
//            try {
//                detailfile.transferTo(targetFile);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            try {
                FileUtils.uploadFile(detailfile.getBytes(), path, detailfileName);

            }catch (Exception e){
                throw  new FileException(ExceptionEnum.FileException);
            }

            String detailphoto = detailfileName;
            //获取当前时间
            Date now = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowtime = df.format(now);
            System.out.println(nowtime);

            boolean ok = commodityDao.add(
                    commodity,
                    addname,
                    year,
                    stock,
                    price,
                    purchase_price,
                    shelfDate,
                    productionDate,
                    detailphoto,
                    nowtime
            );
        } catch (Exception e) {
           throw  new DataLinkException(e, ExceptionEnum.DATALINK_Exception);
        }

        try{
            commoditys = commodityDao.loadCommodityByid(commodity);
            Year years = new Year();
            years = yearDao.selectByYearid(commoditys.getY_id());

            commodityVo.setYear(years);
            commodityVo.setCommodity(commoditys);
        }catch(Exception e){
            throw new DataLinkException(e, ExceptionEnum.DATALINK_Exception);

        }


        return commodityVo;
    }

    public boolean deleteCommodity(String cid) {
       try{
           /**
            * 先删除图片
            */
           Commodity commodity = new Commodity();
           commodity = commodityDao.loadCommodityByid(cid);
           String path = "/usr/local/upload/goods/";
           String photo = commodity.getImage();
           //删除对应的图片
           String d_detailphoto = path + photo;
           System.out.println(d_detailphoto);
           File targetFile = new File(d_detailphoto);
            try{
                if (targetFile.exists()) {
                    targetFile.delete();
                }
            }catch(Exception e){
                throw new FileException(ExceptionEnum.FileException);

            }

          return commodityDao.deleteCommodity(cid);
       }catch(Exception e){
           throw new DataLinkException(e, ExceptionEnum.DATALINK_Exception);

       }


    }

    /**
     * 通过id 查找商品
     * @param cid
     * @return
     */
    public CommodityVo loadById(String cid) {
        try{
            CommodityVo commodityVo = new CommodityVo();
            Commodity commodity = commodityDao.loadCommodityByid(cid);
            Year year = yearDao.selectByYearid(commodity.getY_id());

            commodityVo.setYear(year);
            commodityVo.setCommodity(commodity);
        return  commodityVo;
        }catch(Exception e){
            throw new DataLinkException(e, ExceptionEnum.DATALINK_Exception);

        }
    }

    /**
     * 更新
     * @param request
     * @param session
     * @param detailfile
     * @return
     */
    public CommodityVo updateCommodity(HttpServletRequest request, HttpSession session, MultipartFile detailfile) {
        String commodity = request.getParameter("commodity");
        String addname  = request.getParameter("addname");
        String year = request.getParameter("year");
        String stock= String.valueOf(request.getParameter("stock"));
        float price = Float.valueOf(request.getParameter("price"));
        float purchase_price = Float.valueOf(request.getParameter("purchaseprice"));
        String shelfDate = request.getParameter("shelfDate");
        String productionDate  = request.getParameter("productionDate");
        String editDetailphoto = "";
        try{
            String path = "D:/goods/";
            Commodity commodity1 = commodityDao.loadCommodityByid(commodity);
            //检测是否修改了图片
            if (detailfile!=null && !detailfile.getOriginalFilename().equals("")) {
                //先把眼镜查询出来

                String photo = commodity1.getImage();
                //删除对应的图片
                String d_detailphoto = path + photo;
                System.out.println(d_detailphoto);
                File targetFile = new File(d_detailphoto);
                if (targetFile.exists()) {
                    targetFile.delete();
                }

                String ctimes = System.currentTimeMillis() + "";
                System.out.println("开始，保存");
                String newDetailphoto = ctimes + ".jpg";
                System.out.println(newDetailphoto);
                System.out.println(path);

                FileUtils.uploadFile(detailfile.getBytes(), path, newDetailphoto);
//                File editFile = new File(path, newDetailphoto);
//                if (!editFile.exists()) {
//                    editFile.mkdirs();
//                }
                editDetailphoto = newDetailphoto;

            }else{
                editDetailphoto = commodity1.getImage();
            }


        }catch(Exception e){
            throw new  FileException(ExceptionEnum.FileException);

        }

        boolean ok = commodityDao.update(
                commodity,
                addname,
                year,
                stock,
                price,
                purchase_price,
                shelfDate,
                productionDate,
                editDetailphoto

        );
        CommodityVo commodityVo = new CommodityVo();
        Commodity commodity2 = commodityDao.loadCommodityByid(commodity);
        Year year1 = yearDao.selectByYearid(commodity2.getY_id());

        commodityVo.setCommodity(commodity2);
        commodityVo.setYear(year1);

        return commodityVo;
    }
}
