/**
 * 
 */
package controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.User;
import repository.UserRepository;
import util.Encryption;

/**
 * @author Fernando Costa Migliorini
 * @email fercosmig@gmail.com
 * @since Dec 27, 2019
 * @file controller.LoginBean.java
 */
@ManagedBean(name = "lb")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(LoginBean.class.getName());

	/**
	 * Attributes
	 */
	private String usr;
	private String pwd;
	private User user;
	private boolean isLogged;

	/**
	 * Constructors
	 */
	public LoginBean() {
		LOGGER.info("classe instanciada");
		this.isLogged = false;
	}

	/**
	 * Methods
	 */
	public String login() {
		LOGGER.info("validando login - usr = " + this.usr + ",  pwd = " + this.pwd);
		FacesContext context = FacesContext.getCurrentInstance();

		this.usr = this.usr.trim();

		User inputUser = new User();
		inputUser.setLogin(this.usr);

		try {
			UserRepository ur = new UserRepository();
			User validatingUser = ur.retrieveByLogin(inputUser);
			LOGGER.info("usuario do banco: " + validatingUser.getLogin());
			this.pwd = this.pwd.trim();
			this.pwd = Encryption.getMD5(this.pwd);

			if (pwd.equals(validatingUser.getPassword())) {
				LOGGER.info("password ok");
				this.user = validatingUser;
				context.getExternalContext().getSessionMap().put("isLogged", true);
				context.getExternalContext().getSessionMap().put("loggedUser", this.user);
				return "home";
			} else {
				LOGGER.info("invalid password");
				FacesMessage msg = new FacesMessage("invalid user or password!");
				context.getExternalContext().invalidateSession();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null, msg);
				return null;
			}
		} catch (Exception e) {
			LOGGER.error("invalid login: " + e);
			FacesMessage msg = new FacesMessage("invalid user or password!");
			context.getExternalContext().invalidateSession();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
			return null;
		}

	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();

		LOGGER.info("method logout");
		this.isLogged = false;
		this.user = null;
		this.usr = null;
		this.pwd = null;
		context.getExternalContext().invalidateSession();
		return "index?faces-redirect=true";
	}

	/**
	 * Getters and Setters
	 */
	/**
	 * @return the usr
	 */
	public String getUsr() {
		return usr;
	}

	/**
	 * @param usr the usr to set
	 */
	public void setUsr(String usr) {
		this.usr = usr;
	}

	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the isLogged
	 */
	public boolean isLogged() {
		return isLogged;
	}

	/**
	 * @param isLogged the isLogged to set
	 */
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

}
