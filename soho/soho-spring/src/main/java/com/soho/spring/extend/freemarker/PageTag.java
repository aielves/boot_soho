package com.soho.spring.extend.freemarker;

import com.soho.spring.model.RGX;
import com.soho.spring.utils.PageUtils;
import com.soho.spring.utils.RGXUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * FreeMarker自定义标签,分页导航
 *
 * @author shadow
 */
@Component
public class PageTag implements TemplateDirectiveModel {

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody directiveBody)
            throws TemplateException, IOException {
        Object pageFun = params.get("pageFun");
        Object pageNo = params.get("pageNo");
        Object pageNumber = params.get("pageNumber");
        if (pageFun == null || StringUtils.isEmpty(pageFun.toString())) {
            throw new TemplateException("参数[pageFun]非法", null);
        }
        if (!RGXUtils.matches(pageNo == null ? "" : pageNo.toString(), RGX.INTEGER)) {
            throw new TemplateException("参数[pageNo]非法", null);
        }
        if (!RGXUtils.matches(pageNumber == null ? "" : pageNumber.toString(), RGX.INTEGER)) {
            throw new TemplateException("参数[pageNumber]非法", null);
        }
        env.getOut().write(PageUtils.getHtml(pageFun.toString(), Integer.parseInt(pageNo.toString()), Integer.parseInt(pageNumber.toString())));
    }

}