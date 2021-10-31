package com.whiplash.core.commom.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/9/2 15:01
 * @projectName whiplash
 * @title: ResultData
 * @description: 封装 API返回的数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public static<T> ResultData<T> getResultData(T t){
        if(t!=null){
            return getResultDataSuccess(t);
        }else{
            return getResultDataError(t);
        }
    }

    public static<T> ResultData<T> getResultData(EnumConstant.RESULT_CODE code , T t){
        ResultData resultData = new ResultData();
        resultData.setCode(code.getCode());
        resultData.setMsg(code.getMsg());
        resultData.setData(t);
        return resultData;
    }

    public static<T> ResultData<T> getResultData(EnumConstant.RESULT_CODE code , String message , T t){
        ResultData resultData = new ResultData();
        resultData.setCode(code.getCode());
        resultData.setMsg(code.getMsg());
        resultData.setMessage(message);
        resultData.setData(t);
        return resultData;
    }

    /**
     * 通用返回
     * 错误 请求参数为空
     * @param <T>
     * @return
     */
    public static <T> ResultData<T> getResultDataParamEmpty() {
        ResultData resultData = new ResultData();
        resultData.setCode(EnumConstant.RESULT_CODE.ER_3333.getCode());
        resultData.setMsg(EnumConstant.RESULT_CODE.ER_3333.getMsg());
        resultData.setMessage(CommomConstant.REQUEST_PARAMS_EMPTY);
        return resultData;
    }

    /**
     * 通用返回
     * 错误 请求参数错误
     * @param <T>
     * @return
     */
    public static <T> ResultData<T> getResultDataParamError() {
        ResultData resultData = new ResultData();
        resultData.setCode(EnumConstant.RESULT_CODE.ER_3333.getCode());
        resultData.setMsg(EnumConstant.RESULT_CODE.ER_3333.getMsg());
        resultData.setMessage(CommomConstant.REQUEST_PARAMS_ERROR);
        return resultData;
    }

    /**
     * 通用返回
     * 错误 请求数据不存在
     * @param <T>
     * @return
     */
    public static <T> ResultData<T> getResultDataNotExist() {
        ResultData resultData = new ResultData();
        resultData.setCode(EnumConstant.RESULT_CODE.SU_0000.getCode());
        resultData.setMsg(EnumConstant.RESULT_CODE.SU_0000.getMsg());
        resultData.setMessage(CommomConstant.REQUEST_DATA_NOT_EXIST);
        return resultData;
    }

    /**
     * 通用返回
     * 错误
     * @param <T>
     * @return
     */
    public static <T> ResultData<T> getResultDataError() {
        ResultData resultData = new ResultData();
        resultData.setCode(EnumConstant.RESULT_CODE.ER_3333.getCode());
        resultData.setMsg(EnumConstant.RESULT_CODE.ER_3333.getMsg());
        return resultData;
    }

    public static <T> ResultData<T> getResultDataSuccess() {
        ResultData resultData = new ResultData();
        resultData.setCode(EnumConstant.RESULT_CODE.SU_0000.getCode());
        resultData.setMsg(EnumConstant.RESULT_CODE.SU_0000.getMsg());
        return resultData;
    }

    public static <T> ResultData<T> getResultDataError(T t) {
        ResultData resultData = new ResultData();
        resultData.setCode(EnumConstant.RESULT_CODE.ER_3333.getCode());
        resultData.setMsg(EnumConstant.RESULT_CODE.ER_3333.getMsg());
        resultData.setData(t);
        return resultData;
    }

    public static <T> ResultData<T> getResultDataSuccess(T t) {
        ResultData resultData = new ResultData();
        resultData.setCode(EnumConstant.RESULT_CODE.SU_0000.getCode());
        resultData.setMsg(EnumConstant.RESULT_CODE.SU_0000.getMsg());
        resultData.setData(t);
        return resultData;
    }

    public static <T> ResultData<List<T>> getResultData(List<T> list) {
        ResultData resultData = new ResultData();
        if(list == null || list.isEmpty()){
            resultData.setCode(EnumConstant.RESULT_CODE.ER_3333.getCode());
            resultData.setMsg(EnumConstant.RESULT_CODE.ER_3333.getMsg());
        }else{
            resultData.setCode(EnumConstant.RESULT_CODE.SU_0000.getCode());
            resultData.setMsg(EnumConstant.RESULT_CODE.SU_0000.getMsg());
        }
        resultData.setData(list);
        return resultData;
    }

    /**
     * 请求无权限 被拒绝 通用返回
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResultData<T> forbidden(T t) {
        ResultData resultData = new ResultData();
        resultData.setCode(EnumConstant.RESULT_CODE.HTTP_STATUS_FORBIDDEN.getCode());
        resultData.setMsg(EnumConstant.RESULT_CODE.HTTP_STATUS_FORBIDDEN.getMsg());
        resultData.setData(t);
        return resultData;
    }

    /**
     * 未登录 或者 token 过期
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResultData<T> unauthorized(T t) {
        ResultData resultData = new ResultData();
        resultData.setCode(EnumConstant.RESULT_CODE.HTTP_STATUS_UNAUTHORIZED.getCode());
        resultData.setMsg(EnumConstant.RESULT_CODE.HTTP_STATUS_UNAUTHORIZED.getMsg());
        resultData.setData(t);
        return resultData;
    }

}
