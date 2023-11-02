package com.dsm.up_backend_v2.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    //Bad Request
    BAD_REQUEST(400, "잘못된 요청입니다."),
    PASSWORD_MISMATCH(400, "비밀번호가 일치하지 않습니다."),
    IMAGE_UPLOAD_FAIL(400, "이미지가 정상적으로 업로드 되지 않았습니다."),
    IMAGE_FORM_FALSE(400, "이미지 형식이 올바르지 않습니다."),

    //UnAuthorised
    NOT_ACCESS_TOKEN(401, "access 토큰이 아닙니다."),
    TOKEN_ERROR(401, "토큰이 잘못되지 않았는지 확인해주세요."),
    TOKEN_UNAUTHORIZED(401,"토큰이 유효 하지 않습니다."),

    //Forbidden
    USER_NOT_MATCH_(403,"작성자와 일치하지 않습니다"),

    //Not Found
    USER_NOT_FOUND(404,"찾을 수 없는 유저입니다."),
    POST_NOT_FOUND(404, "게시글을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(404, "댓글을 찾을 수 없습니다."),
    REFRESH_TOKEN_NOT_FOUND(404, "리프레쉬 토큰을 찾을 수 없습니다. ");

    private final int statusCode;
    private final String errorMessage;
}