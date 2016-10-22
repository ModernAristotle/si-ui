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
//    @RequestMapping(value = {"/me/basic.templates"})
//    public String basic(ModelAndView mv, HttpServletRequest request) {
//        return "user/basic";
//    }
//
//    @RequestMapping(value = {"/home", "/index.templates"})
//    public ModelAndView userHome(ModelAndView mv, HttpServletRequest request) {
//        mv = getModelAndView(mv, request, "home", "user/home");
//        return mv;
//    }
//
//    @RequestMapping(value = {"/me/editProfile", "/me/editProfile.templates"})
//    public ModelAndView userEditProfile(ModelAndView mv, HttpServletRequest request) {
//        mv = getModelAndView(mv, request, "edit_profile", "user/edit_profile");
//        return mv;
//    }
//
//    @RequestMapping(value = {"/me/donations", "/me/donations.templates"})
//    public ModelAndView userDonations(ModelAndView mv, HttpServletRequest request) {
//        mv = getModelAndView(mv, request, "my_donations", "user/my_donations");
//        return mv;
//    }
//
//    @RequestMapping(value = {"/me/awaz  ", "/me/awaz.templates"})
//    public ModelAndView userAwaz(ModelAndView mv, HttpServletRequest request) {
//        mv = getModelAndView(mv, request, "awaz", "user/awaz");
//        return mv;
//    }
//
//    @RequestMapping(value = {"/me/{fragmentName}.templates"})
//    public ModelAndView genericView(ModelAndView mv, HttpServletRequest request, @PathVariable("fragmentName") String fragmentName) {
//        mv = getModelAndView(mv, request, fragmentName, "user/" + fragmentName);
//        return mv;
//    }


    private ModelAndView getModelAndView(ModelAndView mv, HttpServletRequest request, String fragmentFile) {
        mv.getModel().put("fragmentFile", fragmentFile);
        mv.getModel().put("s3_static_content_dir", "https://s3.ap-south-1.amazonaws.com/siorg/website");

        mv.setViewName("user_template");
        return mv;
    }
}
