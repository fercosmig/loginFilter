/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Fernando Costa Migliorini
 * @email fercosmig@gmail.com
 * @since Dec 27, 2019
 * @file model.User.java
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Attributes
	 */
	private Integer id;
	private String login;
	private String password;
	private Boolean status;
	private Date registrationDate;

	/**
	 * Constructors
	 */
	public User() {
	}

	/**
	 * @param id
	 * @param login
	 * @param password
	 * @param status
	 * @param registrationDate
	 */
	public User(Integer id, String login, String password, Boolean status, Date registrationDate) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.status = status;
		this.registrationDate = registrationDate;
	}

	/**
	 * Getters and Setters
	 */

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
