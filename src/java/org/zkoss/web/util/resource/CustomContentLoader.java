package org.zkoss.web.util.resource;

import groovy.lang.Writable;
import groovy.text.Template;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import javax.servlet.ServletContext;
import org.codehaus.groovy.grails.commons.ConfigurationHolder;
import org.codehaus.groovy.grails.web.pages.GroovyPagesTemplateEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.util.resource.Locator;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.metainfo.PageDefinition;
import org.zkoss.zk.ui.metainfo.PageDefinitions;
import org.zkoss.zk.ui.metainfo.Parser;

public class CustomContentLoader extends ResourceLoader {

    private static final String UTF_8_ENCODING = "UTF-8";
    private static final String CONFIG_OPTION_GSP_ENCODING = "grails.views.gsp.encoding";
    private static final String CONFIG_ZKGRAILS_TAGLIB_DISABLE = "grails.zk.taglib.disabled";

    private final WebApp _wapp;
    private final ApplicationContext _ctx;

    public CustomContentLoader(WebApp wapp) {
        _wapp = wapp;
        _ctx  = WebApplicationContextUtils.getRequiredWebApplicationContext(
            (ServletContext)wapp.getNativeContext()
        );
    }

    private Template createTemplate(GroovyPagesTemplateEngine gsp, ByteArrayResource ba) throws Exception {
        // find "createTemplate"
        Class<?> c = GroovyPagesTemplateEngine.class;
        Method m = null;

        //
        // Try Grails 1.3.x first
        //
        try {
            m = c.getMethod("createTemplate", org.springframework.core.io.Resource.class, boolean.class);
        if(m != null)
            return (Template)m.invoke(gsp, ba, false);
        } catch(Throwable e){}

        //
        // Grails 1.2.x
        //
        m = c.getMethod("createTemplate", org.springframework.core.io.Resource.class);
        return (Template)m.invoke(gsp, ba);
    }

    //-- super --//
    protected Object parse(String path, File file, Object extra)
    throws Exception {
        Boolean disable = (Boolean)ConfigurationHolder.getFlatConfig().get(CONFIG_ZKGRAILS_TAGLIB_DISABLE);
        if(disable != null) {
            if(disable) {
                final Locator locator = extra != null ? (Locator)extra: PageDefinitions.getLocator(_wapp, path);
                return new Parser(_wapp, locator).parse(file, path);
            }
        }

        Locator locator = null;
        if(extra !=null)
            locator = (Locator)extra;
        else
            locator = PageDefinitions.getLocator(_wapp, path);

        GroovyPagesTemplateEngine gsp = (GroovyPagesTemplateEngine)_ctx.getBean("groovyPagesTemplateEngine");

        byte[] buffer = new byte[(int)file.length()];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        bis.read(buffer);

        String encoding = (String)ConfigurationHolder.getFlatConfig().get(CONFIG_OPTION_GSP_ENCODING);
        if(encoding == null) encoding = UTF_8_ENCODING;

        String bufferStr = new String(buffer, encoding);
        bufferStr = bufferStr.replaceAll("@\\{", "\\$\\{'@'\\}\\{");

        // Issue 161
        Template template = createTemplate(gsp, new ByteArrayResource(bufferStr.getBytes(encoding)));

        //
        // Issue 113 is between here
        // Do not need to do anything, just correct encoding in Config.groovy
        //
        Writable w = template.make();
        StringWriter sw = new StringWriter();
        w.writeTo(new PrintWriter(sw));

        // checked
        String zulSrc = sw.toString().replaceAll("\\#\\{","\\$\\{");

        // checked
        StringReader reader = new StringReader(zulSrc);
        PageDefinition pgdef = new Parser(_wapp, locator).parse(reader, null);
        pgdef.setRequestPath(path);
        return pgdef;
    }

    protected Object parse(String path, URL url, Object extra) throws Exception {
        final Locator locator =
            extra != null ? (Locator)extra: PageDefinitions.getLocator(_wapp, path);
        return new Parser(_wapp, locator).parse(url, path);
    }

}
