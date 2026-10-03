package Creational_Design_Pattern.Builder_Design_Pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SqlQuery{
    private final String table;
    private final List<String> columns;
    private final List<String> conditions;
    private final String orderBy;
    private final String orderDirection;
    private final int limit;
    private final int offset;

    private SqlQuery(Builder builder){
        this.table=builder.table;
        this.columns=List.copyOf(builder.columns);
        this.conditions=List.copyOf(builder.conditions);
        this.orderBy=builder.orderBy;
        this.orderDirection=builder.orderDirection;
        this.limit=builder.limit;
        this.offset=builder.offset;
    }

    public String toSql() {
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append(columns.isEmpty() ? "*" : String.join(", ", columns));
        sql.append(" FROM ").append(table);
        if (!conditions.isEmpty()) {
            sql.append(" WHERE ").append(String.join(" AND ", conditions));
        }
        if (orderBy != null) {
            sql.append(" ORDER BY ").append(orderBy).append(" ").append(orderDirection);
        }
        if (limit > 0) {
            sql.append(" LIMIT ").append(limit);
        }
        if (offset > 0) {
            sql.append(" OFFSET ").append(offset);
        }
        return sql.toString();
    }

    public static class Builder{
        private final String table;
        private List<String> columns=new ArrayList<>();
        private List<String> conditions=new ArrayList<>();
        private String orderBy;
        private String orderDirection="ASC";
        private int limit;
        private int offset;

        public Builder(String table){
            this.table=table;
        }

        public Builder select(String... cols){
            this.columns.addAll(Arrays.asList(cols));
            return this;
        }

        public Builder where(String condition){
            this.conditions.add(condition);
            return this;
        }

        public Builder orderBy(String column, String direction) {
            this.orderBy = column;
            this.orderDirection = direction;
            return this;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder offset(int offset) {
            this.offset = offset;
            return this;
        }

        public SqlQuery build() {
            return new SqlQuery(this);
        }
    }
}

public class SQL_Query_Builder {
    public static void main(String[] args) {
        SqlQuery query1 = new SqlQuery.Builder("users")
                .select("name", "email")
                .where("age > 18")
                .where("active = true")
                .orderBy("name", "ASC")
                .limit(10)
                .build();

        SqlQuery query2 = new SqlQuery.Builder("orders")
                .select("id", "total", "created_at")
                .where("status = 'completed'")
                .where("total > 100")
                .orderBy("created_at", "DESC")
                .limit(20)
                .offset(40)
                .build();

        System.out.println(query1.toSql());
        System.out.println(query2.toSql());
    }
}
