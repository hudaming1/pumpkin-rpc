package org.hum.pumpkin.common.serviceloader.support;

import org.hum.pumpkin.common.exception.PumpkinException;

public class MetaData {
	
	private String defaultName;
	
	public MetaData(Class<?> classType) {
		// 获取ClassType的注解
		SPI spiAnno = classType.getAnnotation(SPI.class);
		if (spiAnno == null) {
			throw new PumpkinException("Extension type(" + classType.getName() + ") is not extension, because WITHOUT @" + SPI.class.getSimpleName() + " Annotation!");
		}
		defaultName = spiAnno.value();
	}

	public String getDefaultExtName() {
		return defaultName;
	}
}
