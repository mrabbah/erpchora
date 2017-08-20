package com.choranet.exception;

/**
 *
 * @author rabbah
 */
class CommunExceptionParams {

    private String prefixErrText;
    private String debugText;
    private String errorCode;
    private String messageBunble;
    private String[] messageParams;

    /**
     * @return the prefixErrText
     */
    public final String getPrefixErrText() {
        return prefixErrText;
    }

    /**
     * @param prefixErrText the prefixErrText to set
     */
    public final void setPrefixErrText(String prefixErrText) {
        this.prefixErrText = prefixErrText;
    }

    /**
     * @return the debugText
     */
    public final String getDebugText() {
        return debugText;
    }

    /**
     * @param debugText the debugText to set
     */
    public final void setDebugText(String debugText) {
        this.debugText = debugText;
    }

    /**
     * @return the errorCode
     */
    public final String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public final void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the messageBunble
     */
    public final String getMessageBunble() {
        return messageBunble;
    }

    /**
     * @param messageBunble the messageBunble to set
     */
    public final void setMessageBunble(String messageBunble) {
        this.messageBunble = messageBunble;
    }

    /**
     * @return the messageParams
     */
    public final String[] getMessageParams() {
        return messageParams;
    }

    /**
     * @param messageParams the messageParams to set
     */
    public final void setMessageParams(String[] messageParams) {
        this.messageParams = messageParams;
    }
}
