package com.mysite.sbb.article.controller;

import com.mysite.sbb.article.dao.ArticleRepository;
import com.mysite.sbb.article.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping("/test")
    @ResponseBody
    public String testFunc() {
        return "test";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Article> showList() {
        return articleRepository.findAll();
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Article showArticleDetail(@RequestParam long id, String name) {
        Optional<Article> article = articleRepository.findById(id);
       return article.orElse(null);
    }
    @RequestMapping("/doModify")
    @ResponseBody
    public Article doModify(long id, String title, String body) {
        Article article = articleRepository.findById(id).get();
        if(title != null) {
            article.setTitle(title);
        }
        if(body != null) {
            article.setBody(body);
        }
        articleRepository.save(article);

        return article;
        /* http://localhost:8082/usr/article/doModify?id=2&title=%EC%88%98%EC%A0%95%EB%90%9C%202%EB%B2%88%20%EC%A0%9C%EB%AA%A9&body=%EC%88%98%EC%A0%95%EB%90%9C%20%EB%82%B4%EC%9A%A92 */
    }

    @RequestMapping("/doDelete")
    @ResponseBody
    public String doDelete(long id) {

        articleRepository.deleteById(id);
            return "%d번 게시물이 삭제되었습니다".formatted(id);



        /* http://locaslhost:8082/usr/article/doDelete?id=2 */
    }
}

