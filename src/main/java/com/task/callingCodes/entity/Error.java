package com.task.callingCodes.entity;

public class Error {
    private String errorMsg;

    public Error(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Error() {
    }

    /**
     * Getter for errorMsg
     *
     * @return errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }
}
