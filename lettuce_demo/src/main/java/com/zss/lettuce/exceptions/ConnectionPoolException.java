package com.zss.lettuce.exceptions;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/10 13:24
 * @desc 连接池异常
 */
@SuppressWarnings("unused")
public class ConnectionPoolException extends Exception {

    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
