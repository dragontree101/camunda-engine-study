package com.example.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class TestDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    System.out.println("测试节点 - Test Task - 回调");
  }
}
