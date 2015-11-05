package com.levi9.code9.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.levi9.code9.model.Question;
import com.levi9.code9.model.Test;
import com.levi9.code9.service.QuestionService;
import com.levi9.code9.service.TestService;
import com.levi9.code9.web.dto.TestDTO;

@Controller
@RequestMapping("/tests")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("tests")
	public List<TestDTO> getAll() {
		List<TestDTO> retVal = new ArrayList<>();
		List<Test> tests = testService.findAll();
		TestDTO dto = new TestDTO();
		for (Test test : tests) {
			dto.setId(test.getId());
			dto.setName(test.getName());
			dto.setCreateDate(test.getCreateDate());
			dto.setCreatedBy(test.getCreatedBy());
			retVal.add(dto);
		}
		return retVal;
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew(Model model) {
		TestDTO dto = new TestDTO();
		dto.setCreateDate(new Date());
		model.addAttribute("testDTO", dto);
		return "addEditTest";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEdit(@PathVariable Long id, Model model) {
		Test test = testService.findOne(id);
		TestDTO dto = new TestDTO();
		dto.setId(test.getId());
		dto.setName(test.getName());
		dto.setCreateDate(test.getCreateDate());
		dto.setCreatedBy(test.getCreatedBy());
		model.addAttribute("testDTO", dto);
		return "addEditTest";
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable Long id) {
		testService.remove(id);
		return "redirect:..";
	}
	
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public String save(@Valid TestDTO dto, BindingResult bindingResult, 
			Model model) {
		if (!bindingResult.hasErrors()) {
			Test test;
			if (dto.getId() != null) {
				test = testService.findOne(dto.getId());
			} else {
				test = new Test();
			}
			test.setName(dto.getName());
			test.setCreatedBy(dto.getCreatedBy());
			testService.save(test);
			return "redirect:tests";
		} else {
			model.addAttribute("testDTO", dto);
			return "addEditTest";
		}
	}
	
	@RequestMapping(params = "cancel", method = RequestMethod.POST)
	public String cancel() {
		return "redirect:tests";
	}
	
	@RequestMapping(value = "/editQuestions/{id}", method = RequestMethod.GET)
	public String getAddRemoveQuestions(@PathVariable Long id, Model model) {
		Test test = testService.findOne(id);
		List<Question> nonAssignedQuestions = questionService.findAll();
		nonAssignedQuestions.removeAll(test.getQuestions());
		List<Question> assignedQuestions = new ArrayList<>();
		assignedQuestions.addAll(test.getQuestions());
		model.addAttribute("test", id);
		model.addAttribute("assignedQuestions", assignedQuestions);
		model.addAttribute("nonAssignedQuestions", nonAssignedQuestions);
		return "addRemoveQuestions";
	}
	
	@RequestMapping(params = "addQuestion", method = RequestMethod.POST)
	public String addQuestion(@RequestParam Long testId, 
			@RequestParam(value = "nonAssignedQuestions", required = false, defaultValue = "-1") Long questionId, 
			Model model) {
		Question question = questionService.findOne(questionId);
		Test test = testService.findOne(testId);
		if (question != null) {
			test.addQuestion(question);
			testService.save(test);
		} else {
			model.addAttribute("addQuestionRequired", "test.addQuestion.required");
		}
		List<Question> nonAssignedQuestions = questionService.findAll();
		nonAssignedQuestions.removeAll(test.getQuestions());
		List<Question> assignedQuestions = new ArrayList<>();
		assignedQuestions.addAll(test.getQuestions());
		model.addAttribute("test", testId);
		model.addAttribute("assignedQuestions", assignedQuestions);
		model.addAttribute("nonAssignedQuestions", nonAssignedQuestions);
		return "addRemoveQuestions";
	}
	
	@RequestMapping(params = "removeQuestion", method = RequestMethod.POST)
	public String removeQuestion(@RequestParam Long testId, 
			@RequestParam(value = "assignedQuestions", required = false, defaultValue = "-1") Long questionId, 
			Model model) {
		Question question = questionService.findOne(questionId);
		Test test = testService.findOne(testId);
		if (question != null) {
			test.removeQuestion(question);
			testService.save(test);
		} else {
			model.addAttribute("removeQuestionRequired", "test.removeQuestion.required");
		}
		List<Question> nonAssignedQuestions = questionService.findAll();
		nonAssignedQuestions.removeAll(test.getQuestions());
		List<Question> assignedQuestions = new ArrayList<>();
		assignedQuestions.addAll(test.getQuestions());
		model.addAttribute("test", testId);
		model.addAttribute("assignedQuestions", assignedQuestions);
		model.addAttribute("nonAssignedQuestions", nonAssignedQuestions);
		return "addRemoveQuestions";
	}
}
