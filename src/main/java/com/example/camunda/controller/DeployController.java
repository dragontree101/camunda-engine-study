package com.example.camunda.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinitionQuery;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/deploy")
public class DeployController {

  @Resource
  private RepositoryService repositoryService;

  @Resource
  private RuntimeService runtimeService;

  @Resource
  private TaskService taskService;

  @Resource
  private HistoryService historyService;

  @GetMapping(value = "/create")
  public String deployCreate() {
    Deployment deployment = repositoryService.createDeployment().name("最新测试流程").addClasspathResource("bpmn/test.bpmn").deploy();
    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
        .deploymentId(deployment.getId()).singleResult();
    ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), "test-business-key-lastest");
    log.info("processInstance businessKey is  {}", processInstance.getBusinessKey());
    return "deploy test ok";
  }

  @GetMapping(value = "/query/by/name")
  public String deployQuery() {
    List<Deployment> deploymentList = repositoryService.createDeploymentQuery().deploymentName("测试流程").list();
    String deploymentListStr = deploymentList.stream().map(Deployment::toString).collect(Collectors.joining(","));
    log.info("deployment list {}", deploymentListStr);
    return deploymentListStr;
  }

  @GetMapping(value = "/process/definition")
  public String processDefinition() {
    ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery()
        .deploymentId("7b9bea0f-26cd-11ed-92c7-e6bdfeb31245");
    List<ProcessDefinition> processDefinitionList = processDefinitionQuery.list();
    String processDefinitionListStr = processDefinitionList.stream().map(ProcessDefinition::toString)
        .collect(Collectors.joining(","));
    log.info("processDefinition list {}", processDefinitionListStr);
    return processDefinitionListStr;
  }

  @GetMapping(value = "/process/start")
  public String processStart() {
    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
        .deploymentId("7b9bea0f-26cd-11ed-92c7-e6bdfeb31245").singleResult();
    ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), "test-business-key-2");
    log.info("processInstance businessKey is  {}", processInstance.getBusinessKey());
    return processInstance.toString();
  }

  @GetMapping(value = "/process/history/query")
  public String processHistoryQuery() {
    List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery().finished().list();
    log.info("historicTaskInstanceList size  is  {}", historicTaskInstanceList.size());

    List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery().finished().list();
    log.info("historicActivityInstanceList size  is  {}", historicActivityInstanceList.size());

    return "" + historicTaskInstanceList.size();
  }

  @GetMapping(value = "/process/task/query")
  public String processTaskQuery() {
    List<Task> taskList = taskService.createTaskQuery().list();
    log.info("taskList size  is  {}", taskList.size());
    return "" + taskList.size();
  }
}
