package org.bodywired.api.dao.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.bodywired.api.model.nutriment.AbstractNutriment.MIN_TYPE;

public class MineralTypeHandler extends BaseTypeHandler<MIN_TYPE> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			MIN_TYPE parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.ordinal() + 1);
	}

	@Override
	public MIN_TYPE getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		String ordinalBDD = rs.getString(columnName);
		try {
			return MIN_TYPE.values()[Integer.parseInt(ordinalBDD) - 1];
		} catch (NumberFormatException nfe) {
			System.out.println(nfe);
		}
		return null;
	}

	@Override
	public MIN_TYPE getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		String ordinalBDD = rs.getString(columnIndex);
		try {
			return MIN_TYPE.values()[Integer.parseInt(ordinalBDD) - 1];
		} catch (NumberFormatException nfe) {
			System.out.println(nfe);
		}
		return null;
	}

	@Override
	public MIN_TYPE getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		String ordinalBDD = cs.getString(columnIndex);
		try {
			return MIN_TYPE.values()[Integer.parseInt(ordinalBDD) - 1];
		} catch (NumberFormatException nfe) {
			System.out.println(nfe);
		}
		return null;
	}

}
