package com.vigorous.common.pojo;

public class ResultModel {
	private int status;
	
	private String message;
	
	private Object content;
	
	public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getContent() {
        return content;
    }

    public ResultModel(int code, String message) {
        this.status = code;
        this.message = message;
        this.content = "";
    }

    public ResultModel(int code, String message, Object content) {
        this.status = code;
        this.message = message;
        this.content = content;
    }
    
    public static ResultModel ok() {
		return new ResultModel(200, "OK");
	}

}
