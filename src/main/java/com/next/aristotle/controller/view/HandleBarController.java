package com.next.aristotle.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by ravi on 23/12/16.
 */
@Controller
public class HandleBarController {

    @RequestMapping(value = {"/index.html"})
    public ModelAndView genericEachHtmlView(ModelAndView mv, HttpServletRequest request) throws Exception {
        return getModelAndView(mv, request, "/template/index.html", "templates/local/data/index.json");
    }


    @RequestMapping(value = {"/content/news/{id}/{newsTitle}.html", "/content/pressrelease/{id}/{newsTitle}.html"})
    public ModelAndView singleNewsPage(ModelAndView mv, HttpServletRequest request, @PathVariable("id") String id, @PathVariable("newsTitle") String newsTitle) throws Exception {
        return getModelAndView(mv, request, "/template/pageContent/single-news.html", "templates/local/data/single-news.json");
    }

    @RequestMapping(value = {"/content/news_press_release.html"})
    public ModelAndView newsListPage(ModelAndView mv, HttpServletRequest request, @PathVariable("id") String id, @PathVariable("newsTitle") String newsTitle) throws Exception {
        return getModelAndView(mv, request, "/template/pageContent/news-list.html", "templates/local/data/news-list.json");
    }

    private ModelAndView getModelAndView(ModelAndView mv, HttpServletRequest request, String fragmentFile, String dataFile) throws Exception {
        mv.setViewName("local/generic");
        mv.getModel().put("data", readFile(dataFile));
        mv.getModel().put("template", fragmentFile);
        return mv;
    }

    private String readFile(String pathname) throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(pathname);

        try {
            Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString().replaceAll("\\n", "");
        } finally {
            // Potential issue here: if this throws an IOException,
            // it will mask any others. Normally I'd use a utility
            // method which would log exceptions and swallow them
            in.close();
        }
    }
}
