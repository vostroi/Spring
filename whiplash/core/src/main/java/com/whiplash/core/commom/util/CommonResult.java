package com.whiplash.core.commom.util;

import javafx.scene.chart.StackedAreaChart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2021/10/29 15:28
 * @projectName whiplash
 * @title: CommomResult
 * @description: 用于 内部 方法调用 返回值 封装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {

    /**
     * 返回的数据
     */
    private T t;

    private Boolean hasError = false;

    /**
     * 错误信息
     */
    private String errorMsg = CommomConstant.STR_EMPTY;

    public static <T> CommonResult<T> getResult(T t) {
        if(t == null){
            return fail(t);
        }

        return success(t);
    }


    /**
     * 成功
     * @param t
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T t) {
        CommonResult cr = new CommonResult();
        cr.setT(t);
        cr.setHasError(false);
        cr.setErrorMsg(CommomConstant.STR_EMPTY);
        return cr;
    }

    public static <T> CommonResult<T> fail(T t){
        CommonResult cr = new CommonResult();
        cr.setT(t);
        cr.setHasError(false);
        cr.setErrorMsg(CommomConstant.STR_METHOD_RETURN_NULL);
        return cr;
    }

    public static <T> CommonResult<T> fail(String errorMsg){
        CommonResult cr = new CommonResult();
        cr.setHasError(true);
        cr.setErrorMsg(errorMsg);
        return cr;
    }

}
