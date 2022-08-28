package com.example.camunda.listener;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

//暂时没效果
@Slf4j
@ProcessApplication
public class InvoiceProcessApplication extends ServletProcessApplication {

  public TaskListener getTaskListener() {
    return new TaskListener() {
      public void notify(DelegateTask delegateTask) {
        // handle all Task Events from Invoice Process
        log.info("InvoiceProcessApplication TaskListener notify delegateTask is {}", delegateTask);
      }
    };
  }

  public ExecutionListener getExecutionListener() {
    return new ExecutionListener() {
      public void notify(DelegateExecution execution) throws Exception {
        // handle all Execution Events from Invoice Process
        log.info("InvoiceProcessApplication ExecutionListener notify delegateTask is {}", execution);
      }
    };
  }
}
