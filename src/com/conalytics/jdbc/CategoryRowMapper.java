package com.conalytics.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.conalytics.domain.Category;

public class CategoryRowMapper implements RowMapper<Category> {

	@Override
	public Category mapRow(ResultSet resultSet, int line) throws SQLException {
		CategoryExtractor catExtractor = new CategoryExtractor();
		return catExtractor.extractData(resultSet);
	}

}
