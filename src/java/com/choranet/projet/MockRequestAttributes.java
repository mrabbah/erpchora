/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.choranet.projet;


import org.springframework.web.context.request.RequestAttributes;
//import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
public interface MockRequestAttributes extends RequestAttributes{
    HttpSession getSession();
}
