package de.g2p.ToSe_Parkapp.Controller;

import de.g2p.ToSe_Parkapp.Entities.Anbieter;
import de.g2p.ToSe_Parkapp.Repositories.AnbieterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/anbieter")
public class AnbieterController {

    @Autowired
    AnbieterRepository anbieterRepository;

    @GetMapping("/getall")
    public List<Anbieter> findAll(){
        return anbieterRepository.findAll();
    }

    public String insertOne(){
        //anbieterRepository.save(new Anbieter());
        return "redirect:/anbieter/getall";
    }
}
