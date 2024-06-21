<%--
Views should be stored under the WEB-INF folder so that
they are not accessible except through controller process.

This JSP is here to provide a redirect to the dispatcher
servlet but should be the only JSP outside of WEB-INF.
--%>
<%@page import="model.bean.Usuario"%>
<%@page import="model.dao.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% response.sendRedirect("./inicio"); %>

<%
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    Usuario.setIdUsuarioStatic(0);
%>
