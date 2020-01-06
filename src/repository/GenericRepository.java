/**
 * 
 */
package repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Fernando Costa Migliorini
 * @email fercosmig@gmail.com
 * @since Dec 27, 2019
 * @file repository.GenericRepository.java
 */
public interface GenericRepository<T extends Serializable> {

	public void create(T object) throws Exception;

	public T retrieveById(T object) throws Exception;

	public List<T> retrieveAll() throws Exception;

	public void update(T object) throws Exception;

	public void delete(T object) throws Exception;

}
