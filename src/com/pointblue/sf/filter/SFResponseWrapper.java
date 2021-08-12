/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pointblue.sf.filter;


import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author jcombs
 */
public class SFResponseWrapper extends HttpServletResponseWrapper
{
     public SFResponseWrapper(final HttpServletResponse response) {
        super(response);    
    }

    @Override
    public String getHeader(String name) {
        // TODO Auto-generated method stub
        return super.getHeader(name);
    }
    @Override
    public Collection<String> getHeaderNames() {
        // TODO Auto-generated method stub
        return super.getHeaderNames();
    }
    
}
