package com.frankc137.jinote.dao;

import com.frankc137.jinote.dto.Base;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class RepoProxy<T extends Base> implements Repo<T> {

    private CrudRepo<T> repo;

    public RepoProxy(CrudRepo<T> repo) {
        this.repo = repo;
    }

    @Override
    public T get(String id) {
        if (id == null) return null;
        return repo.findById(id).orElse(null);
    }

    @Override
    public T set(T t) {
        return repo.save(t);
    }

    @Override
    public List<T> listAll() {
        return repo.listAll();
    }

    @Override
    public List<T> list(String parentid) {
        return repo.list(parentid);
    }

    @Override
    public List<T> listBy(String groupid) {
        return repo.listBy(groupid);
    }

    @Override
    public T del(String id) {
        T t = get(id);
        if (t != null) {
            repo.deleteById(id);
            return t;
        }
        return null;
    }

    public <R extends Repo<T>> R proxying(Class<R> iface) {
        return (R) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{ iface }, (proxy, method, args) -> {
            try {
                return method.invoke(RepoProxy.this, args);
            } catch (IllegalArgumentException e) {
                Method m = repo.getClass().getMethod(method.getName(), method.getParameterTypes());
                return m.invoke(repo, args);
            }
        });
    }

}
