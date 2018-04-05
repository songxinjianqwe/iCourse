package com.sinjinsong.icourse.core.enumeration.conversion;

import com.sinjinsong.icourse.core.enumeration.institution.InstitutionStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
public class InstitutionStatusTypeHandler implements TypeHandler<InstitutionStatus> {

    @Override
    public void setParameter(PreparedStatement ps, int i, InstitutionStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public InstitutionStatus getResult(ResultSet rs, String columnName) throws SQLException {
        return InstitutionStatus.getByCode(rs.getInt(columnName));
    }

    @Override
    public InstitutionStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        return InstitutionStatus.getByCode(rs.getInt(columnIndex));
    }

    @Override
    public InstitutionStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return InstitutionStatus.getByCode(cs.getInt(columnIndex));
    }
}
