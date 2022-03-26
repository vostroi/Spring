package com.vostroi.util.mybatis;

import com.vostroi.util.EnumConstant;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Administrator
 * @date 2021/5/15 17:43
 * @projectName scloud2021
 * @title: EnumRecordStateHandler
 * @description: mybatis RECORD_STATE 枚举类型转换器
 */
public class EnumRecordStateHandler extends BaseTypeHandler<EnumConstant.RECORD_STATE> {
    /**
     * 设置参数时，定义如何将java类型转为数据库类型
     * @param preparedStatement
     * @param i 字段顺序
     * @param record_state
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, EnumConstant.RECORD_STATE record_state, JdbcType jdbcType) throws SQLException {
        // 根据数据库表字段类型而定
        preparedStatement.setInt( i , record_state.getValue());
    }

    /**
     * 获取字段时，定义如何将数据库类型转为java类型
     * @param resultSet
     * @param s  字段名
     * @return
     * @throws SQLException
     */
    @Override
    public EnumConstant.RECORD_STATE getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if(resultSet.wasNull()){
            return null;
        }else{
            int v = resultSet.getInt(s);
            return EnumConstant.RECORD_STATE.locateEnum(v);
        }
    }

    /**
     * 定义 通过表字段索引获取数据时，如何将数据库类型转为java类型
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public EnumConstant.RECORD_STATE getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if(resultSet.wasNull()){
            return null;
        }else{
            int v = resultSet.getInt(i);
            return EnumConstant.RECORD_STATE.locateEnum(v);
        }
    }

    /**
     * 定义调用存储过程后，如何把数据库类型转为java类型
     * @param callableStatement
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public EnumConstant.RECORD_STATE getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if(callableStatement.wasNull()){
            return null;
        }else{
            int v = callableStatement.getInt(i);
            return EnumConstant.RECORD_STATE.locateEnum(v);
        }
    }
}
