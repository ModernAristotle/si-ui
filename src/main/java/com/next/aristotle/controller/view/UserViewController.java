package com.next.aristotle.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserViewController {


    @RequestMapping(value = {"/{fragmentFile}.html"})
    public ModelAndView genericEachHtmlView(ModelAndView mv, HttpServletRequest request, @PathVariable("fragmentFile") String fragmentFile) {
        mv.getModel().put("s3_static_content_dir", "https://s3.ap-south-1.amazonaws.com/siorg/website");
        mv.setViewName("test/" + fragmentFile);
        return mv;
    }

    @RequestMapping(value = {"/{directory}/{fragmentFile}.html"})
    public ModelAndView subPages(ModelAndView mv, HttpServletRequest request, @PathVariable("directory") String directory, @PathVariable("fragmentFile") String fragmentFile) {
        mv.getModel().put("s3_static_content_dir", "https://s3.ap-south-1.amazonaws.com/siorg/website");
        return getModelAndView(mv, request, directory + "/" + fragmentFile);
    }

    private ModelAndView getModelAndView(ModelAndView mv, HttpServletRequest request, String fragmentFile) {
        mv.getModel().put("fragmentFile", fragmentFile);
        mv.getModel().put("s3_static_content_dir", "https://s3.ap-south-1.amazonaws.com/siorg/website");

        mv.setViewName("user_template");
        return mv;
    }
}
