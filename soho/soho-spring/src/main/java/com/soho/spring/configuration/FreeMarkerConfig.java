package com.soho.spring.configuration;

import com.soho.spring.extend.HtmlTemplateLoader;
import com.soho.spring.extend.freemarker.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author shadow
 */
@Configuration
public class FreeMarkerConfig {

    @Autowired(required = false)
    private freemarker.template.Configuration configuration;
    @Autowired(required = false)
    private HtmlTag htmlTag;
    @Autowired(required = false)
    private GTM8Tag gtm8Tag;
    @Autowired(required = false)
    private HasRoleTag hasRoleTag;
    @Autowired(required = false)
    private UserTag userTag;
    @Autowired(required = false)
    private OSSDomainTag ossDomainTag;
    @Autowired(required = false)
    private TokenTag tokenTag;
    @Autowired(required = false)
    private PageTag pageTag;


    @PostConstruct
    public void setSharedVariable() {
        configuration.setTemplateLoader(new HtmlTemplateLoader(configuration.getTemplateLoader()));
        configuration.setSharedVariable("html", htmlTag);
        configuration.setSharedVariable("gtm8", gtm8Tag);
        configuration.setSharedVariable("hasRole", hasRoleTag);
        configuration.setSharedVariable("usr", userTag);
        configuration.setSharedVariable("formToken", tokenTag);
        configuration.setSharedVariable("OSSDomain", ossDomainTag);
        configuration.setSharedVariable("page", pageTag);
    }

}