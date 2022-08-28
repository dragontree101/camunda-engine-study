package com.example.camunda.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
public class TestDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    log.info("测试节点 - Test Task - 回调, businessKey is {}", execution.getBusinessKey());
//    throw new RuntimeException("测试运行时异常");
  }
}
