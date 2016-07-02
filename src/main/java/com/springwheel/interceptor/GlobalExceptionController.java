package com.springwheel.interceptor;

import com.springwheel.api.support.ErrorResult;
import com.springwheel.common.exception.ParamCheckException;
import com.springwheel.common.util.constants.MediaTypes;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author hjm
 * @Time 2016/6/5 9:36.
 */
public class GlobalExceptionController extends BasicErrorController {


    public GlobalExceptionController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes, errorProperties);
    }

    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request,
                                  HttpServletResponse response) {


        HttpStatus httpStatus = getStatus(request);

        //如果是404的错误
        if (HttpStatus.NOT_FOUND == httpStatus) {
            return new ModelAndView("error-404");
        }

        //代码异常
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));

        return new ModelAndView("error", model);
    }

    @RequestMapping
    @ResponseBody
    public ResponseEntity error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);

        //参数校验异常
        String exception =   (String)body.get("exception");
        String message = (String)body.get("message");
        if (exception.equals(ParamCheckException.class.getName())){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
            ErrorResult result = new ErrorResult(HttpStatus.BAD_REQUEST.value(),
                    message);
            return new ResponseEntity<ErrorResult>(result,headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Map<String, Object>>(body, status);
    }


}
