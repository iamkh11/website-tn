package com.front.website.controllers;

import com.front.website.models.Match;
import com.front.website.models.News;
import com.front.website.models.Player;
import com.front.website.models.Staff;
import com.front.website.repos.MatchRepository;
import com.front.website.repos.NewsRepository;
import com.front.website.repos.PlayerRepository;
import com.front.website.repos.StaffRepository;
import com.front.website.repos.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


// indication de 'Controller'
@Controller
public class DisplayController {
    

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    StaffRepository staffRepository ; 

   
    @RequestMapping(value={"/tunisiaclubfootball","/"})
    public String getIndex(Model model){
    
        model.addAttribute("news", newsRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
    // return view 
    return "tunisiaclubfootball" ;
                    
    }
    
    @RequestMapping(path="/contact")
    public String getContact(Model model){
    
    
    // return view 
    return "contact" ;
                    
    }

    // Afficher les matches
    
    @RequestMapping(path="/calendrier")
    public String getAllMatches(ModelMap modelMap,
        @RequestParam (name="page",defaultValue = "0") int page,
        @RequestParam (name="size", defaultValue = "7") int size)
    {

        Page<Match> match = matchRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));

        modelMap.addAttribute("match", match);
        modelMap.addAttribute("pages", new int[match.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "calendrier";
        }

    @RequestMapping("/joueurs")
    public String getAllPlayers(ModelMap modelMap,
            @RequestParam (name="page",defaultValue = "0") int page,
            @RequestParam (name="size", defaultValue = "6") int size)
    {
    
    Page<Player> players = playerRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
    
    modelMap.addAttribute("players", players);
    modelMap.addAttribute("pages", new int[players.getTotalPages()]);
    modelMap.addAttribute("currentPage", page);
    modelMap.addAttribute("size", size);
    return "joueurs";
    }
    
    // return la liste des staff by 6 pour chaque page ordre DESC by id
    @RequestMapping("/staff")
    public String getAllStaff(ModelMap modelMap,
            @RequestParam (name="page",defaultValue = "0") int page,
            @RequestParam (name="size", defaultValue = "6") int size)
    {
    
    Page<Staff> staff = staffRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
    
    modelMap.addAttribute("staff", staff);
    modelMap.addAttribute("pages", new int[staff.getTotalPages()]);
    modelMap.addAttribute("currentPage", page);
    modelMap.addAttribute("size", size);
    return "staff";
    }


   /* @RequestMapping("/actu")
    public String getAllNews(Model model) {
        model.addAttribute("news", newsRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
        
        
        return "actu";
    }*/

   
    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable String id, Model model) {
        
        model.addAttribute("news", newsRepository.findById(id).get());
       
        
        return "article";
        
    }

    @RequestMapping("/actu")
	public String getAllNews(ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "6") int size)
	{
	
	Page<News> news = newsRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
    
	modelMap.addAttribute("news", news);
	modelMap.addAttribute("pages", new int[news.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	modelMap.addAttribute("size", size);
	return "actu";
	}




}
