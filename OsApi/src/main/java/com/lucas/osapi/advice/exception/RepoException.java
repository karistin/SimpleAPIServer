package com.lucas.osapi.advice.exception;

/**
 * packageName    : com.lucas.osapi.advice.exception
 * fileName       : RepoException
 * author         : lucas
 * date           : 2022-11-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-30        lucas       최초 생성
 */
public class RepoException extends RuntimeException{
    public RepoException() {
        super();
    }

    public RepoException(String message) {
        super(message);
    }

    public RepoException(String message, Throwable cause) {
        super(message, cause);
    }
}
