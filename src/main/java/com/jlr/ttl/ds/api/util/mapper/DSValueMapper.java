package com.jlr.ttl.ds.api.util.mapper;

public interface DSValueMapper<E,R,Q> {
    public static <E,Q> E requestToEntity(Q entity){
        // Interface method
        return null;
    }
    public static <R,E> R responseToEntity(E entity){
        // Interface method
        return null;
    }
    public static <E,R> R entityToResponse(E entity) {
        // Interface method
        return null;
    }
}
