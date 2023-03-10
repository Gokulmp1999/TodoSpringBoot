<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
    <div class="container">
        <h1>My Todos</h1>
        <hr>
        <table class="table">
            <thead>
                <tr>
                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Is Done?</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td>${todo.description}</td>
                        <td>${todo.targetDate}</td>
                        <td>${todo.done}</td>
                        <td><a href="delete-todo?id=${todo.id}" Class="btn btn-warning">DELETE</a></td>
                        <td><a href="update-todo?id=${todo.id}" Class="btn btn-success">UPDATE</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="add-todo" class="btn btn-success">Add Todos</a>

    </div>
<%@ include file="common/footer.jspf" %>