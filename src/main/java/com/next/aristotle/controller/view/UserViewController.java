package com.next.aristotle.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserViewController {


    @RequestMapping(value = {"/template/index.html", "/"})
    public ModelAndView homePage(ModelAndView mv, HttpServletRequest request) {
        mv.getModel().put("s3_static_content_dir", "https://s3.ap-south-1.amazonaws.com/siorg/website");
        mv.getModel().put("s3_dynamic_css_dir", "");
        mv.setViewName("local/index");
        addPageData(mv, request);
        return mv;
    }

    @RequestMapping(value = {"/template/{fragmentFile}.html"})
    public ModelAndView genericEachHtmlView(ModelAndView mv, HttpServletRequest request, @PathVariable("fragmentFile") String fragmentFile) {
        return getModelAndView(mv, request, "pageContent/" + fragmentFile);
    }


    @RequestMapping(value = {"/template/{directory}/{fragmentFile}.html"})
    public ModelAndView subPages(ModelAndView mv, HttpServletRequest request, @PathVariable("directory") String directory, @PathVariable("fragmentFile") String fragmentFile) {
        mv.getModel().put("s3_static_content_dir", "https://s3.ap-south-1.amazonaws.com/siorg/website");
        mv.getModel().put("s3_dynamic_css_dir", "");
        return getModelAndView(mv, request, directory + "/" + fragmentFile);
    }

    private ModelAndView getModelAndView(ModelAndView mv, HttpServletRequest request, String fragmentFile) {
        mv.getModel().put("pageContentFile", fragmentFile);
        mv.getModel().put("s3_static_content_dir", "https://s3.ap-south-1.amazonaws.com/siorg/website");
        mv.getModel().put("s3_dynamic_css_dir", "");
        mv.setViewName("local/single-column");
        addPageData(mv, request);
        return mv;
    }

    private void addPageData(ModelAndView mv, HttpServletRequest httpServletRequest) {
        String title = "Not Implemented";

        if (httpServletRequest.getRequestURI().startsWith("/events.html")) {
            title = "Events";
        } else if (httpServletRequest.getRequestURI().startsWith("/pressRelease.html")) {
            title = "Press Releases";
        } else if (httpServletRequest.getRequestURI().startsWith("/contactus.html")) {
            title = "Contact US";
        }
        mv.getModel().put("pageTitle", title);

    }
}
