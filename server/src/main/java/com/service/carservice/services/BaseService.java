package com.service.carservice.services;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService implements DisposableBean {

    public void destroy() {
        persistOnShutdown();
    }

    protected abstract void persistOnShutdown();
}