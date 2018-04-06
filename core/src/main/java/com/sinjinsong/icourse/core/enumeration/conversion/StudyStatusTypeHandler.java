package com.sinjinsong.icourse.core.enumeration.conversion;

import com.sinjinsong.icourse.core.enumeration.course.StudyStatus;
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
public class StudyStatusTypeHandler implements TypeHandler<StudyStatus> {

    @Override
    public void setParameter(PreparedStatement ps, int i, StudyStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public StudyStatus getResult(ResultSet rs, String columnName) throws SQLException {
        return StudyStatus.getByCode(rs.getInt(columnName));
    }

    @Override
    public StudyStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        return StudyStatus.getByCode(rs.getInt(columnIndex));
    }

    @Override
    public StudyStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return StudyStatus.getByCode(cs.getInt(columnIndex));
    }
}
