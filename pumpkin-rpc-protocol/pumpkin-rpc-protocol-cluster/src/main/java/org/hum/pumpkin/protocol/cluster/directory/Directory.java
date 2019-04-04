package org.hum.pumpkin.protocol.cluster.directory;

import java.util.List;

import org.hum.pumpkin.registry.EndPoint;

public interface Directory<T> {

    Class<T> getInterface();
    
    List<EndPoint> list(String key);
}

