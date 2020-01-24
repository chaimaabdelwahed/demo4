package tn.gc.demo4.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.gc.demo4.Model.Client;
import tn.gc.demo4.Repository.ClientRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClientRest {

    @Autowired
    ClientRepository clientRepository;


    @GetMapping(value = "/clients")
    public List<Client> getClients(@RequestParam(value = "nom",required = false) String nom){
        if(nom!=null)
        return clientRepository.findAllByNameContains(nom);
        else return clientRepository.jibhom();
    }


    @PostMapping(value = "/create-client/{name}")
    public void createClient(@PathVariable(value = "name") String name){
        Client client=new Client(name);

        clientRepository.save(client);
    }
    @DeleteMapping(value="/delete/{id}")
    public void deleteClient(@RequestParam(value="id") Long id){

        clientRepository.deleteById(id);

    }
    @PutMapping(value="/update/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long id, @Valid @RequestBody Client client1){

           Optional<Client> client= clientRepository.findById(id);

if(client.isPresent()) {
    client.get().setId(client1.getId());
    client.get().setName(client1.getName());
    client.get().setVille(client1.getVille());

    Client updatedClient = clientRepository.save(client.get());
    return ResponseEntity.ok(updatedClient);
}else return ResponseEntity.notFound().build();

    }


}

