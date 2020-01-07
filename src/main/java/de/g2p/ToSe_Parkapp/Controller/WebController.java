package de.g2p.ToSe_Parkapp.Controller;

import de.g2p.ToSe_Parkapp.Entities.Nutzer;
import de.g2p.ToSe_Parkapp.Repositories.NutzerRepository;
import de.g2p.ToSe_Parkapp.Service.MyUserDetailService;
import de.g2p.ToSe_Parkapp.Service.MyUserDetails;
import de.g2p.ToSe_Parkapp.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
public class WebController {

    @Autowired
    NutzerRepository nutzerRepository;

    //GetMapping for the homepage for IP-Adress (or localhost) only
    @GetMapping("/")
    public String homeGet() {
        return "home";
    }

    //GetMapping for the testing page
    @GetMapping("/testweiterleitung")
    public String testweiterleitung() {
        return "testweiterleitung";
    }

    //GetMapping for the error page
    @GetMapping("/errorpage")
    public String error() {
        return "error_page";
    }

    //GetMapping for the homepage
    @GetMapping("/home")
    public String homeAdmin() {
        Nutzer nutzer = findNutzer();
        String returnstring = "";
        if(nutzer.getAdmin().equalsIgnoreCase("admin"))

        if (nutzer.getAdmin() == true)
            returnstring = "home_admin";
        else
            returnstring = "home";
        return returnstring;
    }



    //GetMapping for the Login Page
    @GetMapping("/login")
    public String loginGet() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost() {
        //test if the given username and password are correct
        return "home";
    }

    @GetMapping("/passwort_zurueckgesetzt")
    public String passwortZurueck() {
        return "email_bestaetigt";
    }

    @GetMapping("/admin_alle_ausstehenden_reservierungen")
    public String adminRes() {
        return "admin_alle_ausstehenden_reservierungen";
    }

    @GetMapping("/admin_vergangene_transaktionen")
    public String adminTrans() {
        return "admin_vergangene_transaktionen";
    }

    //GetMapping for the Login Page
    @GetMapping("/login")
    public String loginGet() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(Model model, HttpSession session) {
        return "home";
    }

    //GetMapping for the logout page and redirect to the login form
    @GetMapping("/logout")
    public String logoutGet() {
        return "logout";
    }
    @PostMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "login?logout";
    }

    //GetMapping for the buttons on the home page if there is no mapping in other classes
    // ToDo Profil and Kontakt has to be added later when the html pages are done

    @GetMapping("/suche")
    public String suche() {
        return "parkplaetze";
    }

    @GetMapping("/aktueller_parkplatz")
    public String aktuellerParkplatz() {
        return "spezieller_parkplatz";
    }


    //GetMapping for getting an E-Mail to set a new password.
    @GetMapping("/passwordreset")
    public String resetPasswordGet(Model model) {
        model.addAttribute("nutzer", new Nutzer());
        return "passwort_zuruecksetzten";
    }

    //PostMapping for getting an E-Mail to set a new password.
    @PostMapping("/passwordreset")
    public String resetPasswordPost(@RequestParam("emailaddresse") String emailaddress) {

        /*ToDo try convert a String into a HTML hyperlink so that the User ust has to click on it in the mail instead of copy and pasting the url. Following is a example how to do this...
        String url = "stackoverflow.com/questions/ask";
        String someVariable = "testUrl";
        Html entryLink = new Html("<a target=\"_blank\" href=\"" + url + "\">" + someVariable + "</a>");*/

        MailService mailService = new MailService();
        String resetLink = "localhost:8080/newpassword";
        mailService.sendSimpleMessage(emailaddress, "Passwort zurücksetzten", "Bitte öffnen Sie auf folgenden Link, durch kopieren und einfügen im Browser, um Ihr Passwort zurückzusetzten: \n" + resetLink);

        return "email_bestaetigt";
    }

    public Nutzer findNutzer() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String benutzername = "";
        if(principal instanceof UserDetails)
            benutzername = ((UserDetails) principal).getUsername();
        else
            benutzername = principal.toString();

        Nutzer nutzer = nutzerRepository.findByBenutzernameNO(benutzername);
        return nutzer;
    }
}


    //GetMapping for setting the new password.
    @GetMapping("/newpassword")
    public String newPasswordGet(Model model) {

        model.addAttribute("nutzer", new Nutzer());

        return "neues_passwort";
    }

    //PostMapping for setting the new password.
    @PostMapping("/newpassword")
    public String newPasswordPost(@ModelAttribute Nutzer nutzer, @RequestParam("emailaddresse") String emailaddress, @RequestParam("newPassword") String password) {

        boolean check = nutzerRepository.findByEmailAdresse(emailaddress).isPresent();
        String returnString = "";
        if (check) {
            nutzerRepository.updatePasswort(emailaddress, password);
            returnString = "passwort_zurueckgesetzt";
        } else {
            returnString = "error_page";
        }
        return returnString;
    }


}

