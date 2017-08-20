package com.choranet.exception;

import com.choranet.commun.Utf8ResourceBundle;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.transaction.TransactionSystemException;

/**
 *
 * @author rabbah
 */
public class CommunException extends Exception {

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(CommunException.class);
    private int depth = 4;
    private int MAX_DEPTH_ANALYSIS = 20;
    private DBInformation dbInfoCause;
    protected List<InfoItem> infoItems = new ArrayList<InfoItem>();
    protected InfoItem lastInfoItem = null;
    private String errorCode = null;
    private String prefixErrText = "";
    private String debugText = "";

    private static String getString(String errorCode, String messageBunble) {

        String errCodeMessage = null;
        try {
            errCodeMessage = Utf8ResourceBundle.getBundle(messageBunble).getString(errorCode);
        } catch (Exception e) {
        }

        if (errCodeMessage == null) {
            errCodeMessage = "\"" + errorCode + "\"";
        }

        return errCodeMessage;
    }

    /**
     * Constructor for CommunException The message must match the errorCode
     *
     * @param errorCode
     * @param messageBunble
     */
    public CommunException(String errorCode, String messageBunble) {
        /*
         * ok, it's a bit dirty but it permits to get the message with
         * e.getMessage() instead of null
         */
        super(MessageFormat.format(getString(errorCode, messageBunble), (Object[]) null));
        this.errorCode = errorCode;

        String[] messageParams = null;
        addInfo(errorCode, messageBunble, messageParams);
    }

    /**
     * Constructor for CommunException The message must match the errorCode
     *
     * @param errorCode
     * @param messageBunble
     * @param cause
     */
    public CommunException(String errorCode, String messageBunble, Throwable cause) {
        // The message must match the errorCode
        super(MessageFormat.format(getString(errorCode, messageBunble), (Object[]) null));
        this.errorCode = errorCode;
        this.initCause(cause);
        String[] messageParams = null;
        addInfo(errorCode, messageBunble, messageParams);
    }

    /**
     * Constructor for CommunException The message must match the errorCode
     *
     * @param errorCode
     * @param messageBunble
     * @param messageParams
     */
    public CommunException(String errorCode, String messageBunble, String[] messageParams) {
        // The message must match the errorCode
        super(MessageFormat.format(getString(errorCode, messageBunble), (Object[]) messageParams));
        this.errorCode = errorCode;
        addInfo(errorCode, messageBunble, messageParams);
    }

    public CommunException(String errorCode, String messageBunble, Object[] messageParams) {
        super(MessageFormat.format(getString(errorCode, messageBunble), (Object[]) messageParams));
        this.errorCode = errorCode;
        addInfo(errorCode, messageBunble, messageParams);
    }

    public CommunException(String errorCode, String messageBunble, String[] messageParams, Throwable cause) {
        super(MessageFormat.format(getString(errorCode, messageBunble), (Object[]) messageParams));
        this.initCause(cause);
        this.errorCode = errorCode;
        addInfo(errorCode, messageBunble, messageParams);
    }

    public CommunException(String errorCode, String messageBunble, Object[] messageParams, Throwable cause) {
        super(MessageFormat.format(getString(errorCode, messageBunble), (Object[]) messageParams));
        this.initCause(cause);
        this.errorCode = errorCode;
        addInfo(errorCode, messageBunble, messageParams);
    }

    public CommunException(CommunExceptionParams params, Throwable cause) {
        super(MessageFormat.format(getString(params.getErrorCode(), params.getMessageBunble()), (Object[]) params.getMessageParams()));
        this.initCause(cause);
        addInfo(params);
    }

    public CommunException(CommunExceptionParams params) {
        super(MessageFormat.format(getString(params.getErrorCode(), params.getMessageBunble()), (Object[]) params.getMessageParams()));
        addInfo(params);
    }

    public final String getLastCode() {
        return lastInfoItem.errorCode;
    }

    /**
     * Set text to be write before error message.
     *
     * @param prefixErrText prefix text
     */
    public void setPrefixErrText(String prefixErrText) {
        this.prefixErrText = prefixErrText;
    }

    /**
     * @param debugText the debugText to set
     */
    public void setDebugText(String debugText) {
        this.debugText = debugText;
    }

    public CommunException addInfo(CommunExceptionParams params) {

        if (params.getPrefixErrText() != null) {
            this.prefixErrText = params.getPrefixErrText();
        }
        if (params.getDebugText() != null) {
            this.debugText = params.getDebugText();
        }
        return addInfo(params.getErrorCode(), params.getMessageBunble(), params.getMessageParams());
    }

    public CommunException addInfo(String errorCode, String messageBunble, Object... messageParams) {

        String[] params = null;
        if (messageParams != null) {
            params = new String[messageParams.length];
            for (int i = 0; i < messageParams.length; i++) {
                if (messageParams[i] != null) {
                    params[i] = messageParams[i].toString();
                } else {
                    params[i] = null;
                }
            }
        }
        return addInfo(errorCode, messageBunble, params);
    }

    public CommunException addInfo(String errorCode, String messageBunble, String... messageParams) {

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String errorContext = stackTraceElements[depth].getClassName() + ":" + stackTraceElements[depth].getMethodName();

        String errorText;

        try {
            ResourceBundle bundle = Utf8ResourceBundle.getBundle(messageBunble);
            errorText = prefixErrText + MessageFormat.format(bundle.getString(errorCode), (Object[]) messageParams);
        } catch (Exception e) {
            errorText = prefixErrText;

            if (messageParams != null) {

                for (int i = 0; i < messageParams.length; i++) {
                    errorText += "arg(" + i + ")=" + messageParams[i];

                    if (i < (messageParams.length - 1)) {
                        errorText += ", ";
                    } else {
                        errorText += ". ";
                    }
                }
            }
        }

        if (logger.getEffectiveLevel().isGreaterOrEqual(Level.DEBUG)) {
            if (!debugText.equals("")) {
                errorText += "\nDEBUG: " + debugText;
            }
        }

        this.infoItems.add(lastInfoItem = new InfoItem(errorContext, errorCode, errorText));
        // analyse cause from DB for errors that can be recovered at application layer
        JDBCException hibernateException = getSQLCause(this);
        if (hibernateException != null) {
            dbInfoCause = new DBInformation();
            if (hibernateException.getSQLException() != null) {
                dbInfoCause.dbMessage = hibernateException.getSQLException().getMessage();
                dbInfoCause.sqlVendorCode = hibernateException.getSQLException().getErrorCode();
            }
            dbInfoCause.sqlStatus = hibernateException.getSQLState();
            if (hibernateException.getSQLState() != null) {
                dbInfoCause.sqlStatusClass = hibernateException.getSQLState().substring(0, 2);
            }
            dbInfoCause.sqlString = hibernateException.getSQL();
            if (hibernateException instanceof ConstraintViolationException) {
                dbInfoCause.failedConstraint = ((ConstraintViolationException) hibernateException).getConstraintName();
            }
        }

        return this;
    }

    private JDBCException getSQLCause(Exception e) {
        int depthLocal = 1;
        if (e == null) {
            return null;
        }
        if (e instanceof JDBCException) {
            return (JDBCException) e;
        }
        Throwable previous = e;
        Throwable current;
        if (e instanceof TransactionSystemException) {
            current = ((TransactionSystemException) e).getApplicationException();
        } else {
            current = e.getCause();
        }
        for (; (current != null && depthLocal < MAX_DEPTH_ANALYSIS); depthLocal++) {
            if (previous == current) {
                break;
            }
            if (current instanceof JDBCException) {
                return (JDBCException) current;
            }
            if (current instanceof TransactionSystemException) {
                current = ((TransactionSystemException) current).getApplicationException();
            } else {
                current = current.getCause();
            }
        }
        return null;
    }

    public String getCode() {
        return getCode(false);
    }

    public String getCode(boolean printErrText) {

        StringBuilder builder = new StringBuilder();

        for (int i = this.infoItems.size() - 1; i >= 0; i--) {
            InfoItem info = this.infoItems.get(i);
            builder.append('[');
            builder.append(info.errorContext);
            builder.append(':');
            builder.append(info.errorCode);
            builder.append("] ");
            if (printErrText) {
                builder.append(info.errorText);
            }
            if (i > 0) {
                builder.append('\n');
            }
        }

        return builder.toString();
    }

    /**
     * Returns a message containing the Database Information Cause if any.
     *
     */
    @Override
    public String getLocalizedMessage() {

        String retMessage = getMessage();

        if (this.dbInfoCause != null) {
            retMessage += (this.dbInfoCause.sqlVendorCode != 0) ? (this.dbInfoCause.sqlVendorCode + " - ") : "";
            retMessage += (this.dbInfoCause.dbMessage != null) ? (this.dbInfoCause.dbMessage) : "";
        }

        return retMessage;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append(getCode(true)); // if false

        if (getMessage() != null) {
            builder.append('\n'); // if false
            if (getCause() == null) {
                builder.append(getMessage());
            } else if (!getMessage().equals(getCause().toString())) {
                builder.append(getMessage());
            }
        }
        appendException(builder, getCause());

        return builder.toString();
    }

    private void appendException(StringBuilder builder, Throwable throwable) {
        if (throwable == null) {
            return;
        }
        appendException(builder, throwable.getCause());
        builder.append(throwable.toString());
        builder.append('\n');
    }

    /**
     * The concatenation of every readable messages
     *
     * @return a simple message
     */
    public String getSimpleMessage() {

        StringBuilder builder = new StringBuilder();

        builder.append(getMessage());

        try {
            Throwable currentCause = getCause();
            while (currentCause instanceof Exception) {
                // addMessage if the 'cause message' and the 'message' are different
                boolean addMessage = (currentCause.getCause() == null) || (!currentCause.getMessage().equals(currentCause.getCause().getMessage()));
                if (addMessage) {
                    builder.append('\n');
                    builder.append(currentCause.getMessage());
                }
                currentCause = currentCause.getCause();
            } // end while
        } catch (Exception e) {
            logger.error("Exception while generating simple message: " + e.getMessage(), e);
        }

        return builder.toString();
    }

    /**
     * The concatenation of every readable messages This is nearly like the
     * toString method. Cette methode est créee pour ne pas introduire de
     * regression dans toString.
     *
     * @return a technical message
     */
    public String getTechnicalMessage() {

        StringBuilder builder = new StringBuilder();

        // on commence à l'exception courante et on parcours les causes
        Throwable currentCause = this;
        try {
            while (currentCause instanceof Exception) {
                String s = currentCause.getClass().getName();
                s += (currentCause instanceof CommunException) ? (":" + ((CommunException) currentCause).errorCode) : "";
                s += ((currentCause.getMessage() != null) ? (": " + currentCause.getMessage()) : "");
                builder.append(s);
                builder.append('\n');
                currentCause = currentCause.getCause();
            } // end while
        } catch (Exception e) {
            logger.error("Exception while generating technical message: " + e.getMessage(), e);
        }

        String ret = builder.toString();

        // Remove the last NEWLINE
        if (ret.length() > 2 && ret.endsWith("\n")) {
            ret = ret.substring(0, ret.length() - 2);
        }

        return ret;
    }

    @SuppressWarnings("serial")
    protected class InfoItem implements Serializable {

        /**
         * Eg:
         * com.rsd.glass.service.igpEditor.impl.CodeTemplateServiceImpl:create
         */
        public String errorContext = null;
        /**
         * Eg: gpEditor.err.code.template
         */
        public String errorCode = null;
        /**
         * The error text
         */
        public String errorText = null;

        public InfoItem(String contextCode, String errorCode, String errorText) {

            this.errorContext = contextCode;
            this.errorCode = errorCode;
            this.errorText = errorText;
        }
    }

    /**
     * @return the dbInfoCause
     */
    public DBInformation getDbInfoCause() {
        return dbInfoCause;
    }
}
