package com.example.camunda.controller;

import javax.annotation.Resource;
import org.camunda.bpm.engine.RepositoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deploy")
public class DeployController {

  @Resource
  private RepositoryService repositoryService;

  @GetMapping(value = "/test")
  public String deployTest() {
    repositoryService.createDeployment().name("测试流程").addClasspathResource("bpmn/test.bpmn").deploy();
    return "deploy test ok";
  }
}
