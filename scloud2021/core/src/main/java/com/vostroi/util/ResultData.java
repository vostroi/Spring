package com.vostroi.util;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @date 2020/8/18 16:58
 * @projectName mcloud
 * @title: ResultData
 * @description: 封装 API返回的数据
 */
@Data
public class ResultData<T> implements Serializable {
    /**
     * 返回代码
     */
    private String code;
    /**
     * 返回消息(code对应的消息)
     */
    private String msg;
    /**
     * 返回详细描述消息
     */

    private String message;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 页脚数据
     */
    private List<?> footer;

    /**
     * 当前页面
     */
    private long page = 0;
    /**
     * 当前页条数
     */
    private long size = 0;
    /**
     * 总条数
     */
    private long total = 0;
    /**
     * 总页数
     */
    private long totalPage = 0;

    public static ResultData getResultData(EnumConstant.RESULT_CODE code ){
        ResultData resultData = new ResultData();
        resultData.setCode(code.getCode());
        resultData.setMsg(code.getMsg());
        return resultData;
    }

    public static ResultData getResultData(EnumConstant.RESULT_CODE code , String message ){
        ResultData resultData = new ResultData();
        resultData.setCode(code.getCode());
        resultData.setMsg(code.getMsg());
        resultData.setMessage(message);
        return resultData;
    }

    public static<R> ResultData<R> getResultData(R r){
        ResultData resultData = new ResultData();
        if(r!=null){
            resultData.setCode(EnumConstant.RESULT_CODE.SU_0000.getCode());
            resultData.setMsg(EnumConstant.RESULT_CODE.getName(resultData.getCode()));
        }else{
            resultData.setCode(EnumConstant.RESULT_CODE.ER_3333.getCode());
            resultData.setMsg(EnumConstant.RESULT_CODE.getName(resultData.getCode()));

        }

        resultData.setData(r);
        return resultData;
    }

    public static<R> ResultData<R> getResultData(EnumConstant.RESULT_CODE code , R r){
        ResultData resultData = new ResultData();
        resultData.setCode(code.getCode());
        resultData.setMsg(code.getMsg());
        resultData.setData(r);
        return resultData;
    }

    public static<R> ResultData<R> getResultData(EnumConstant.RESULT_CODE code , String message , R r){
        ResultData resultData = new ResultData();
        resultData.setCode(code.getCode());
        resultData.setMsg(code.getMsg());
        resultData.setMessage(message);
        resultData.setData(r);
        return resultData;
    }

}
