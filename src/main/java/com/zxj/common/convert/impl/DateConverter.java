package com.zxj.common.convert.impl;

import java.util.Calendar;
import java.util.Date;

import com.zxj.common.convert.AbstractConverter;
import com.zxj.common.utils.DateUtils;
import com.zxj.common.utils.StringUtils;

/**
 * 日期转换器
 *
 * @author Looly
 */
public class DateConverter extends AbstractConverter<Date> {

    /**
     * 日期格式化
     */
    private String format;

    /**
     * 获取日期格式
     *
     * @return 设置日期格式
     */
    public String getFormat() {
        return format;
    }

    /**
     * 设置日期格式
     *
     * @param format 日期格式
     */
    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    protected Date convertInternal(Object value) {
        // Handle Calendar
        if (value instanceof Calendar) {
            return ((Calendar) value).getTime();
        }

        // Handle Long
        if (value instanceof Long) {
            //此处使用自动拆装箱
            return new Date((Long) value);
        }

        final String valueStr = convertToStr(value);
        try {
            return StringUtils.isBlank(format) ? DateUtils.parse(valueStr) : DateUtils.parse(valueStr, format);
        } catch (Exception e) {
            // Ignore Exception
        }
        return null;
    }

}
