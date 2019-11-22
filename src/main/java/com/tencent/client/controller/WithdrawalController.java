package com.tencent.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tencent.client.service.WithdrawalService;
import com.tencent.client.util.ResponseVo;

@RestController
@RequestMapping("withdrawal")
public class WithdrawalController {
      
  @Autowired
  WithdrawalService withdrawalService;
  
   /**
    * 微法院调撤
    * @return
    */
  @RequestMapping("withdrawalCaseCount")
  public ResponseVo withdrawalCaseCount() {
    
    return withdrawalService.withdrawalCaseCount();
    
  }
  /**
   * 执行节点
   * @return
   */
  @RequestMapping("executionNode")
  public ResponseVo executionNode() {
    return withdrawalService.executionNode();
  }
  
  /**
   * 当事人分布
   * @return
   */
  @RequestMapping("distributionPartie")
  public ResponseVo distributionPartie() {
    return withdrawalService.distributionPartie();
  }


}
