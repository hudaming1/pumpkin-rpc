package org.hum.pumpkin.protocol.cluster.directory;

import java.util.List;

public interface Directory<T> {

    Class<T> getInterface();
    
    List<String> list(String key);
}

