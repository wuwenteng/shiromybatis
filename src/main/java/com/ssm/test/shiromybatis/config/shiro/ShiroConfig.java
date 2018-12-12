package com.ssm.test.shiromybatis.config.shiro;

import net.sf.ehcache.CacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ASUS
 * @date 2018/12/2 17:06
 */
@Configuration
public class ShiroConfig {

    /**
     * 配置Shiro过滤器
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) throws IOException {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置未登录时的返回信息
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc",new AjaxPermissionsAuthorizationFilter());
        //filterMap.put("kickout",kickoutSessionControllerFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        // 要拦截的url
        Map<String,String> filterChainDefinitonMap = new LinkedHashMap<>();
        // 从上到下顺序执行, authc检查登录，anon不做检查
        filterChainDefinitonMap.put("/","anon");
        filterChainDefinitonMap.put("/static/**","anon");
        filterChainDefinitonMap.put("/login","anon");
        filterChainDefinitonMap.put("/login/logout","anon");
        filterChainDefinitonMap.put("/error","anon");
        //filterChainDefinitonMap.put("/getoneuser/*","anon");
        filterChainDefinitonMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitonMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() throws IOException {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
//        securityManager.setCacheManager(ehCacheManager());
//        securityManager.setSessionManager(configWebSessionManager());
//        securityManager.setRememberMeManager(rememberMeManager());

        return securityManager;
    }

    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher());
        return userRealm;
    }

    /**
     * 凭证匹配器
     */

    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        return matcher;
    }

    /**
     * shiro生命周期
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启shiro注解
     * 使用下面的注解从而避免使用过AOP？？还不太清楚，实验一下看看
     */
    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
     public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() throws IOException {
         AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
         authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
         return authorizationAttributeSourceAdvisor;
     }

}
