package com.yuansong.study.global;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yuansong.tools.common.DateTool;

@ControllerAdvice
public class ModelAttributeTest {
	
	private DateTool dt = new DateTool();
	
	@ModelAttribute(value="modelAttributeTest")
	public String modelAttributeTest() {
		return dt.GetDatetimeWithMillionsecond();
	}
}
