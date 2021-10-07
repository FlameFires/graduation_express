package com.express.config;

import com.express.model.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;

/**
 * 全局异常处理
 * Created by cc_want on 2018/6/2.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 打印异常错误信息
     *
     * @param message
     * @param result
     */
    public static void printBindException(StringBuffer message, BindingResult result) {
        List<ObjectError> allErrors = result.getAllErrors();
        for (int i = 0; i < allErrors.size(); i++) {
            message.append(allErrors.get(i).getDefaultMessage());
            if (allErrors.size() > 1 && i < allErrors.size() - 1) {
                message.append("<br/>");
            }
        }
    }

    /**
     * 方法参数校验
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultMsg handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        System.out.println("参数校验异常：" + e.getMessage());
        return ResultMsg.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultMsg handleMethodArgumentNotValidException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        System.out.println("参数校验异常：" + e.getMessage());
        return ResultMsg.error(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResultMsg exceptionHandler(Exception ex) throws IOException {
        log.error(ex.getMessage(), ex);
        if (ex instanceof MethodArgumentNotValidException) {
            BindingResult result = ((MethodArgumentNotValidException) ex).getBindingResult();
            if (result.hasErrors()) {
                StringBuffer sb = new StringBuffer();
                printBindException(sb, result);
                return ResultMsg.error(sb.toString());
            }
        }
        return ResultMsg.error(ex.toString());
    }
}