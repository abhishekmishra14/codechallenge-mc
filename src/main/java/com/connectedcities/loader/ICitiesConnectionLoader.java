package com.connectedcities.loader;

import java.util.List;

public interface ICitiesConnectionLoader<E, R> {

	public List<R> loaddata(E e) throws Exception;
}
