package com.wty.app.library.data;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    public static class SqlQueryBuilder{

        public List<String> sqlquery = new ArrayList<String>();

        public SqlQueryBuilder(){
            sqlquery.clear();
        }

        /**
         * @Decription select *
         **/
        public SqlQueryBuilder selectAll(){
            select("*");
            return this;
        }

        /**
         * @Decription select null
         **/
        public SqlQueryBuilder selectNull(){
            select("null");
            return this;
        }

        /**
         * @Decription select column1,column2,column3
         * @param columnname 列名
         **/
        public SqlQueryBuilder select(String... columnname){
            List<String> list = new ArrayList<String>();
            for(String column:columnname){
                list.add(column);
            }
            sqlquery.add(String.format(" select %s ", TextUtils.join(",", list)));
            return this;
        }

        /**
         * @Decription from tablename
         * @param tablename 表名
         **/
        public SqlQueryBuilder from(String tablename){
            sqlquery.add(String.format(" from %s ",tablename));
            return this;
        }

        /**
         * @Decription as alias
         * @param alias 别名
         **/
        public SqlQueryBuilder as(String alias){
            sqlquery.add(String.format(" as %s ",alias));
            return this;
        }

        /**
         * @Decription where clauses
         * @param clauses 子句
         **/
        public SqlQueryBuilder where(String clauses){
            sqlquery.add(String.format(" where %s ",clauses));
            return this;
        }

        /**
         * @Decription and clauses
         * @param clauses 子句
         **/
        public SqlQueryBuilder and(String clauses){
            sqlquery.add(String.format(" and %s ",clauses));
            return this;
        }

        /**
         * @Decription or clauses
         * @param clauses 子句
         **/
        public SqlQueryBuilder or(String clauses){
            sqlquery.add(String.format(" or %s ",clauses));
            return this;
        }

        /**
         * @Decription between clauses
         * @param clauses 子句
         **/
        public SqlQueryBuilder between(String clauses){
            sqlquery.add(String.format(" between %s ",clauses));
            return this;
        }

        /**
         * @Decription exists clauses
         * @param clauses 子句
         **/
        public SqlQueryBuilder exists(String clauses){
            sqlquery.add(String.format(" exists %s ",clauses));
            return this;
        }

        /**
         * @Decription not exists clauses
         * @param clauses 子句
         **/
        public SqlQueryBuilder notExists(String clauses){
            sqlquery.add(String.format(" not exists %s ",clauses));
            return this;
        }

        /**
         * @Decription not in clauses
         * @param clauses 子句
         **/
        public SqlQueryBuilder notIn(String clauses){
            sqlquery.add(String.format(" not in %s ",clauses));
            return this;
        }

        /**
         * @Decription not between clauses
         * @param clauses 子句
         **/
        public SqlQueryBuilder notBetween(String clauses){
            sqlquery.add(String.format(" not between %s ",clauses));
            return this;
        }

        /**
         * @Decription limit value
         * @param value 个数
         **/
        public SqlQueryBuilder limit(int value){
            sqlquery.add(String.format(" limit %s ",value));
            return this;
        }

        /**
         * @Decription limit value
         * @param value 个数
         **/
        public SqlQueryBuilder offset(int value){
            sqlquery.add(String.format(" offset %s ",value));
            return this;
        }

        /**
         * @Decription order by value desc
         * @param columnname 列名
         **/
        public SqlQueryBuilder orderByDesc(String columnname){
            sqlquery.add(String.format(" order by %s desc",columnname));
            return this;
        }

        /**
         * @Decription left join on
         * @param tablename 表名
         * @param clauses On子句
         **/
        public SqlQueryBuilder leftJoin(String tablename,String clauses){
            sqlquery.add(String.format("left join %s on %s",tablename,clauses));
            return this;
        }

        /**
         * @Decription 生成sqlite语句
         **/
        public String Builder(){
            return TextUtils.join(" ",sqlquery);
        }
    }

}
