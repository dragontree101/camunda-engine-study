package com.example.camunda.listener;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

//没配置就没效果
@Slf4j
@Component
public class CamundaExecutionListener implements ExecutionListener {

  @Override
  public void notify(DelegateExecution delegateExecution) throws Exception {
    log.info("CamundaExecutionListener notify delegateExecution is {}", delegateExecution);
  }
}
