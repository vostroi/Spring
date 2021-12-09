package com.whiplash.core.commom.spring;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.whiplash.core.commom.util.WhiplashStrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author Administrator
 * @date 2021/12/7 17:07
 * @projectName whiplash
 * @title: PagerUtil
 * @description: 分页对象工具类
 */
@Slf4j
public class PagerUtil {

    public static Pageable initPage(int pageNumber, int pageSize, String sort, String order) {
        Pageable pageable;
        if(pageNumber<1) {
            pageNumber = 1;
        }
        if(pageSize<=0) {
            pageSize = 10;
        }

        //排序处理
        if(StrUtil.isNotEmpty(sort)) {
            pageable = PageRequest.of(pageNumber-1, pageSize,initSort(sort,order));
        }else {
            pageable = PageRequest.of(pageNumber-1, pageSize);
        }
        return pageable;
    }

    /**
     * 初始化排序
     * @param sort
     * @param order
     * @return
     */
    public static Sort initSort(String sort, String order) {
        if(StrUtil.isEmpty(sort)) {
            return Sort.unsorted();
        }
        Sort.Direction d;

        if(StrUtil.isEmpty(order)) {
            order = "desc";
        }
        String[] regexs = new String[] {",",";"};
        List<String> sortFields = WhiplashStrUtil.splitString(sort,regexs);
        List<String> orderProps = WhiplashStrUtil.splitString(order,regexs);

        int sortSize = sortFields.size();
        int orderSize = orderProps.size();
        if(orderSize==1) {
            d = Sort.Direction.fromString(orderProps.get(0));

            return Sort.by(d, sortFields.toArray(new String[sortFields.size()]));
        }else {
            List<Sort.Order> orders = Lists.newArrayList();
            Sort.Order o = null;
            for(int i=0;i<sortSize;i++) {
                if(i<orderSize) {
                    o = new Sort.Order(Sort.Direction.fromString(orderProps.get(i)),sortFields.get(i));
                }else {
                    o = new Sort.Order(Sort.Direction.fromString(orderProps.get(0)),sortFields.get(i));
                }
                orders.add(o);
            }

            return Sort.by(orders);
        }
    }


}
