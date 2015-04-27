package db;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ClearedRequest extends HttpServletRequestWrapper
{

    /**
     * Create a new request wrapper that will merge additional parameters into
     * the request object without prematurely reading parameters from the
     * original request.
     * 
     * @param request
     * @param additionalParams
     */
    public ClearedRequest(final HttpServletRequest request)
    {
        super(request);
    }

    @Override
    public String getParameter(final String name)
    {
        return null;
    }

    @Override
    public Map<String, String[]> getParameterMap()
    {
        
        //Return an unmodifiable collection because we need to uphold the interface contract.
        return new TreeMap<String, String[]>();
    }

    @Override
    public Enumeration<String> getParameterNames()
    {
        return null;
    }

    @Override
    public String[] getParameterValues(final String name)
    {
        return null;
    }
}
