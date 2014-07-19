package io.higgs.http.server;

import io.higgs.core.InvokableMethod;
import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * @author Courtney Robinson <courtney@crlog.info>
 */
public class WebApplicationException extends RuntimeException {
    private InvokableMethod source;
    private HttpResponseStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private String template, message;
    private Object response;
    private HttpRequest request;

    public WebApplicationException(HttpResponseStatus status, HttpRequest request, InvokableMethod source) {
        this(status);
        this.request = request;
        this.source = source;
    }

    public WebApplicationException(HttpResponseStatus status) {
        this.status = status;
    }

    public WebApplicationException(HttpResponseStatus status, Object response, HttpRequest request) {
        this(status, response, request, null);
    }

    public WebApplicationException(HttpResponseStatus status, Object response,
                                   HttpRequest request, String template) {
        this(status, template, request);
        this.response = response;
    }

    public WebApplicationException(HttpResponseStatus status, String template, HttpRequest request) {
        this(status, request);
        this.template = template;
    }

    public WebApplicationException(HttpResponseStatus status, HttpRequest request) {
        this(status);
        this.request = request;
    }

    public WebApplicationException(WebApplicationException cause) {
        this(cause.getStatus(), cause.getResponse(), cause.getRequest(), cause.getTemplate());
    }

    public HttpResponseStatus getStatus() {
        return status;
    }

    public Object getResponse() {
        return response;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(final HttpRequest request) {
        this.request = request;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(final String template) {
        this.template = template;
    }

    public void setResponse(final Object response) {
        this.response = response;
    }

    public void setStatus(final HttpResponseStatus status) {
        this.status = status;
    }

    public WebApplicationException(final HttpResponseStatus status, final String template) {
        this(status, template, null);
    }

    public WebApplicationException(HttpResponseStatus status, HttpRequest request, Throwable cause) {
        super(cause);
        this.status = status;
        this.request = request;
    }

    public boolean hasRequest() {
        return request != null;
    }

    public InvokableMethod getSource() {
        return source;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "WebApplicationException{" +
                "status=" + status +
                ", template='" + template + '\'' +
                ", response=" + response +
                ", path=" + (request == null ? "null" : request.getUri()) +
                '}';
    }

    public void setMessage(String message) {
        this.message = message;
    }
}