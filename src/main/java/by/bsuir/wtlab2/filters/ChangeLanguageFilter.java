package by.bsuir.wtlab2.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Class of the filter to change language
 * @author haidukevgen
 * @version 1.0
 */
public class ChangeLanguageFilter implements Filter {
    /**
     * Method to get correct language
     * @param httpServletRequest Servlet request object
     * @return Selected language
     */
    private String getLocale(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        String language = (String)httpSession.getAttribute("lang");
        if (language == null) {
            language = "en";
            httpSession.setAttribute("lang", "en");
        }
        return language;
    }

    /**
     * Method to do filter for language
     * @param request Servlet request object
     * @param response Servlet response object
     * @param chain Filter chain object
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String language = getLocale(req);
        req.setAttribute("lang", language);
        chain.doFilter(request, response);
    }
}