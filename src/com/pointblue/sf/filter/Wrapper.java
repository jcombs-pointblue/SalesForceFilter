/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pointblue.sf.filter;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.FilterConfig;

/**
 *
 * @author jcombs
 */
public class Wrapper implements Filter
{
    String requestStringToMatch; //lighning.force.com
    String queryParamPrefix; // c__

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException
    {
        System.out.println("SFFilter: doFilter called");
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final SFResponseWrapper wrapper = new SFResponseWrapper(httpResponse);
        
        //This invokes the rest of the filter chain using the wrapper
        chain.doFilter(request, wrapper);

        //after the chain has been processed, we have the response
        String locationHeader = httpResponse.getHeader("Location");
        System.out.println("SFFilter: locationHeader: " + locationHeader);
        Collection<String> headerNames = wrapper.getHeaderNames();
        
        //This would dump all the header names
       /* for (String headername : headerNames)
        {
            System.out.println("SFFilter: headername: " + headername);

        }
       */
        if (locationHeader != null)
        {
            System.out.println("SFFilter: locationHeader: " + locationHeader);
            if (locationHeader.contains(requestStringToMatch))
            {
                locationHeader = locationHeader.replace("code=", queryParamPrefix+"code=");
                //locationHeader = locationHeader.replace("code=", "c__code=");

                System.out.println("SFFilter: locationHeader: out: " + locationHeader);
                httpResponse.setHeader("Location", locationHeader);

            }

        }else
        {
          System.out.println("SFFilter: no Location Header ");
 
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        System.out.println("SFFilter: init called");
       requestStringToMatch = filterConfig.getInitParameter("requestStringToMatch");
       if(requestStringToMatch == null)
       {
           System.out.println("SFFilter: missing required parameter: requestStringToMatch");
       }
       queryParamPrefix = filterConfig.getInitParameter("queryParamPrefix");
     if(queryParamPrefix == null)
       {
           System.out.println("SFFilter: missing required parameter: queryParamPrefix");
       }  
        

    }

    @Override
    public void destroy()
    {
    }
}
