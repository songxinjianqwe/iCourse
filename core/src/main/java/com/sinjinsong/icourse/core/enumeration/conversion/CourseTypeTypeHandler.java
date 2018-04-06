package com.sinjinsong.icourse.core.enumeration.conversion;

import com.sinjinsong.icourse.core.enumeration.course.CourseType;
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
public class CourseTypeTypeHandler implements TypeHandler<CourseType> {

    @Override
    public void setParameter(PreparedStatement ps, int i, CourseType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public CourseType getResult(ResultSet rs, String columnName) throws SQLException {
        return CourseType.getByCode(rs.getInt(columnName));
    }

    @Override
    public CourseType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return CourseType.getByCode(rs.getInt(columnIndex));
    }

    @Override
    public CourseType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return CourseType.getByCode(cs.getInt(columnIndex));
    }
}
