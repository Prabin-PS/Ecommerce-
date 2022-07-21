package com.newproject.marketplace.service;

import com.newproject.marketplace.model.GetItemMaster;
import com.newproject.marketplace.model.ItemMaster;
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
public class ItemMasterService {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ResponseEntity updateitemmaster(ItemMaster itemMaster){
        try{
            int count = jdbcTemplate.update("update item_master set item_name = ?, alias = ?, mrp = ?, sale_rate = ?,stock = ?, gst = ?, " +
                    "category = ?, tax_type = ?, status = ?, updated_at = current_timestamp, outlet_id = ? " +
                    "where id = ? and outlet_id = ?", itemMaster.getItemName(), itemMaster.getAlias(), itemMaster.getMrp(), itemMaster.getSaleRate(), itemMaster.getStock(),
                    itemMaster.getGst(), itemMaster.getCategory(), itemMaster.getTaxType(),
                    itemMaster.getStatus(), itemMaster.getId(),itemMaster.getOutletId(), itemMaster.getOutletId());
            if (count==0){
                jdbcTemplate.update("insert into item_master (item_name, alias, mrp, sale_rate, stock, gst, "  +
                        "category, tax_type, status, id,  outlet_id, created_at, updated_at) values " +
                        "(?,?,?,?,?,?,?,?,?,?,?,current_timestamp,current_timestamp )", itemMaster.getItemName(),itemMaster.getAlias(),
                        itemMaster.getMrp(), itemMaster.getSaleRate(), itemMaster.getStock(), itemMaster.getGst(), itemMaster.getCategory(),
                        itemMaster.getTaxType(), itemMaster.getStatus(), getId(itemMaster.getOutletId()),itemMaster.getOutletId()) ;
                return ResponseEntity.ok("Item Master created successfully");
            }
                return ResponseEntity.ok("Item Master updated successfully");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Unable create item master. Kindly check");
        }
    }

    private Long getId(Long outlet_id) {
        String query = "INSERT INTO item_master_sequence (outlet_id,item_master_id) values ( ?, 1) on conflict (outlet_id) " +
                "do update set item_master_id = item_master_sequence.item_master_id + 1";
        jdbcTemplate.update(query,outlet_id);
        return jdbcTemplate.queryForObject("select item_master_id from item_master_sequence where outlet_id = ?"  , Long.class,outlet_id);
    }

    public ResponseEntity getitemmaster(GetItemMaster getItemMaster) {

        String whereQuery = "";

        Map<String,Object> newHashmap = new HashMap<>();
        newHashmap.put("id", getItemMaster.getId());
        newHashmap.put("outletId", getItemMaster.getOutletId());
        newHashmap.put("status", getItemMaster.getStatus());

        if (getItemMaster.getId()!=0L) {
            whereQuery = "id=:id";
        }

        else if(getItemMaster.getOutletId()!=0L){
            whereQuery = whereQuery.equalsIgnoreCase("")? "outletId=:outletId" : "and outletId=:outletId";
        }

        else if(getItemMaster.getStatus()!=""){

            whereQuery = whereQuery.equalsIgnoreCase("")? "status =: status" : "and status =: status";
        }

        whereQuery = whereQuery.equalsIgnoreCase("")?"" : " where " + whereQuery;

        SqlRowSet sqlRowSet = namedParameterJdbcTemplate.queryForRowSet("select * from item_master "+whereQuery, newHashmap);
        List<ItemMaster> itemMasterList = new ArrayList<>();
        while(sqlRowSet.next()){
            ItemMaster itemMaster = new ItemMaster();
            itemMaster.setId(sqlRowSet.getLong("id"));
            itemMaster.setItemName(sqlRowSet.getString("item_name"));
            itemMaster.setAlias(sqlRowSet.getString("alias"));
            itemMaster.setMrp(sqlRowSet.getDouble("mrp"));
            itemMaster.setSaleRate(sqlRowSet.getDouble("sale_rate"));
            itemMaster.setStock(sqlRowSet.getDouble("stock"));
            itemMaster.setCategory(sqlRowSet.getString("category"));
            itemMaster.setGst(sqlRowSet.getDouble("gst"));
            itemMaster.setTaxType(sqlRowSet.getBoolean("tax_type"));
            itemMaster.setStatus(sqlRowSet.getString("status"));
            itemMaster.setOutletId(sqlRowSet.getLong("outlet_id"));
            itemMaster.setCreatedAt(sqlRowSet.getString("created_at"));
            itemMaster.setUpdatedAt(sqlRowSet.getString("updated_at"));
            itemMasterList.add(itemMaster);
        }
        return ResponseEntity.ok(itemMasterList);
    }
}
