package netgloo.configs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = authUser.getUsername();
		if (username.matches("UserA")) {
			redirectStrategy.sendRedirect(arg0, arg1, "/notifications");
		}
		else
		{
			redirectStrategy.sendRedirect(arg0, arg1, "/");
		}
	}

}
