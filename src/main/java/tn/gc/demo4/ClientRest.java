package tn.gc.demo4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientRest {

    @Autowired
    ClientRepository clientRepository;


    @GetMapping(value = "/clients")
    public List<Client> getClients(@RequestParam(value = "nom",required = false) String nom){
        if(nom!=null)
        return clientRepository.findAllByNameContains(nom);
        else return clientRepository.jibhom();
    }
    @GetMapping(value = "/create-client/{name}")
    public void createClient(@PathVariable(value = "name") String name){
        Client client=new Client(name);

        clientRepository.save(client);
    }
}
