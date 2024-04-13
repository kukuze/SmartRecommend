package org.algotithmcontestdatacollect.displaybackend.service.recommend;

import org.algotithmcontestdatacollect.displaybackend.service.recommend.handler.ChainHandler;

import java.util.ArrayList;
import java.util.List;

public class Chain<T> {
    private List<ChainHandler<T>> handlers = new ArrayList<>();
    private int index = 0;

    public Chain<T> addHandler(ChainHandler<T> handler) {
        handlers.add(handler);
        return this;
    }

    public void proceed(T context) {
        if (index < handlers.size()) {
            handlers.get(index++).handle(context, this);
        }
    }
}