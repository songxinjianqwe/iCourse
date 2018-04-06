package com.sinjinsong.icourse.core.enumeration.conversion;

import com.sinjinsong.icourse.core.enumeration.order.PayType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
public class PayTypeTypeHandler implements TypeHandler<PayType> {
    
    @Override
    public void setParameter(PreparedStatement ps, int i, PayType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public PayType getResult(ResultSet rs, String columnName) throws SQLException {
        return PayType.getByCode(rs.getInt(columnName));
    }

    @Override
    public PayType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return PayType.getByCode(rs.getInt(columnIndex));
    }
    
    @Override
    public PayType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return PayType.getByCode(cs.getInt(columnIndex));
    }
}