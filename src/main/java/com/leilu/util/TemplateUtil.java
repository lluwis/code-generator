package com.leilu.util;

import com.leilu.util.config.GlobalConfig;
import com.leilu.util.config.OutputConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.util.List;
import java.util.Properties;

/**
 * Created by leilu on 2018/3/28.
 */
public class TemplateUtil {
    public static VelocityEngine engine;

    public static void init() {
        Properties p = new Properties();

        p.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        p.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        p.setProperty(Velocity.ENCODING_DEFAULT, "utf-8");
        p.setProperty(Velocity.INPUT_ENCODING, "utf-8");
        p.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
        p.setProperty("file.resource.loader.unicode", "true");
        engine = new VelocityEngine(p);
    }

    public static Template getTemplate(String templateName) throws Exception{
        return  engine.getTemplate(templateName,"utf-8");
    }

    public static List<OutputConfig> initOutput(GlobalConfig globalConfig) throws Exception{
        List<OutputConfig> list=globalConfig.getOutputConfigList();

        init();

        for(OutputConfig outputConfig:list){
            String fullPath=globalConfig.getProjectPath();
            if(StringUtils.isNotEmpty(outputConfig.getBathPath())){
                fullPath=fullPath+outputConfig.getBathPath();
            }
            if(StringUtils.isNotEmpty(outputConfig.getPackageName())){
                fullPath=fullPath+outputConfig.getPackageName().replace(".","\\")+"\\";
            }
            outputConfig.setFullPath(fullPath);
            outputConfig.setTemplate(getTemplate(outputConfig.getTemplateName()));

        }

        return list;
    }


}
