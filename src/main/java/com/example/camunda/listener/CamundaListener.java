package com.example.camunda.listener;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.spring.boot.starter.event.ExecutionEvent;
import org.camunda.bpm.spring.boot.starter.event.TaskEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CamundaListener {


  @EventListener
  public void onTaskEvent(DelegateTask taskDelegate) {
    log.info("Handling mutable DelegateTask:{}", taskDelegate.getTaskDefinitionKey());
  }

  @EventListener
  public void onTaskEvent(TaskEvent taskEvent) {
    log.info("Handling immutable TaskEvent:{}", taskEvent.getTaskDefinitionKey());
  }

  @EventListener
  public void onExecutionEvent(DelegateExecution executionDelegate) {
    log.info("Handling mutable DelegateExecution:{}", executionDelegate.getCurrentActivityName());
  }

  @EventListener
  public void onExecutionEvent(ExecutionEvent executionEvent) {
    log.info("Handling immutable ExecutionEvent:{}", executionEvent.getProcessDefinitionId());
  }

  @EventListener
  public void onHistoryEvent(HistoryEvent historyEvent) {
    log.info("Handling mutable HistoryEvent:{}", historyEvent.getEventType());
  }
}
