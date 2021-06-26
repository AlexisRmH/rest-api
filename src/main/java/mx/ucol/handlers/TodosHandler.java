package mx.ucol.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mx.ucol.models.ToDo;
import mx.ucol.helpers.DBConnection;
import mx.ucol.helpers.JSON;

public class ToDosHandler implements HttpHandler {
    public void handle(HttpExchange exchange) {
        try {
            String requestMethod = exchange.getRequestMethod();

            switch (requestMethod) {
                case "GET":
                    try {
                        getHandler(exchange);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case "POST":
                    postHandler(exchange);
                    break;
                case "PUT":
                    putHandler(exchange);
                    break;
                case "DELETE":
                    deleteHandler(exchange);
                    break;
                default:
            }
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    private void getHandler(HttpExchange exchange) throws IOException, SQLException {

        /*
         * Supported endpoints for the GET handler: GET /todos Get all the ToDo entries
         * rom the DB instance
         *
         * GET /todos/:id Get the ToDo entry with the :id from the DB instance
         *
         * To get the connection to the DB use the following Connection connection =
         * DBConnection.getInstance();
         *
         * Then you can use the connection variable to create statements.
         */

        OutputStream output = exchange.getResponseBody();
        Connection connection = DBConnection.getInstance();
        String sql = "SELECT * FROM todos";
        List<ToDo> toDoList = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()){
            ToDo todo = new ToDo();
            todo.setId(result.getInt("id"));
            todo.setTitle(result.getString("title"));
            todo.setCompleted(result.getInt("completed") == 1 ? true : false);

            toDoList.add(todo);
        }

        String jsonResponse = JSON.objectToJson(toDoList);
        byte[] response = jsonResponse.getBytes();

        exchange.sendResponseHeaders(200, response.length);
        output.write(response);
        output.close();
    }

    private void postHandler(HttpExchange exchange) {
        try {
            /*
             * Supported endpoints for the POST handler: POST /todos Creates a new ToDo
             * entry
             *
             * You can use getBodyContent to read a JSON from the HTTP request: String
             * jsonBody = getBodyContent(exchange.getRequestBody())
             *
             * Try to convert the jsonBody variable to a Todo object to ensure the JSON is
             * well-formed.
             */

            OutputStream output = exchange.getResponseBody();

            byte[] response = "POST Handler".getBytes();

            exchange.sendResponseHeaders(200, response.length);
            output.write(response);
            output.close();
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    private void putHandler(HttpExchange exchange) {
        try {

            /*
             * Supported endpoints for the PUT handler POST /todos/:id Update the details of
             * ToDo netry with :id if exists
             */

            OutputStream output = exchange.getResponseBody();
            byte[] response = "PUT Handler".getBytes();

            exchange.sendResponseHeaders(200, response.length);
            output.write(response);
            output.close();
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    private void deleteHandler(HttpExchange exchange) {
        try {

            /*
             * Supported endpoints for the DELETE handler DELETE /todos/:id Remove ToDo
             * entry with :id if exists
             */

            OutputStream output = exchange.getResponseBody();
            byte[] response = "DELETE Handler".getBytes();

            exchange.sendResponseHeaders(200, response.length);
            output.write(response);
            output.close();
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    private void notSupportedHandler(HttpExchange exchange) {
        try {
            OutputStream output = exchange.getResponseBody();
            byte[] response = "Not supported".getBytes();

            exchange.sendResponseHeaders(200, response.length);
            output.write(response);
            output.close();
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    private String getBodyContent(InputStream input) throws UnsupportedEncodingException, IOException {
        InputStreamReader streamReader = new InputStreamReader(input, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(streamReader);

        int buffer;
        StringBuilder builder = new StringBuilder();

        while ((buffer = bufferedReader.read()) != -1) {
            builder.append((char) buffer);
        }

        bufferedReader.close();
        streamReader.close();

        return builder.toString();
    }
}
