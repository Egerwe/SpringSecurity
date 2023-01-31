package com.Yao.handler;

import com.Yao.utils.MD5;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author Yjw
 * 2023/2/1 0:03
 */
public class MD5TypeHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String s, JdbcType jdbcType) throws SQLException {
        if (s == null) {
            ps.setString(i, null);
            return;
        }
        ps.setString(i, MD5.encrypt(s));
    }

    @Override
    public String getNullableResult(ResultSet rs, String s) throws SQLException {
        return rs.getString(s);
    }

    @Override
    public String getNullableResult(ResultSet rs, int i) throws SQLException {
        return rs.getString(i);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int i) throws SQLException {
        return cs.getString(i);
    }
}
