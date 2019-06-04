package com.leilu.main;

import com.leilu.util.config.DataSourceConfig;
import com.leilu.util.config.ExtendConfig;
import com.leilu.util.config.GlobalConfig;
import com.leilu.util.config.OutputConfig;
import com.leilu.util.generator.Generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leilu on 2018/3/28.
 */
public class MainClass {
    public static void main(String[] args) {

        try{
            GlobalConfig globalConfig=buildGlobalConfig();
            Generator.generateCode(globalConfig);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static GlobalConfig buildGlobalConfig(){
        GlobalConfig globalConfig=new GlobalConfig();
        globalConfig.setProjectPath("D:\\Git\\fin-peerlending-console\\");
        globalConfig.setProjectPackage("com.leilu.peerlending.console");

        //配置数据源
        DataSourceConfig dataSourceConfig=new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://192.168.145.154:3306/peerlending");
        dataSourceConfig.setUserName("root");
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        Map<String,String> tableMap=new HashMap<String, String>();
        tableMap.put("trade_investplanorders","InvestPlanOrders");
        /*tableMap.put("trade_orders","Orders");
        tableMap.put("trade_loanorders","LoanOrders");
        tableMap.put("trade_repaymentorders","RepaymentOrders");
        tableMap.put("trade_withdraworders","WithdrawOrders");
        tableMap.put("trade_userbankaccount","UserBankAccount");
        tableMap.put("trade_orderdetails","OrderDetails");
        tableMap.put("bank_transaction","BankTransaction");*/
        dataSourceConfig.setTableMap(tableMap);
        globalConfig.setDataSourceConfig(dataSourceConfig);


        //数据模型补充配置
        ExtendConfig extendConfig=new ExtendConfig();

        Map<String,Object> map=new HashMap<String, Object>();
        extendConfig.putTableExtend("trade_investplanorders",map);
        //范围查询字段
        List<String> rangeColumns=new ArrayList<String>();
        rangeColumns.add("createdDate");
        map.put("rangeColumns",rangeColumns);
        //单选字段
        List<String> selectColumns=new ArrayList<String>();
        selectColumns.add("reFlag");
        selectColumns.add("status");
        selectColumns.add("periodType");
        map.put("selectColumns",selectColumns);

        map=new HashMap<String, Object>();
        extendConfig.putTableExtend("trade_userbankaccount",map);
        selectColumns=new ArrayList<String>();
        selectColumns.add("idCardType");
        selectColumns.add("bankType");
        selectColumns.add("role");
        selectColumns.add("status");
        selectColumns.add("sourceType");
        map.put("selectColumns",selectColumns);

        map=new HashMap<String, Object>();
        extendConfig.putTableExtend("trade_loanorders",map);
        selectColumns=new ArrayList<String>();
        selectColumns.add("status");
        selectColumns.add("loanPurposeType");
        map.put("selectColumns",selectColumns);

        map=new HashMap<String, Object>();
        extendConfig.putTableExtend("trade_orders",map);
        selectColumns=new ArrayList<String>();
        selectColumns.add("status");
        selectColumns.add("bizType");
        selectColumns.add("reFlag");
        map.put("selectColumns",selectColumns);

        map=new HashMap<String, Object>();
        extendConfig.putTableExtend("trade_repaymentorders",map);
        selectColumns=new ArrayList<String>();
        selectColumns.add("status");
        map.put("selectColumns",selectColumns);

        map=new HashMap<String, Object>();
        extendConfig.putTableExtend("trade_withdraworders",map);
        selectColumns=new ArrayList<String>();
        selectColumns.add("status");
        selectColumns.add("userType");
        map.put("selectColumns",selectColumns);

        map=new HashMap<String, Object>();
        extendConfig.putTableExtend("bank_transaction",map);
        selectColumns=new ArrayList<String>();
        selectColumns.add("bizType");
        selectColumns.add("transactionStatus");
        selectColumns.add("preStatus");
        map.put("selectColumns",selectColumns);

        globalConfig.setExtendConfig(extendConfig);

        //输出配制
        List<OutputConfig> outputConfigList=new ArrayList<OutputConfig>();
        OutputConfig outputConfig;

        outputConfig=new OutputConfig();
        outputConfig.setBathPath("peerlending-console-export\\src\\main\\java\\");
        outputConfig.setPackageName("com.leilu.peerlending.console.export.vo");
        outputConfig.setTemplateName("Vo.java.vm");
        outputConfig.setFileName(".java");
        outputConfigList.add(outputConfig);

        outputConfig=new OutputConfig();
        outputConfig.setBathPath("peerlending-console-dao\\src\\main\\java\\");
        outputConfig.setPackageName("com.leilu.peerlending.console.dao");
        outputConfig.setTemplateName("Mapper.java.vm");
        outputConfigList.add(outputConfig);

        outputConfig=new OutputConfig();
        outputConfig.setBathPath("peerlending-console-dao\\src\\main\\resources\\");
        outputConfig.setPackageName("com.leilu.peerlending.console.dao");
        outputConfig.setTemplateName("Mapper.xml.vm");
        outputConfigList.add(outputConfig);

        outputConfig=new OutputConfig();
        outputConfig.setBathPath("peerlending-console-controller\\src\\main\\java\\");
        outputConfig.setPackageName("com.leilu.peerlending.console.controller");
        outputConfig.setTemplateName("Controller.java.vm");
        outputConfigList.add(outputConfig);

        /*outputConfig=new OutputConfig();
        outputConfig.setBathPath("peerlending-console-web\\src\\main\\webapp\\WEB-INF\\page\\");
        outputConfig.setTemplateName("List.html.vm");
        outputConfig.setFileName(".html");
        outputConfig.setFileCapital(false);
        outputConfigList.add(outputConfig);*/

        outputConfig=new OutputConfig();
        outputConfig.setBathPath("peerlending-console-web\\src\\main\\webapp\\WEB-INF\\page\\");
        outputConfig.setTemplateName("Detail.html.vm");
        outputConfig.setFileCapital(false);
        outputConfigList.add(outputConfig);

        /*outputConfig=new OutputConfig();
        outputConfig.setBathPath("peerlending-console-web\\src\\main\\webapp\\js\\");
        outputConfig.setTemplateName("List.js.vm");
        outputConfig.setFileName(".js");
        outputConfig.setFileCapital(false);
        outputConfigList.add(outputConfig);

        outputConfig=new OutputConfig();
        outputConfig.setBathPath("peerlending-console-web\\src\\main\\webapp\\js\\");
        outputConfig.setTemplateName("Detail.js.vm");
        outputConfig.setFileCapital(false);
        outputConfigList.add(outputConfig);*/

        globalConfig.setOutputConfigList(outputConfigList);
        return globalConfig;
    }
}
