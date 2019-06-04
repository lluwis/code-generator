package com.leilu.util.generator;

import com.leilu.util.DbUtil;
import com.leilu.util.TemplateUtil;
import com.leilu.util.config.DataSourceConfig;
import com.leilu.util.config.GlobalConfig;
import com.leilu.util.config.OutputConfig;
import com.leilu.util.entity.TableEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by leilu on 2018/3/28.
 */
public class Generator {
    public static void generateCode(GlobalConfig globalConfig) throws Exception {

        //获取数据库模型信息
        DataSourceConfig dataSourceConfig = globalConfig.getDataSourceConfig();
        DbUtil.open(dataSourceConfig);
        List<TableEntity> tableList = DbUtil.getTableEntity(globalConfig);

        //初始化模板和输出配置
        List<OutputConfig> outputConfigList = TemplateUtil.initOutput(globalConfig);

        //组合模型和模板
        for (TableEntity tableEntity : tableList) {
            for (OutputConfig outputConfig : outputConfigList) {
                outputFile(tableEntity, outputConfig, globalConfig);
            }
        }

        DbUtil.close();
    }

    public static void outputFile(TableEntity tableEntity, OutputConfig outputConfig, GlobalConfig globalConfig) throws Exception {

        //组装文件输出目录
        String className=outputConfig.isFileCapital()?tableEntity.getClassName():tableEntity.getUnCapitalClassName();
        String fileName;
        if(StringUtils.isNotEmpty(outputConfig.getFileName())){
            fileName=className+outputConfig.getFileName();
        } else {
            fileName=className+ outputConfig.getTemplateName().replace(".vm", "");
        }
        String outputFile = outputConfig.getFullPath() +fileName ;
        File f = new File(outputFile);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }

        //组装vm参数
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("table", tableEntity);
        velocityContext.put("packageName",outputConfig.getPackageName());
        velocityContext.put("projectPackage",globalConfig.getProjectPackage());
        //获取扩展配置信息
        Map<String,Object> extendMap= globalConfig.getExtendConfig().getByTableName(tableEntity.getTableName());
        if(extendMap!=null){
            for(String key:extendMap.keySet()){
                velocityContext.put(key,extendMap.get(key));
            }
        }

        //组合模板和参数，输出文件
        FileOutputStream fos = new FileOutputStream(f);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"));
        Template template = outputConfig.getTemplate();
        template.merge(velocityContext, bufferedWriter);
        bufferedWriter.close();
        System.out.println("文件【"+outputFile+"】生成成功");

    }


}
