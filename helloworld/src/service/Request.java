package service;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

public class Request {
    public final String              method;
    public final String              path;
    public final String              protocol;
    public final Map<String, String> headers;
    public final List<String>        bodyLines;
    public final BufferedWriter      writer;

    public Request(Socket socket, Charset encoding) {
        try {
            BufferedReader reader      = new BufferedReader(new InputStreamReader(socket.getInputStream(), encoding.name()));
            String         requestLine = reader.readLine();
            String[]       parts       = requestLine.split(" ", 3);
            method = parts[0];
            path = parts[1];
            protocol = parts[2];
            headers = getHeaderMap(reader);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), encoding.name()));
            if (headers.containsKey("Expect")) {
                writer.write("HTTP/1.1 100 Continue\r\n\r\n");
                writer.flush();
            }
            bodyLines = getBodyLines(reader);
        } catch (Exception e) {
            throw new Error("could not make request", e);
        }
    }

    private static Map<String, String> getHeaderMap(BufferedReader reader) throws IOException {
        Map<String, String> headers = new HashMap<>();
        for (String line = reader.readLine(); !(line == null || line.isEmpty()); line = reader.readLine()) {
            String[] parts = line.split(": *", 2);
            headers.put(parts[0], parts[1]);
        }
        return Collections.unmodifiableMap(headers);
    }

    private List<String> getBodyLines(BufferedReader reader) throws IOException {
        List<String> lines            = new ArrayList<>();
        String       transferEncoding = headers.get("Transfer-Encoding");
        if (transferEncoding == null || "identity".equals(transferEncoding)) {
            return lines;
        }
        for (String line = reader.readLine(); !(line == null || line.isEmpty()); line = reader.readLine()) {
            lines.add(line);
        }
        return Collections.unmodifiableList(lines);
    }

}
