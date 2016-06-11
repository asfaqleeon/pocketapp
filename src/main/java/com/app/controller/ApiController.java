package com.app.controller;

import com.app.domain.Account;
import com.app.domain.Link;
import com.app.domain.LinkDetails;
import com.app.message.LinkUrl;
import com.app.message.Message;
import com.app.message.ParseLinkDetails;
import com.app.repository.AccountRepository;
import com.app.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by user1 on 10/06/2016.
 */

@RestController
public class ApiController {
    private static final String SECRET_KEY = "a secret key";

    @Autowired
    LinkRepository linkRepository;

    @Autowired
    AccountRepository accountRepository;

    private String verifyToken(String token) {
        String user="";
        try {
            byte[] eToken = Base64.getDecoder().decode(token);
            String payload = new String(eToken, "UTF-8");
            //System.out.println("decoded token: " + payload);

            String key = payload.substring(0, SECRET_KEY.length());
            if(!"".equals(key) && !key.isEmpty() && key.equals(SECRET_KEY)){
                user = payload.substring(SECRET_KEY.length()+1);
                //System.out.println("infos: " + key + " " + user);
            }

        } catch (Exception e) {
            user = e.toString();
        }
        return user;
    }

    @RequestMapping(value = "/api/add", method = RequestMethod.POST)
    public Message addLink(@RequestBody LinkUrl linkUrl,
                           @RequestHeader("x-access-token")String token,
                           HttpServletResponse response){

        System.out.println(verifyToken(token));

        Link link = new Link();
        link.setAccount(accountRepository.findOneByEmail(verifyToken(token)));
        link.setUrl(linkUrl.getUrl());
        link.setCategory(linkUrl.getCategory());

        LinkDetails linkDetails = new LinkDetails();
        ParseLinkDetails parseLinkDetails = new ParseLinkDetails(linkUrl.getUrl());
        linkDetails.setTitle(parseLinkDetails.title());
        linkDetails.setDescription(parseLinkDetails.description());

        link.setLinkDetails(linkDetails);
        linkRepository.save(link);
        return new Message("link saved");
    }

    @RequestMapping(value = "/api/list", method = RequestMethod.GET)
    public @ResponseBody List<Link>  listAll(@RequestHeader("x-access-token")String token){
        System.out.println(verifyToken(token));
        Account account = accountRepository.findOneByEmail(verifyToken(token));

        List<Link> all = linkRepository.findAllByAccount(account,
                new Sort(new Sort.Order(Sort.Direction.DESC, "linkId")));

        return all;
    }

    @RequestMapping(value = "/api/delete", method = RequestMethod.DELETE)
    public Message removeLink(@RequestParam("id") int linkId){
        linkRepository.delete(linkId);
        return new Message("deleted");
    }

}
