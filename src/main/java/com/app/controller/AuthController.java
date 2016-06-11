package com.app.controller;

import com.app.domain.Account;
import com.app.message.Auth;
import com.app.message.Message;
import com.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

/**
 * Created by user1 on 10/06/2016.
 */

@RestController
public class AuthController {
    private static final String SECRET_KEY = "a secret key";
    private static final String PUBLIC_KEY = "public key";

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public Message login(@RequestBody Auth auth,
                         @RequestHeader("x-access-token")String token,
                         HttpServletResponse response){

        String encoded="";
        try {
            byte[] dname = Base64.getDecoder().decode(auth.getEmail());
            byte[] dpass = Base64.getDecoder().decode(auth.getPassword());

            String name = new String(dname, "UTF-8");
            String pass = new String(dpass, "UTF-8");

            Account users = accountRepository.findOneByEmailAndPassword(name,pass);
            System.out.println(users.toString());
            if(users != null) {

                byte[] dPublicKey = Base64.getDecoder().decode(token);
                String publicKey = new String(dPublicKey, "UTF-8");
                System.out.println("decoded token: " + publicKey);

                if (publicKey.equals(PUBLIC_KEY)) {
                    String addKey = SECRET_KEY + ":" + name;

                    encoded = Base64.getEncoder().encodeToString(addKey.getBytes("utf-8"));
                    System.out.println("encoded string: " + encoded);

                    response.setHeader("mkey", encoded);

                    return new Message("success login");
                } else {
                    return new Message("login failed");
                }
            } else {
                return new Message("login failed");
            }

        } catch (Exception e){
            System.out.println(e.toString());
            return new Message("login failed");
        }
    }

    @RequestMapping(value = "/api/signup", method = RequestMethod.POST)
    public Message signUp(@RequestBody Auth auth,
                          HttpServletResponse response){

        try{
            byte[] dname = Base64.getDecoder().decode(auth.getEmail());
            byte[] dpass = Base64.getDecoder().decode(auth.getPassword());

            String name = new String(dname, "UTF-8");
            String pass = new String(dpass, "UTF-8");

            Account account = new Account();
            account.setEmail(name);
            account.setPassword(pass);
            accountRepository.save(account);

            return new Message("saved");
        }catch (Exception e){
            System.out.println(e);
            return new Message("not saved");
        }

    }


    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public Message logout(){
        return new Message("logged out");
    }
}
