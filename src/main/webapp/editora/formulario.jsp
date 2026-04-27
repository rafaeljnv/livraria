<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Livraria Virtual</title>
</head>
<body>
    <div align="center">
        <h1>Gerenciamento de Editoras</h1>
        <h2>
            <a href="lista">Lista de Editoras</a>
        </h2>
    </div>
    <div align="center">
        <c:choose>
            <c:when test="${editora != null}">
                <form action="atualizacao" method="post">
                    <table border="1">
                        <caption>Edição</caption>
                        <input type="hidden" name="id" value="${editora.id}" />
                        <tr>
                            <th><label for="cnpj">CNPJ</label></th>
                            <td><input type="text" id="cnpj" name="cnpj" size="20" required value="${editora.cnpj}" /></td>
                        </tr>
                        <tr>
                            <th><label for="nome">Nome</label></th>
                            <td><input type="text" id="nome" name="nome" size="45" required value="${editora.nome}" /></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Salvar" /></td>
                        </tr>
                    </table>
                </form>
            </c:when>
            <c:otherwise>
                <form action="insercao" method="post">
                    <table border="1">
                        <caption>Cadastro</caption>
                        <tr>
                            <th><label for="cnpj">CNPJ</label></th>
                            <td><input type="text" id="cnpj" name="cnpj" size="20" required /></td>
                        </tr>
                        <tr>
                            <th><label for="nome">Nome</label></th>
                            <td><input type="text" id="nome" name="nome" size="45" required /></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Salvar" /></td>
                        </tr>
                    </table>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>