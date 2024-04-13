package org.algotithmcontestdatacollect.displaybackend.service.recommend.handler;

import org.algotithmcontestdatacollect.displaybackend.service.recommend.Chain;

public interface ChainHandler<T> {
    void handle(T context, Chain<T> chain);
}