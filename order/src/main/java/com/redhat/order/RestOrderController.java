package com.redhat.order;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class RestOrderController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam("id") long id) {
        String url = "http://msa-test-git.nanshan.svc.cluster.local:8080/customers/" + id;
        RestTemplate rt = new RestTemplate();
        Person p = rt.getForObject(url, Person.class);
        return "World peace! - - " + p.getName();
    }

    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    public ResponseEntity quote(@RequestParam("uid") long uid,
            @RequestParam("pid") long pid) {
        String url = "http://msa-test-git.nanshan.svc.cluster.local:8080/customers/" + uid;
        RestTemplate rt = new RestTemplate();
        Person p = rt.getForObject(url, Person.class);

        url = "http://product.nanshan.svc.cluster.local:8080/products/" + pid;
        Product pt = rt.getForObject(url, Product.class);
        return ResponseEntity.ok("User name:" + p.getName() + " --- Product Description: " + pt.getContent());
    }
}
