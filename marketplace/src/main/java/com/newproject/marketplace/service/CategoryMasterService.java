package com.newproject.marketplace.service;

import com.newproject.marketplace.model.CategoryMaster;
import com.newproject.marketplace.model.GetCategoryMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryMasterService {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ResponseEntity updatecategorymaster(CategoryMaster categoryMaster) {
        try {
            int count = jdbcTemplate.update("update category_master set category_name = ?, alias = ?, status = ?, updated_at = current_timestamp  " +
                    "where id = ? and outlet_id = ?", categoryMaster.getCategoryName(), categoryMaster.getAlias(), categoryMaster.getStatus(), categoryMaster
                    .getId(), categoryMaster.getOutletId());
            if (count == 0) {
                jdbcTemplate.update("insert into category_master ( id, category_name, alias, status, outlet_id, created_at, updated_at) values " +
                        "(?,?,?,?,?, current_timestamp, current_timestamp )", getid(categoryMaster.getOutletId()), categoryMaster.getCategoryName(), categoryMaster
                        .getAlias(), categoryMaster.getStatus(), categoryMaster.getOutletId());
                return ResponseEntity.ok("Category Master Created Successfully..!");
            }
            return ResponseEntity.ok("Category Master Updated Successfully..!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Unable to create category master");
        }
    }

    private long getid(Long outlet_id) {
        String query = "INSERT INTO category_master_sequence (category_master_id, outlet_id) VALUES (1,?) on conflict (outlet_id)" +
                "do update set category_master_id = category_master_sequence.category_master_id + 1";
        jdbcTemplate.update(query, outlet_id);
        return jdbcTemplate.queryForObject("select category_master_id from category_master_sequence where outlet_id = ?", Long.class, outlet_id);
    }

    public ResponseEntity getcategorymaster(GetCategoryMaster getCategoryMaster) {

        String whereQuery = "";

        Map<String, Object> newHashmap = new HashMap<>();
        newHashmap.put("id", getCategoryMaster.getId());
        newHashmap.put("outletId", getCategoryMaster.getOutletId());
        newHashmap.put("status", getCategoryMaster.getStatus());

        if (getCategoryMaster.getId() != 0L) {
            whereQuery = "id=:id";
        } else if (getCategoryMaster.getOutletId() != 0L) {
            whereQuery = whereQuery.equalsIgnoreCase("") ? "outletId=:outletId" : "and outletId=:outletId";
        } else if (getCategoryMaster.getStatus() != "") {

            whereQuery = whereQuery.equalsIgnoreCase("") ? "status =: status" : "and status =: status";
        }

        whereQuery = whereQuery.equalsIgnoreCase("") ? "" : " where " + whereQuery;

        SqlRowSet sqlRowSet = namedParameterJdbcTemplate.queryForRowSet("select * from category_master " + whereQuery, newHashmap);
        List<CategoryMaster> categoryMasterList = new ArrayList<>();
        while (sqlRowSet.next()) {
            CategoryMaster categoryMaster = new CategoryMaster();
            categoryMaster.setId(sqlRowSet.getLong("id"));
            categoryMaster.setCategoryName(sqlRowSet.getString("category_name"));
            categoryMaster.setAlias(sqlRowSet.getString("alias"));
            categoryMaster.setStatus(sqlRowSet.getString("status"));
            categoryMaster.setCreatedAt(sqlRowSet.getString("created_at"));
            categoryMaster.setUpdatedAt(sqlRowSet.getString("updated_at"));
            categoryMaster.setOutletId(sqlRowSet.getLong("outlet_id"));
            categoryMasterList.add(categoryMaster);
        }
        return ResponseEntity.ok(categoryMasterList);
    }
}


