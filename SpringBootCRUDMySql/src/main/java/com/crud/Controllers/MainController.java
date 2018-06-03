package com.crud.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crud.Models.AppUsers;
import com.crud.Service.AppUsersRepo;

@Controller
public class MainController {
	@Autowired
	AppUsersRepo repo;
	
	@RequestMapping("/")
	public ModelAndView goHomePage(){
		ModelAndView mv=new ModelAndView("home");
		mv.addObject("lists",repo.findAll());
		return mv;
		
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView doSave(@RequestParam("firstname") String fname,@RequestParam("lastname") String lname){
		ModelAndView mv=new ModelAndView("redirect:/");
		AppUsers au=new AppUsers();
		au.setFirstname(fname);
		au.setLastname(lname);
		repo.save(au);
		return mv;
		
	}
	@RequestMapping(value="view/{id}",method=RequestMethod.GET)
	public ModelAndView doView(@PathVariable("id") int id){
		ModelAndView mv=new ModelAndView("view");
		
		AppUsers user=repo.findById(id).get();
		System.out.println(user.getFirstname());
		mv.addObject("lists",user);
		return mv;
		
	}
	@RequestMapping(value="delete/{id}",method=RequestMethod.GET)
	public ModelAndView doDelete(@PathVariable("id") int id){
		ModelAndView mv=new ModelAndView("redirect:/");
		repo.deleteById(id);
		return mv;
		
	}
	@RequestMapping(value="edit/{id}",method=RequestMethod.GET)
	public ModelAndView doEdit(@PathVariable("id") int id){
		ModelAndView mv=new ModelAndView("edit");
		AppUsers user=repo.findById(id).get();
		mv.addObject("lists",user);
		return mv;
		
	}
	
	@RequestMapping(value="update/{id}",method=RequestMethod.POST)
	public ModelAndView doUpdate(@PathVariable("id")int id,@RequestParam("firstname") String fname,@RequestParam("lastname") String lname){
		ModelAndView mv=new ModelAndView("redirect:/");
		AppUsers au=repo.findById(id).get();
		au.setFirstname(fname);
		au.setLastname(lname);
		repo.save(au);
		return mv;
		
	}

}
