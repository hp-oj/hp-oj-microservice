package cn.pepedd.hpoj.judge.service.codesandbox.impl;

import cn.pepedd.hpoj.judge.service.codesandbox.CodeSandbox;
import cn.pepedd.hpoj.entity.codesandbox.ExecuteCodeRequest;
import cn.pepedd.hpoj.entity.codesandbox.ExecuteCodeResponse;

/**
 * 第三方代码沙箱（调用网上现成的代码沙箱）
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
