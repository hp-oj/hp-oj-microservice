package cn.pepedd.hpoj.judge.service.codesandbox;

import cn.pepedd.hpoj.entity.codesandbox.ExecuteCodeRequest;
import cn.pepedd.hpoj.entity.codesandbox.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
