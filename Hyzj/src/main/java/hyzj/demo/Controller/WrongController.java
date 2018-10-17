package hyzj.demo.Controller;


import hyzj.demo.Service.TestServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class WrongController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @Autowired
    TestServive testServive;
    //
    @RequestMapping(value=ERROR_PATH)
    public String index(Model model, HttpServletResponse response) {

        return "404";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
