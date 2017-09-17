package com.hyperiongray.sitehound.backend.model;

/**
 * Created by tomas on 5/09/17.
 */
public class DdLoginResult {

    private String id;
    private String result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DdLoginResult that = (DdLoginResult) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return result != null ? result.equals(that.result) : that.result == null;
    }

    @Override
    public int hashCode() {
        int result1 = id != null ? id.hashCode() : 0;
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "DdLoginResult{" +
                "id='" + id + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
