package br.com.demoServlet.interfaces;

import java.util.List;

import br.com.demoServlet.models.User;

public interface UserInterface {
	public List<User> show();
	public void store(User user);
	public void update(User user);
	public void remove(Integer userId);
	public User index(Integer userId);
}
