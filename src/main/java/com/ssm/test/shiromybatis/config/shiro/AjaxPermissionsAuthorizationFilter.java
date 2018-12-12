package com.ssm.test.shiromybatis.config.shiro;

import com.ssm.test.shiromybatis.pojo.ReturnData;
import com.ssm.test.shiromybatis.util.ErrorEnum;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author ASUS
 * @date 2018/12/3 9:52
 * 自定义Filter
 * 对没有登录的请求进行拦截，全部返回json信息，覆盖掉Shiro原本的跳转login.jsp的拦截方式
 */
public class AjaxPermissionsAuthorizationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        ReturnData data = new ReturnData();
        data.setCode(ErrorEnum.E_20011.getErrorCode());
        data.setMsg(ErrorEnum.E_20011.getErrorMsg());
        // returnMap.put("returnMsg",ErrorEnum.E_20011.getErrorMsg());
        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.println(data);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
        return false;
    }
    /**
     *
     * 禁止spring自动注册filter,而是使用自定义Filter
     * @param filter
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(AjaxPermissionsAuthorizationFilter filter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }
}
