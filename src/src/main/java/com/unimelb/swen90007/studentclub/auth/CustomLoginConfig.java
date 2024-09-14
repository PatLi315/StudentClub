package com.unimelb.swen90007.studentclub.auth;

import javax.security.auth.login.Configuration;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag;
import java.util.HashMap;
import java.util.Map;

public class CustomLoginConfig extends Configuration {
    @Override
    public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
        // 为你的LoginModule设置所需的参数
        Map<String, String> options = new HashMap<>();
        options.put("debug", "true");  // 设置为调试模式，方便检查问题
        options.put("useTicketCache", "false"); // 配置项
        // 添加其他需要的配置

        // 配置 LoginModule，第二个参数 LoginModuleControlFlag 表示控制模式
        return new AppConfigurationEntry[] {
                new AppConfigurationEntry(
                        "com.unimelb.swen90007.studentclub.auth.StudentLoginModule", // 自定义的 LoginModule
                        LoginModuleControlFlag.REQUIRED, // REQUIRED表示必须通过该模块的认证
                        options // 传递配置项
                )
        };
    }

    // 静态方法，在应用启动时调用
    public static void setCustomConfiguration() {
        Configuration.setConfiguration(new CustomLoginConfig());
    }
}
