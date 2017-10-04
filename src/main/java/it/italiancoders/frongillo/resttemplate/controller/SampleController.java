package it.italiancoders.frongillo.resttemplate.controller;

import it.italiancoders.frongillo.resttemplate.model.HelloMessage;
import it.italiancoders.frongillo.resttemplate.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    @RequestMapping(value="/public/api/hello", method= RequestMethod.GET)
    public ResponseEntity<?> publicSimpleGetHelloWorld(){
        return ResponseEntity.ok(new HelloMessage("Ciao Anonimo"));
    }

    @RequestMapping(value="/public/api/helloPath", method= RequestMethod.GET)
    public ResponseEntity<?> publicGetWithParamHelloWorld(@RequestParam String name){
        return ResponseEntity.ok(new HelloMessage("Ciao "+name));
    }

    @RequestMapping(value="/public/api/hello", method= RequestMethod.POST)
    public ResponseEntity<?> publicPostHelloWorld(@RequestBody Person person){
        return ResponseEntity.ok(new HelloMessage("Ciao "+
                                     person.getName()+" "+
                                     person.getSurname()));
    }

    @RequestMapping(value="/public/api/hello-exception", method= RequestMethod.GET)
    public ResponseEntity<?> publicHelloWorldWithException(){
        throw new RuntimeException("Errore hello world");
    }

}
