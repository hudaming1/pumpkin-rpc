package org.hum.pumpkin.protocol;

import org.hum.pumpkin.common.serviceloader.support.SPI;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.protocol.exporter.Exporter;
import org.hum.pumpkin.protocol.invoker.Invoker;

@SPI
public interface Protocol {

	<T> Exporter<T> export(Class<T> classType, T instances, URL url);

	<T> Invoker<T> refer(Class<T> classType, URL url);
}