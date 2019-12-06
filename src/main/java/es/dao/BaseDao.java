package es.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao {
    List<Map<String,Object>> search(String  sql);
    boolean execute(String sql);
}
