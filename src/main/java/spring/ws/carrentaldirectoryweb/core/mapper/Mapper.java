package spring.ws.carrentaldirectoryweb.core.mapper;

public interface Mapper<F, T> {
    T map(F object);
}
