package ssm.domain;

import net.sf.json.JSONArray;

public class QueryVo {
    public JSONArray rows;
    public Long total;
    public Boolean result;
    public String success;

    public JSONArray getRows() {
        return rows;
    }



    public Boolean getResult() {
        return result;
    }

    public String getSuccess() {
        return success;
    }

    public void setRows(JSONArray rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
