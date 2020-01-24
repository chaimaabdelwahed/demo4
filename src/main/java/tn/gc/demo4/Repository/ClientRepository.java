package tn.gc.demo4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.gc.demo4.Model.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    List<Client> findAllByNameContains(String filter);
    @Query("SELECT client from Client client")
    List<Client> jibhom();
}
