/**
 * 
 */
package com.vostroi.util.spring;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Controller中传输的Date类型格式转换
 */
@Slf4j
public class ParamDateEditor extends PropertyEditorSupport {
	/**
	 * 不同的格式转换
	 */
	private SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat minFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
	private SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy/MM/dd");
	/**
	 * 
	 */
	public ParamDateEditor() {	}

	/**
	 * @param source
	 */
	public ParamDateEditor(Object source) {
		super(source);
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Date date = null;
		try {
			// 防止空数据出错
			if (StrUtil.isNotBlank(text)) {
				if(text.matches("^\\d{4}-\\d{1,2}$")){ 
	                date = monthFormat.parse(text);
	            }else if(text.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
	            	date = dateFormat.parse(text);
	            }else if(text.matches("^\\d{4}/\\d{1,2}/\\d{1,2}$")){
	            	date = dayFormat.parse(text);
	            }else if(text.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
	            	date = minFormat.parse(text);
	            }else if(text.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
	            	date = fullFormat.parse(text);
	            }else if(text.length()>19) {
	            	text = text.substring(0, 19);
	            	date = fullFormat.parse(text);
	            }
			}
		} catch (Exception e) {			
			log.error("自动绑定日期数据出错", e);
		}
		setValue(date);
	}
	
	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		return (value != null ? fullFormat.format(value) : "");
	}


}
