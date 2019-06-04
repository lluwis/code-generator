package com.leilu.util;

import com.google.common.base.Joiner;
import com.leilu.util.config.DataSourceConfig;
import com.leilu.util.config.GlobalConfig;
import com.leilu.util.entity.ColumnEntity;
import com.leilu.util.entity.TableEntity;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.builder.CompareToBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by leilu on 2018/3/28.
 */
public class DbUtil {
    public static Connection conn;

    public static void open(DataSourceConfig dataSourceConfig) throws Exception {
        Class.forName(dataSourceConfig.getDriverName());
        conn = DriverManager.getConnection(dataSourceConfig.getUrl(), dataSourceConfig.getUserName(), dataSourceConfig.getPassword());

    }
    public static void close() throws Exception{
        if(conn!=null){
            conn.close();
        }
    }

    public static ResultSet executeQuery(String sql) throws Exception{
        return conn.prepareStatement(sql).executeQuery(sql);
    }

    public static List<TableEntity> getTableEntity(GlobalConfig globalConfig) throws Exception{
        List<String> tableNames=new ArrayList();
        DataSourceConfig dataSourceConfig=globalConfig.getDataSourceConfig();
        for(String tableName :dataSourceConfig.getTableMap().keySet()){
            tableNames.add("'"+tableName+"'");
        }
        String sql="select table_name tableName,table_comment tableComment from information_schema.TABLES where table_name in ( "+ Joiner.on(",").join(tableNames)+")";
        ResultSet resultSet=executeQuery(sql);
        List<TableEntity> tableList=new ArrayList<TableEntity>();
        while(resultSet.next()){
            TableEntity tableEntity=new TableEntity();
            String tableName=resultSet.getString("tableName");
            String tableComment=resultSet.getString("tableComment");
            String className=dataSourceConfig.getTableMap().get(tableName);
            tableEntity.setTableName(tableName);
            tableEntity.setComment(tableComment);
            tableEntity.setClassName(className);
            tableEntity.setUnCapitalClassName(WordUtils.uncapitalize(className));
            Map<String,Object> extendMap=globalConfig.getExtendConfig().getByTableName(tableName);
            addColumns(tableEntity,extendMap);
            tableList.add(tableEntity);
        }
        return tableList;
    }

    public static void addColumns(TableEntity tableEntity,Map<String,Object> extendMap) throws Exception{
        List<ColumnEntity> columnList=new ArrayList<ColumnEntity>();
        List<ColumnEntity> indexList=new ArrayList<ColumnEntity>();
        List<ColumnEntity> uniqueColumns=new ArrayList<ColumnEntity>();
        List<ColumnEntity> columnsWithoutPri=new ArrayList<ColumnEntity>();

        String sql = "select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey,is_nullable isNull from information_schema.columns where table_name = '"+tableEntity.getTableName()+"' ";
        ResultSet resultSet=executeQuery(sql);
        while(resultSet.next()){
            ColumnEntity columnEntity=new ColumnEntity();
            columnEntity.setColumnName(resultSet.getString("columnName"));
            columnEntity.setColumnType(resultSet.getString("dataType"));
            columnEntity.setComment(resultSet.getString("columnComment"));
            columnEntity.setNullable("NO".equals(resultSet.getString("isNull"))?false:true);
            columnEntity.setAttrName(columnEntity.getColumnName());
            columnEntity.setAttrType(processMySqlType(columnEntity.getColumnType()));
            columnEntity.setCapitalAttrName(WordUtils.capitalize(columnEntity.getAttrName()));
            columnList.add(columnEntity);
            String key=resultSet.getString("columnKey");

            //主键
            if("PRI".equals(key)){
                tableEntity.setPri(columnEntity);
            } else {
                columnsWithoutPri.add(columnEntity);
            }

            //唯一索引
            if(StringUtils.isNotEmpty(key)){
                if( "PRI".equals(key)||"UNI".equals(key)){
                    uniqueColumns.add(columnEntity);
                }
                if(!"PRI".equals(key)){
                    indexList.add(columnEntity);
                }

            }

            //设置扩展属性
            if(extendMap!=null){
                setExtend(columnEntity,extendMap);
            }

        }
        tableEntity.setColumns(columnList);
        tableEntity.setColumnsWithoutPri(columnsWithoutPri);
        tableEntity.setUniqueColumns(uniqueColumns);
        tableEntity.setIndexColumns(indexList);

        //根据列属性，增加引入的类
        addImports(tableEntity);

    }

    private static void setExtend(ColumnEntity columnEntity,Map<String,Object> map){
        //范围查询字段
        Object o=map.get("rangeColumns");
        if(o!=null){
            List<String> rangeColumns=(List<String>)o;
            if(rangeColumns.contains(columnEntity.getColumnName())){
                columnEntity.setRange(true);
            }
        }

        //选项字段
        o=map.get("selectColumns");
        if(o!=null){
            List<String> selectColumns=(List<String>)o;
            if(selectColumns.contains(columnEntity.getColumnName())){
                columnEntity.setSelect(true);
            }
        }
    }

    private static void addImports(TableEntity tableEntity){
        List<String> imports=new ArrayList<String>();
        int dateCount=0;
        int decimalCount=0;
        for(ColumnEntity columnEntity:tableEntity.getColumns()){
            if("Date".equals(columnEntity.getAttrType())){
                dateCount++;
            }

            if("BigDecimal".equals(columnEntity.getAttrType())){
                decimalCount++;
            }
        }
        if(dateCount>0){
            imports.add("java.util.Date");
        }
        if (decimalCount>0){
            imports.add("java.math.BigDecimal");
        }
        tableEntity.setImports(imports);
    }

    private static String processMySqlType(String type) {
        String t = type.toLowerCase();
        if (t.contains("char")) {
            return "String";
        } else if (t.contains("bigint")) {
            return "Long";
        } else if (t.contains("int")) {
            return "Integer";
        } else if (t.contains("date") || t.contains("timestamp")) {
            return "String";
        } else if (t.contains("text")) {
            return "String";
        } else if (t.contains("bit")) {
            return "Integer";
        } else if (t.contains("decimal")) {
            return "BigDecimal";
        } else if (t.contains("blob")) {
            return "byte[]";
        } else if (t.contains("float")) {
            return "Float";
        } else if (t.contains("double")) {
            return "Double";
        }
        return "String";
    }
}
