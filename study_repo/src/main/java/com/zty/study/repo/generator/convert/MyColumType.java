package com.zty.study.repo.generator.convert;

import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

public enum MyColumType implements IColumnType {
    // 基本类型
    VARCHAR("VARCHAR", null),
    CHAR("CHAR", null),
    BIGINT("BIGINT", null),
    TINYINT("TINYINT", null),
    INTEGER("INTEGER", null),
    LONGVARCHAR("LONGVARCHAR", null),
    DECIMAL("DECIMAL", null),
    TIMESTAMP("TIMESTAMP", "java.sql.Timestamp"),
    BASE_BOOLEAN("boolean", null),

    // 包装类型
    BYTE("Byte", null),
    SHORT("Short", null),
    CHARACTER("Character", null),
    LONG("Long", null),
    FLOAT("Float", null),
    DOUBLE("Double", null),
    BOOLEAN("Boolean", null),
    STRING("String", null),

    // sql 包下数据类型
    DATE_SQL("Date", "java.sql.Date"),
    TIME("Time", "java.sql.Time"),

    BLOB("Blob", "java.sql.Blob"),
    CLOB("Clob", "java.sql.Clob"),

    // java8 新时间类型
    LOCAL_DATE("LocalDate", "java.time.LocalDate"),
    LOCAL_TIME("LocalTime", "java.time.LocalTime"),
    YEAR("Year", "java.time.Year"),
    YEAR_MONTH("YearMonth", "java.time.YearMonth"),
    LOCAL_DATE_TIME("LocalDateTime", "java.time.LocalDateTime"),

    // 其他杂类
    BYTE_ARRAY("byte[]", null),
    OBJECT("Object", null),
    DATE("Date", "java.util.Date"),
    BIG_INTEGER("BigInteger", "java.math.BigInteger"),
    BIG_DECIMAL("BigDecimal", "java.math.BigDecimal");

    /**
     * 类型
     */
    private final String type;
    /**
     * 包路径
     */
    private final String pkg;

    MyColumType(final String type, final String pkg) {
        this.type = type;
        this.pkg = pkg;
    }


    public String getType() {
        return type;
    }


    public String getPkg() {
        return pkg;
    }
}
