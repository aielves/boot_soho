package com.soho.shiro;

import com.soho.aliyun.ggk.interceptor.KillRobotInterceptor;
import com.soho.shiro.realm.WebLoginRealm;
import com.soho.spring.mvc.interceptor.FormTokenInterceptor;
import com.soho.spring.shiro.initialize.InitDefinition;
import com.soho.spring.shiro.initialize.RuleChain;
import com.soho.spring.shiro.initialize.WebInitializeService;
import com.soho.spring.utils.WCCUtils;
import freemarker.template.TemplateDirectiveModel;
import org.apache.shiro.realm.Realm;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.Filter;
import java.util.*;

/**
 * @author shadow
 */
@Component
public class WebInitializeServiceImp implements WebInitializeService {

    @Override
    public InitDefinition initShiroFilterChainDefinition() {
        InitDefinition definition = new InitDefinition();
        definition.setLoginUrl("/dog/login");
        definition.setSuccessUrl("/dog/findOne");
        definition.setUnauthorizedUrl("/403");
        List<RuleChain> anonRuleChains = new ArrayList<>();
        anonRuleChains.add(new RuleChain("/static/**", "anon"));
        anonRuleChains.add(new RuleChain("/ggk/**", "anon"));
        definition.setAnonRuleChains(anonRuleChains);
        List<RuleChain> roleRuleChains = new ArrayList<>();
        roleRuleChains.add(new RuleChain("/dog/findOne", "kickout,role[user]"));
        roleRuleChains.add(new RuleChain("/dog/findAll", "authc"));
        definition.setRoleRuleChains(WCCUtils.ruleChainComparator(roleRuleChains));
        return definition;
    }

    @Override
    public List<Realm> initShiroRealms() {
        List<Realm> realms = new ArrayList<>();
        realms.add(new WebLoginRealm());
        return realms;
    }

    @Override
    public Map<String, Filter> initShiroFilters() {
        return new LinkedHashMap<>();
    }

    @Override
    public boolean isHttpsCookieSecure() {
        return false;
    }

    @Override
    public List<HandlerInterceptor> initWebMVCInterceptor() {
        List<HandlerInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new KillRobotInterceptor());
        interceptors.add(new FormTokenInterceptor());
        return interceptors;
    }

    @Override
    public Map<String, TemplateDirectiveModel> initFreeMarkerTag() {
        return new HashMap<>();
    }

}