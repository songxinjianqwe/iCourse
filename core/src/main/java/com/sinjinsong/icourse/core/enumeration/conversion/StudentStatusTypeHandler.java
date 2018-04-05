package com.sinjinsong.icourse.core.enumeration.conversion;


import com.sinjinsong.icourse.core.enumeration.student.StudentStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SinjinSong on 2017/4/28.
 */
public class StudentStatusTypeHandler implements TypeHandler<StudentStatus> {

    @Override
    public void setParameter(PreparedStatement ps, int i, StudentStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public StudentStatus getResult(ResultSet rs, String columnName) throws SQLException {
        return StudentStatus.getByCode(rs.getInt(columnName));
    }

    @Override
    public StudentStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        return StudentStatus.getByCode(rs.getInt(columnIndex));
    }

    @Override
    public StudentStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return StudentStatus.getByCode(cs.getInt(columnIndex));
    }
}
